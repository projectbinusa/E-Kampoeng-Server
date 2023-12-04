package com.e_kampoeng.controller;


import com.e_kampoeng.config.JwtTokenUtil;
import com.e_kampoeng.dao.UserDao;
import com.e_kampoeng.exception.CommonResponse;
import com.e_kampoeng.exception.ResponseHelper;
import com.e_kampoeng.model.*;
import com.e_kampoeng.dto.UserDTO;
import com.e_kampoeng.service.JwtUserDetailsService;
import com.e_kampoeng.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class JwtAuthenticationController {

    public static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

    //	Post User

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserDao userDao;

    //	Login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        // Menggunakan email sebagai pengganti username
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        UserModel user = userDao.findByEmail(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, user));
    }


    //	Register
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResponse<UserModel> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseHelper.ok(userDetailsService.save(user));
    }

    //    Authentication user
    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    //    Update profile
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateData(@PathVariable("id") long id, @RequestBody UserDTO user) throws SQLException, ClassNotFoundException {
        logger.info("Updating data with id {}", id);

        Optional<UserModel> currentData = userDetailsService.findById(id);

        if (currentData == null) {
            logger.error("Unable to update. data with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. data with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        currentData.orElseThrow().setProfile(user.getProfile());
        userDetailsService.update(currentData.get().getId());
        return new ResponseEntity<>(currentData, HttpStatus.OK);
    }

    //    Get profile & user
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getData(@PathVariable("id") long id) throws SQLException, ClassNotFoundException {
        logger.info("Fetching data a with id {}", id);

        Optional<UserModel> user = userDetailsService.findById(id);

        if (user == null) {
            logger.error("data with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("data with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}