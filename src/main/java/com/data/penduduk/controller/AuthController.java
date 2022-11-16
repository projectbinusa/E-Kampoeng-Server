package com.data.penduduk.controller;

import com.data.penduduk.model.Rt;
import com.data.penduduk.model.User;
import com.data.penduduk.payload.request.LoginRequest;
import com.data.penduduk.payload.request.LoginRequestRt;
import com.data.penduduk.payload.response.JwtResponse;
import com.data.penduduk.payload.response.JwtResponseRt;
import com.data.penduduk.payload.response.MessageResponse;
import com.data.penduduk.repository.RtRepository;
import com.data.penduduk.repository.UserRepository;
import com.data.penduduk.security.jwt.JwtUtils;
import com.data.penduduk.security.jwt.JwtUtilsRt;
import com.data.penduduk.service.UserDetailsImpl;
import com.data.penduduk.service.UserDetailsImplRt;
import com.data.penduduk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RtRepository rtRepository;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtUtilsRt jwtUtilsRt;

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
                userDetails.getRole(),
                userDetails.getRt()
        ));
    }

    @PostMapping("/login-rt")
    public ResponseEntity<?> authenticatRt(@RequestBody LoginRequestRt loginRequestRt) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestRt.getEmail(), loginRequestRt.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtRt = jwtUtilsRt.generateJwtTokenRt(authentication);

        UserDetailsImplRt userDetailsRt = (UserDetailsImplRt) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponseRt(jwtRt,
                userDetailsRt.getId(),
                userDetailsRt.getEmail(),
                userDetailsRt.getUsername(),
                userDetailsRt.getRole()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // validasi ketika email telah digunakan
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email telah digunakan!"));
        }

        // validasi ketika username telah digunakan
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username telah digunakan!"));
        }

        User users = userService.register(user);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/rw-{id}/add-rt")
    public ResponseEntity<?> registerRt(@PathVariable("id") Long id, @RequestBody Rt rt) {
        // validasi ketika email telah digunakan
        if (rtRepository.existsByEmail(rt.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email telah digunakan!"));
        }

        // validasi ketika username telah digunakan
        if (rtRepository.existsByUsername(rt.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username telah digunakan!"));
        }

        Rt rtt = userService.registerRt(rt, id);
        return ResponseEntity.ok(rtt);
    }

    @GetMapping("/rw")
    public ResponseEntity<?> getAllRw() {
            List<User> sekolah = userService.getAllRw();
            return new ResponseEntity<>(sekolah, HttpStatus.OK);
    }

}
