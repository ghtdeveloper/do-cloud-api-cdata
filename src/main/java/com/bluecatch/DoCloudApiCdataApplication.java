package com.bluecatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication()
public class DoCloudApiCdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoCloudApiCdataApplication.class, args);
	}

}
