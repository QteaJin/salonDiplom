package com.salon.service.crypto;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;

import com.salon.repository.bean.auth.AuthBean;
import com.salon.repository.bean.profile.ProfileBean;
import com.salon.service.profile.ProfileService;
import com.salon.utility.EnumRole;

@Component
public class TokenCryptImpl implements TokenCrypt {

	@Autowired
	private ProfileService profileService;
	
	private final String password = "nfjn uifdshudfnvnus3324jnhia";
	
	private transient Map<Long, String> userTokens = new HashMap<Long, String>(); //save Users tokens
	
	private String salt = KeyGenerators.string().generateKey();
	private TextEncryptor encryptor = Encryptors.text(password, salt);
	
	@Override
	public String cryptToken(String message) {
		
	        
	        String cipherText = encryptor.encrypt(message);
	        
	    
		return cipherText;
	}
	@Override
	public String decryptToken(String message) {
		
		               
        String decryptedText = encryptor.decrypt(message);
		
		return decryptedText;
		
		
	}
	public Map<Long, String> getUserTokens() {
		return userTokens;
	}
	@Override
	public AuthBean checkToken(String token) {
		
		AuthBean bean = new AuthBean();
		if(token == null) {
			bean.setErrorMessage("Token is NULL");
			return bean;
		}
		String decrypt;
				
		try {
			decrypt = decryptToken(token); 
			
		} catch (Exception e) { // javax.crypto.BadPaddingException
			bean.setErrorMessage("Error token encoding");
			
			return bean;
		}
		
		
		String [] splitToken = decrypt.split(" ");
		Long profileId = Long.valueOf(splitToken[0]);
		ProfileBean profBean = profileService.findById(profileId);
		if(profBean.getClient() != null) {
			bean.setUserId(profBean.getClient().getId());
		}else {
			bean.setUserId(profBean.getWorker().getId());
		}
		
		bean.setProfileId(profileId);
		
		switch (splitToken[1]) {
		case "CLIENT": bean.setEnumRole(EnumRole.CLIENT);
			
			break;
			
		case "WORKER": bean.setEnumRole(EnumRole.WORKER);
		
			break;
		
		case "ADMIN": bean.setEnumRole(EnumRole.ADMIN);
			
			break;
		
		case "MANAGER": bean.setEnumRole(EnumRole.MANAGER);
		
			break;
		
		}
				
		return bean;
		
	}
	public String getCookie(HttpServletRequest req) {
		if(req.getCookies()==null) {
			return "token error";
		}
        for (Cookie c : req.getCookies()) {
           if (c.getName().equals("token"))
               return c.getValue();
           }
        return null;
    }
    
}

