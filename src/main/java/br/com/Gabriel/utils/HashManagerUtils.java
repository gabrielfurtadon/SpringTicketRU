package br.com.Gabriel.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class HashManagerUtils {
 
    private HashManagerUtils(){
	}
    
    private static BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

	public static String generateCrypt(String password) {
        return bCrypt.encode(password);
    }

    public static Boolean validateHash(String userPwd, String hashedPwd){
        return bCrypt.matches(userPwd, hashedPwd);
    }
}
