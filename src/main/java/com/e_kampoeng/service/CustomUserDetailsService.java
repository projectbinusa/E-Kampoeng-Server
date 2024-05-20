//package com.e_kampoeng.service;
//
//import com.e_kampoeng.config.CustomUserDetails;
//import com.e_kampoeng.repository.WargaRepository;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final WargaRepository userRepository;
//
//    public CustomUserDetailsService(WargaRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//
//        return new CustomUserDetails(
//                user.getId(),
//                user.getUsername(),
//                user.getPassword(),
//                user.getAuthorities()
//        );
//    }
//}
