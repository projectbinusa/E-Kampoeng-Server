package com.e_kampoeng.service;

import com.e_kampoeng.model.UserModel;
import com.e_kampoeng.model.WargaModel;
import com.e_kampoeng.repository.UserRepository;
import com.e_kampoeng.repository.WargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthService  implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WargaRepository wargaRepository;

    // mencari user berdasarkan username
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserModel userModel = userRepository.findByEmail(email);
        if (userModel != null) {
            // Cek jika pengguna memiliki peran admin
            if (userModel.getRole().equals("admin")) {
                List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
                return new User(userModel.getEmail(), userModel.getPassword(), roles);
            }
        }

        // Cari pengguna dalam tabel WargaModel
        WargaModel wargaModel = wargaRepository.findByEmail(email);
        if (wargaModel != null) {
            // Cek jika pengguna memiliki peran rt
            if (wargaModel.getRole().equals("rt")) {
                List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_RT"));
                return new User(wargaModel.getEmail(), wargaModel.getPassword(), roles);
            }
            // Cek jika pengguna memiliki peran warga
            else if (wargaModel.getRole().equals("warga")) {
                List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_WARGA"));
                return new User(wargaModel.getEmail(), wargaModel.getPassword(), roles);
            }
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
