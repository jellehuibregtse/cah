package com.jellehuibregtse.cah.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is used only for testing purposes.
 * Especially to check if the JWT authentication is ok.
 *
 * @author Jelle Huibregtse
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Success!");
    }
}
