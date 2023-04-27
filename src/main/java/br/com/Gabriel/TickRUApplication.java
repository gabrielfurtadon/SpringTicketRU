package br.com.Gabriel;

//import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TickRUApplication {

	public static void main(String[] args) {
		SpringApplication.run(TickRUApplication.class, args);

		/*
		 * String token = Jwts.builder().setSubject("ADMIN").setIssuedAt(new
		 * Date(System.currentTimeMillis()))
		 * .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
		 * .signWith(
		 * Keys.hmacShaKeyFor(Decoders.BASE64
		 * .decode("404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970")),
		 * SignatureAlgorithm.HS256)
		 * .compact();
		 * 
		 * System.out.println(token);
		 */
	}

}
