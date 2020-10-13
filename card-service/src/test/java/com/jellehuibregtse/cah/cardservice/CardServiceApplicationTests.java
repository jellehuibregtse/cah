package com.jellehuibregtse.cah.cardservice;

import com.jellehuibregtse.cah.cardservice.controllers.CardController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class CardServiceApplicationTests {

    @Autowired
    private CardController cardController;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(cardController);
    }
}
