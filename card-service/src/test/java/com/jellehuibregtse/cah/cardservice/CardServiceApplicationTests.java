package com.jellehuibregtse.cah.cardservice;

import com.jellehuibregtse.cah.cardservice.controllers.CardController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CardServiceApplicationTests {

    @Autowired
    private CardController cardController;

    @Test
    public void contextLoads() {
        assertThat(cardController).isNotNull();
    }
}
