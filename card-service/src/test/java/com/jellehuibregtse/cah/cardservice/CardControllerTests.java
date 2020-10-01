package com.jellehuibregtse.cah.cardservice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CardControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getCard() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/card/get?cardId=0"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":0,\"cardType\":null,\"cardText\":null}"));
    }

}
