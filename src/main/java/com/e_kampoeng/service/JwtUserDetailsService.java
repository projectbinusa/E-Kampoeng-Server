package com.e_kampoeng.service;

import com.e_kampoeng.exception.NotFoundException;
import com.e_kampoeng.repository.UserRepository;
import com.e_kampoeng.exception.InternalErrorException;
import com.e_kampoeng.model.UserModel;
import com.e_kampoeng.dto.UserDTO;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
//	private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/storage-e-kampoeng.appspot.com/o/%s?alt=media";


	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public JwtUserDetailsService() {
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserModel user = userDao.findByEmail(email);
		if (user == null) {
			throw new BadCredentialsException("Email atau password salah!");
		}

		List<SimpleGrantedAuthority> roles = null;

		if (user.getRole().equals("rw")) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new User(email, user.getPassword(), roles);
		}
		if (user.getRole().equals("warga")) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
			return new User(email, user.getPassword(), roles);
		}

		return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}
	public UserModel save(UserDTO user) throws Exception {
		// Periksa apakah email sudah ada dalam basis data
		if (userDao.findByEmail(user.getEmail()) != null) {
			throw new InternalErrorException("Email already exists");
		}

		UserModel newUser = new UserModel();
		newUser.setEmail(user.getEmail());
		newUser.setRole(user.getRole());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		var password = user.getPassword().trim();
		// digit + lowercase char + uppercase char + punctuation + symbol
		var isPasswordValid = !password.matches("^(?=.*[a-z]).{8,20}$");
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
	public UserModel update(Long id) {
		UserModel user = userDao.findById(id).orElse(null);
		if (user == null) {
			throw new NotFoundException("User Id not found");
		}
		return userDao.save(user);
	}

//	private String imageConverter(MultipartFile multipartFile) throws Exception {
//		try {
//			String fileName = getExtension(multipartFile.getOriginalFilename());
//			File file = convertFile(multipartFile, fileName);
//			var RESPONSE_URL = uploadFile(file, fileName);
//			file.delete();
//			return RESPONSE_URL;
//		} catch (Exception e) {
//			e.getStackTrace();
//			throw new Exception("Error upload file!");
//		}
//	}
//
//	private String getExtension(String fileName) {
//		return  fileName.split("\\.")[0];
//	}
//
//	private File convertFile(MultipartFile multipartFile, String fileName) throws IOException {
//		File file = new File(fileName);
//		try (FileOutputStream fos = new FileOutputStream(file)) {
//			fos.write(multipartFile.getBytes());
//			fos.close();
//		}
//		return file;
//	}
//
//	private String uploadFile(File file, String fileName) throws IOException {
//		try {
//			BlobId blobId = BlobId.of("storage-e-kampoeng.appspot.com", fileName);
//			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
//			InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("e-kampoeng-firebase.json");
//			Credentials credentials = GoogleCredentials.fromStream(serviceAccount);
//			Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//			storage.create(blobInfo, Files.readAllBytes(file.toPath()));
//			return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new IOException("Error uploading file to Google Cloud Storage: " + e.getMessage());
//		}
//	}
}
