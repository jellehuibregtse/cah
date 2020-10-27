package com.jellehuibregtse.cah.authservice.service;

import com.jellehuibregtse.cah.authservice.jwt.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class JwtTokenService {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtTokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(String subject, List<String> authorities) {
        var now = System.currentTimeMillis();

        return Jwts.builder()
                   .setSubject(subject)
                   .claim("authorities", authorities)
                   .setIssuedAt(new Date(now))
                   .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getExpiration())))
                   .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                   .compact();
    }
}
