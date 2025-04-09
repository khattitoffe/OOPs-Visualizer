package com.khattitoffe.WebBluej.service;
import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
@Component
public class JWTUtil {
    private String secretKey="myapplication@4bahujd*(@!6ye9182";
    private long expiration =1000*60*60*12;

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token,String username){
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }
}
