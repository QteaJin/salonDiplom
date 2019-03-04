package com.salon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import com.salon.service.crypto.TokenCrypt;

@SpringBootApplication
public class CloudSalonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudSalonApplication.class, args);
		

	}

}

