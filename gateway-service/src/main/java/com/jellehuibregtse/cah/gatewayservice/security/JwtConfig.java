package com.jellehuibregtse.cah.gatewayservice.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * The JwtConfig which reads from the application properties.
 *
 * @author Jelle Huibregtse
 */
@Getter
@Setter
@Configuration
public class JwtConfig {

    @Value("${security.jwt.uri:/api/auth-service/auth/**}")
    private String uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;
}
