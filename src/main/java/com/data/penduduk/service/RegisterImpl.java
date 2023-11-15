package com.data.penduduk.service;

import com.data.penduduk.exception.NotFoundException;
import com.data.penduduk.model.UserModel;
import com.data.penduduk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterImpl {
    @Autowired
    private UserRepository userRepository;

    public UserModel registerNewUser(UserModel newUser) {
        // Periksa apakah pengguna sudah ada
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            throw new NotFoundException("Username sudah digunakan");
        }

        // Enkripsi password sebelum menyimpannya
        String hashedPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        // Simpan pengguna baru ke repositori
        return userRepository.save(newUser);
    }
}
