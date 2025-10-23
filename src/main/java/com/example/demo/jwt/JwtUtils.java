package com.example.demo.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private static final String SECREAT_KEY = "7mA0OdmA3rQpVDtJnRok8lfSrwgTARPL";
    private final SecretKey SECREAT = Keys.hmacShaKeyFor(SECREAT_KEY.getBytes());

    public String getJwtTokenGeneration(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 *60 *60))
                .signWith(SECREAT, Jwts.SIG.HS256)
                .compact()
                ;
    }

    public boolean verifyToken(String token, UserDetails userDetails ){
        return getUserName(token).equals(userDetails.getUsername());
    }
    public String getUserName(String token){
        return Jwts.parser()
                .verifyWith(SECREAT)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


}
