package com.jellehuibregtse.cah.authservice;

import com.jellehuibregtse.cah.authservice.controller.TestController;
import com.jellehuibregtse.cah.authservice.controller.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceApplicationTests {

    @Autowired
    private TestController testController;

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(testController);
        Assertions.assertNotNull(userController);
    }
}