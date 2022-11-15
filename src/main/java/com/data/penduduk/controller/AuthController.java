package com.data.penduduk.controller;

import com.data.penduduk.model.User;
import com.data.penduduk.payload.request.LoginRequest;
import com.data.penduduk.payload.response.JwtResponse;
import com.data.penduduk.payload.response.MessageResponse;
import com.data.penduduk.repository.UserRepository;
import com.data.penduduk.security.jwt.JwtUtils;
import com.data.penduduk.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getUsername(),
                userDetails.getRole()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email telah digunakan!"));
        }

        // Create new user's account
        String role = user.getRole();
        if (role == null) {
            user.setRole("user");
        } else {
            switch (user.getRole()) {
                case "admin":
                    user.setRole("admin");
                    break;
                default:
                    user.setRole("user");
            }
        }
        user.setPassword(encoder.encode(user.getPassword()));

        User users = userRepository.save(user);
        return ResponseEntity.ok(users);

    }

}
