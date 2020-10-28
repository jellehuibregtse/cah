package com.jellehuibregtse.cah.authservice;

import com.jellehuibregtse.cah.authservice.controller.TestController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceApplicationTests {

    @Autowired
    private TestController testController;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(testController);
    }
}