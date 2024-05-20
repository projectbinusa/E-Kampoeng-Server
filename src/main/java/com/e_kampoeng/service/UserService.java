package com.e_kampoeng.service;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.repository.UserRepository;
import com.e_kampoeng.exception.InternalErrorException;
import com.e_kampoeng.model.UserModel;
import com.e_kampoeng.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public UserService() {
    }

    public UserModel saveWargaRoleAdmin(UserDTO userDTO) throws Exception {
        // Periksa apakah email sudah ada dalam basis data
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new InternalErrorException("Email already exists");
        }
        UserModel user = new UserModel();
        user.setEmail(userDTO.getEmail());
        user.setRole("admin");
        user.setUsername(userDTO.getUsername());
        user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }
}