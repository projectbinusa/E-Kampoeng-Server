//package com.data.penduduk.service;
//
//import com.data.penduduk.dao.UserDao;
//import com.data.penduduk.dto.UserDTO;
//import com.data.penduduk.exception.InternalErrorException;
//import com.data.penduduk.model.UserModel;
////import com.data.penduduk.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class UserImpl implements UserDetailsService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private PasswordEncoder bcryptEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserModel user = userDao.findByUsername(username);
//        List<SimpleGrantedAuthority> roles = null;
//
//        if (user.getRole().equals("admin")) {
//            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), roles);
//
//        }
//        if (user.getRole().equals("user")) {
//            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), roles);
//        }
//        return new User(user.getUsername(), user.getPassword(),
//                new ArrayList<>());
//    }
//
//    public UserModel save(UserDTO user) {
//        UserModel newUser = new UserModel();
//        newUser.setEmail(user.getEmail());
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//        newUser.setRole(user.getRole());
//        var password = user.getPassword().trim();
//        // digit + lowercase char + uppercase char + punctuation + symbol
//        var isPasswordValid = !password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
//        );
//        if(isPasswordValid) throw new InternalErrorException("Password yang anda masukkan tidak valid");
//        return userDao.save(newUser);
//    }
//
//    //    For find by id
//    public Optional<UserModel> findById(Long id) {
//        return Optional.ofNullable(userDao.findById(id));
//    }
//
//    //    For update
//    public UserModel update(Long id) {
//        UserModel user = userDao.findById(id);
//        return userDao.save(user);
//    }
//}
//
////@Service
////public class UserImpl implements UserDetailsService {
////
////    @Autowired
////    UserRepository userRepository;
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        UserModel user = userRepository.findByUsername(username);
////        if (user == null) {
////            throw new UsernameNotFoundException("User not found");
////        }
////        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
////    }
////    private Set<SimpleGrantedAuthority> getAuthority(UserModel user) {
////        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
////        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
////        return authorities;
////    }
////}
