package com.data.penduduk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

//@Configuration
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class DataPendudukApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataPendudukApplication.class, args);
		System.out.println("selesai");
	}

}
