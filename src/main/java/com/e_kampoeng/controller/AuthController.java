package com.e_kampoeng.controller;


import com.e_kampoeng.config.JwtTokenUtil;
import com.e_kampoeng.dto.WargaDTO;
import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.model.*;
import com.e_kampoeng.dto.UserDTO;
import com.e_kampoeng.repository.WargaRepository;
import com.e_kampoeng.request.WargaUpdateRequestDTO;
import com.e_kampoeng.service.AuthService;
import com.e_kampoeng.service.WargaService;
import com.google.api.pathtemplate.ValidationException;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-kampoeng/api")
@CrossOrigin(origins = "*")
public class AuthController {

    public static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private WargaService userDetailsService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody JwtRequest authenticationRequest) {
        try {
            authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

            final UserDetails userDetails = authService.loadUserByUsername(authenticationRequest.getEmail());

            // Membuat token JWT
            final String token = jwtTokenUtil.generateToken(userDetails);

            // Membuat JwtResponse dengan token dan data pengguna
            JwtResponse response = new JwtResponse(token, userDetails);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
    }


    // Metode untuk melakukan autentikasi menggunakan AuthenticationManager
    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        try {
            // Mendapatkan informasi pengguna yang sedang login dari SecurityContextHolder
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String loggedInEmail = authentication.getName();

            // Memanggil service untuk mendapatkan profil pengguna yang sedang login
            WargaModel profile = userDetailsService.getProfile(loggedInEmail);

            // Mengembalikan profil pengguna dalam respons HTTP
            return ResponseEntity.ok(profile);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve profile: " + e.getMessage());
        }
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody WargaUpdateRequestDTO requestDTO) {
        try {
            WargaModel updatedWarga = userDetailsService.updateProfile(requestDTO);
            return ResponseEntity.ok(updatedWarga);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}