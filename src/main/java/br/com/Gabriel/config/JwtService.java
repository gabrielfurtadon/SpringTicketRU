package br.com.Gabriel.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.Gabriel.dto.Exceptions.HandlerException;
import br.com.Gabriel.dto.Mappers.ERole;
import br.com.Gabriel.entities.User;

// handler exception, erole, user

@Component
public class JwtService {

    public static final long JWT_TOKEN_TIME = 1000 * 60 * 24;

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    // private final String secret =
    // Base64.getEncoder().encodeToString("segredo".getBytes());

    // encode the password "secret" in a strin SECRET_KEY, in base 256

    public String generateToken(String id, ERole role) {
        return Jwts
                .builder()
                .setSubject(role.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .setId(id)
                .compact();
    }

    public boolean isTokenValid(String token, User user) {
        // final String username = extractUsername(token);
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody()
                .getExpiration()
                .before(new Date());
    }

    private String getRoleFromToken(String token) {
        final Boolean isExpired = isTokenExpired(token);
        if (isExpired == false) {
            Claims claims = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
            return claims.getSubject();
        } else {
            throw new HandlerException("Login expired");
        }
    }

    public String getIdFromToken(String token) {
        final Boolean isExpired = isTokenExpired(token);
        if (isExpired == false) {
            Claims claims = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
            return claims.getId();
        } else {
            throw new HandlerException("Login expired");
        }
    }

    public String getUsernameFromToken(String token) {
        final Boolean isExpired = isTokenExpired(token);
        if (isExpired == false) {
            Claims claims = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
            return claims.getSubject();
        } else {
            throw new HandlerException("Login expired");
        }
    }

    public String getRaFromToken(String token) {
        final Boolean isExpired = isTokenExpired(token);
        if (isExpired == false) {
            Claims claims = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
            return claims.getSubject();
        } else {
            throw new HandlerException("Login expired");
        }
    }

    public Boolean validateToken(String authToken, ERole role) {
        final String roleFromToken = getRoleFromToken(authToken);
        return (roleFromToken.equals(role.toString()) && !isTokenExpired(authToken));
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // show decode secret key in terminal
    // create a token, sign it to a variable, then decode it and print it in
    // terminal
    // public static void main(String[] args) {

}