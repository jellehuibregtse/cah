package com.jellehuibregtse.cah.gatewayservice.security;

import org.springframework.beans.factory.annotation.Value;

/**
 * The JwtConfig which reads from the application properties.
 *
 * @author Jelle Huibregtse
 */
public class JwtConfig {

    @Value("${security.jwt.uri:/auth/**}")
    private String uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUri() {
        return uri;
    }

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }

}
