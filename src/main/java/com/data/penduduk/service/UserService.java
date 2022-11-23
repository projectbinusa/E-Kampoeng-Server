package com.data.penduduk.service;

import com.data.penduduk.model.Rt;
import com.data.penduduk.model.User;
import com.data.penduduk.repository.RtRepository;
import com.data.penduduk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RtRepository rtRepository;

    @Autowired
    PasswordEncoder encoder;

    public User register(User user) {
        // Create new user's account
        String role = user.getRole();
        if (role == null) {
            user.setRole("rw");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        User users = userRepository.save(user);
        return userRepository.save(users);
    }

    public Rt registerRt(Rt rt, Long id) {
        User user = userRepository.findById(id).orElse(null);
        // Create new user's account
        String role = rt.getRole();
        if (role == null) {
            rt.setRole("rt");
        }
        rt.setPassword(encoder.encode(rt.getPassword()));
        rt.setUser(user);
        return rtRepository.save(rt);
    }

    public List<User> getAllRw() {
        return userRepository.findAll();
    }


}
