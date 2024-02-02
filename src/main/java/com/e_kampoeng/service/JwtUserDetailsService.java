package com.e_kampoeng.service;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.repository.UserDao;
import com.e_kampoeng.exception.InternalErrorException;
import com.e_kampoeng.model.UserModel;
import com.e_kampoeng.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserModel user = userDao.findByEmail(email);
		List<SimpleGrantedAuthority> roles = null;

		if (user.getRole().equals("rw")) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new User(email, user.getPassword(), roles);

		}
		if (user.getRole().equals("warga")) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
			return new User(email, user.getPassword(), roles);
		}
		return new User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}

	public UserModel save(UserDTO user) {
		UserModel newUser = new UserModel();
		newUser.setEmail(user.getEmail());
		newUser.setUsername(user.getUsername());
		newUser.setImage(user.getImage());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		var password = user.getPassword().trim();
		// digit + lowercase char + uppercase char + punctuation + symbol
		var isPasswordValid = !password.matches("^(?=.*[a-z]).{8,20}$"
		);
		if(isPasswordValid) throw new InternalErrorException("Standarisasi Password: minimal 8 karakter");
		return userDao.save(newUser);
	}

	//    For find by id
	public UserModel findById(Long id) {
		UserModel user = userDao.findById(id).orElse(null);
		if (user == null) {
			throw new NotFoundException("User Id not found");
		}
		return user;
	}

	//    For update
	public UserModel update(Long id) {
		UserModel user = userDao.findById(id).orElse(null);
		if (user == null) {
			throw new NotFoundException("User Id not found");
		}
		return userDao.save(user);
	}
}
