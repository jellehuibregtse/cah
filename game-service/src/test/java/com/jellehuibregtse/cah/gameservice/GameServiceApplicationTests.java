package com.jellehuibregtse.cah.gameservice;

import com.jellehuibregtse.cah.gameservice.controller.GameController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Application test to see if the context loads.
 *
 * @author Jelle Huibregtse
 */
@SpringBootTest
class GameServiceApplicationTests {

    @Autowired
    private GameController gameController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(gameController);
    }
}
