//package com.data.penduduk.service;
//
//import com.data.penduduk.model.User;
//import com.data.penduduk.repository.UserRepository;
//import com.data.penduduk.response.CustomResponse;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    private final String secretKey = "E-Kampoeng793153";
//
//    public boolean register(User user) {
//        if (userRepository.findByUsername(user.getUsername()) != null) {
//            return false;
//        }
//        User user1 = new User();
//        user1.setUsername(user.getUsername());
//        user1.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        userRepository.save(user1);
//        return true;
//    }
//
//    public CustomResponse loginAdmin(User user) {
//        CustomResponse response = new CustomResponse();
//
//        User user1 = userRepository.findByUsername(user.getUsername());
//        if (user1 != null && passwordEncoder.matches(user.getPassword(), user.getPassword())) {
//            // Generate JWT token jika login berhasil
//            String token = generateToken(user1.getUsername());
//
//            response.setStatus("success");
//            response.setCode(200);
//            response.setMessage("Login berhasil.");
//            response.setData(token); // Mengirim token ke klien
//            return response;
//        }
//
//        response.setStatus("error");
//        response.setCode(401); // Unauthorized
//        response.setMessage("Gagal masuk. Periksa kredensial Anda.");
//        return response;
//    }
//
//    private String generateToken(String username) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + 864000000); // Token berlaku selama 10 hari (misalnya)
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .compact();
//    }
//
//}
