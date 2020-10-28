package com.jellehuibregtse.cah.authservice.auth;

import com.jellehuibregtse.cah.authservice.jwt.JwtConfig;
import com.jellehuibregtse.cah.authservice.service.JwtTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthTests {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private JwtConfig jwtConfig;

    @Test
    public void generateToken_returnsString() {
        var token = jwtTokenService.generateToken("subject");

        assertNotNull(token);
        assertFalse(token.contains(jwtConfig.getPrefix()));
    }
}
