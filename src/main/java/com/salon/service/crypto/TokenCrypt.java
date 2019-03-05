package com.salon.service.crypto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;

@Component
public class TokenCrypt {

	private final String password = "nfjn uifdshudfnvnus3324jnhia";
	
	private transient Map<Long, String> userTokens = new HashMap<Long, String>(); //save Users tokens
	
	private String salt = KeyGenerators.string().generateKey();
	private TextEncryptor encryptor = Encryptors.text(password, salt);
	
	public String cryptToken(String message) {
		
	        
	        String cipherText = encryptor.encrypt(message);
	        
	    
		return cipherText;
	}
	public String decryptToken(String message) {
		
		               
        String decryptedText = encryptor.decrypt(message);
		
		return decryptedText;
		
		
	}
	public Map<Long, String> getUserTokens() {
		return userTokens;
	}
	
    
}

