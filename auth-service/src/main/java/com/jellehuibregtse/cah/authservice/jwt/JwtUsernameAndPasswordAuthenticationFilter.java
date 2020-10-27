package com.jellehuibregtse.cah.authservice.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * This filter authenticates a user with username and password then returns a JWT token.
 *
 * @author Jelle Huibregtse
 */
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtConfig jwtConfig;
    // We use auth manager to validate the user credentials.
    private final AuthenticationManager authManager;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
        this.authManager = authManager;
        this.jwtConfig = jwtConfig;

        // By default, UsernamePasswordAuthenticationFilter listens to "/login" path.
        // In our case, we use "/auth". So, we need to override the defaults.
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), HttpMethod.POST));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            // Get credentials from request.
            var userCredentials = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);

            // Create auth object, that contains the credentials, which will be used by auth manager
            var authToken = new UsernamePasswordAuthenticationToken(userCredentials.getUsername(),
                                                                    userCredentials.getPassword(),
                                                                    Collections.emptyList());

            // Authentication manager authenticates the user, and uses the UserDetailsServiceImpl::loadUserByUsername() method to load the user.
            return authManager.authenticate(authToken);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Upon successful authentication, generate a token.
    // The 'auth' passed to successfulAuthentication() is the current authenticated user.
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) {

        var now = System.currentTimeMillis();
        var token = Jwts.builder()
                        .setSubject(auth.getName())
                        // Convert to list of strings.
                        // This is important because it affects the way we get them back at the gateway.
                        .claim("authorities",
                               auth.getAuthorities()
                                   .stream()
                                   .map(GrantedAuthority::getAuthority)
                                   .collect(Collectors.toList()))
                        .setIssuedAt(new Date(now))
                        .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getExpiration())))
                        .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                        .compact();

        // Add token to header.
        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
    }

    private static class AuthenticationRequest {

        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}