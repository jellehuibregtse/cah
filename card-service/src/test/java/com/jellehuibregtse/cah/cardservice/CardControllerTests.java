package com.jellehuibregtse.cah.cardservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jellehuibregtse.cah.cardservice.models.Card;
import com.jellehuibregtse.cah.cardservice.models.CardType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
@Transactional
class CardControllerTests {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    private static final String BASE_URL = "/card";

    @Autowired
    private MockMvc mvc;

    @Test
    public void getCardsTest() throws Exception {
        this.mvc.perform(get(BASE_URL + "/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"cardType\":\"BLACK\",\"cardText\":\"card-text-for-testing\"},{\"id\":2,\"cardType\":\"WHITE\",\"cardText\":\"card-text-for-testing\"}]"));
    }

    @Test
    public void getCardTest() throws Exception {
        this.mvc.perform(get(BASE_URL + "/get?cardId=1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"cardType\":\"BLACK\",\"cardText\":\"card-text-for-testing\"}"));
    }

    @Test
    public void getNonExistentCardTest() throws Exception {
        this.mvc.perform(get(BASE_URL + "/get?cardId=-1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":0,\"cardType\":null,\"cardText\":null}"));
    }

    // https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
    @Test
    public void addValidCardTest() throws Exception {
        String url = BASE_URL + "/add";
        Card card = new Card();
        card.setCardType(CardType.WHITE);
        card.setCardText("Card text");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(card);

        this.mvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void addCardEmptyPostRequestTest() throws Exception {
        String url = BASE_URL + "/add";
        this.mvc.perform(post(url).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }
}
