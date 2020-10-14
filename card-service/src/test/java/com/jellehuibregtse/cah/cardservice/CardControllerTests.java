package com.jellehuibregtse.cah.cardservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jellehuibregtse.cah.cardservice.model.Card;
import com.jellehuibregtse.cah.cardservice.model.CardType;
import com.jellehuibregtse.cah.cardservice.service.ICardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CardControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ICardService cardService;

    @BeforeEach
    public void setup() {
        var cardOne = new Card(CardType.WHITE, "Text on the white test card.");
        var cardTwo = new Card(CardType.BLACK, "Text on the black test card.");

        saveAll(Arrays.asList(cardOne, cardTwo));
    }

    @Test
    public void getAllCards_returnsStatus200_andAllCards() throws Exception {
        this.mvc.perform(get("/cards"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))

                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].cardType", is("WHITE")))
                .andExpect(jsonPath("$[0].cardText", is("Text on the white test card.")))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].cardType", is("BLACK")))
                .andExpect(jsonPath("$[1].cardText", is("Text on the black test card.")));
    }

    @Test
    public void getCard_returnsStatus200_andCard() throws Exception {
        this.mvc.perform(get("/cards/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.cardType", is("WHITE")))
                .andExpect(jsonPath("$.cardText", is("Text on the white test card.")));
    }

    @Test
    public void getNonExistentCard_returnsStatus404() throws Exception {
        this.mvc.perform(get("/cards/-1")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void addCard_returnsStatus200_andMessage() throws Exception {
        Card card = new Card(CardType.WHITE, "Text on another white test card");

        this.mvc.perform(post("/cards").contentType(APPLICATION_JSON).content(toJsonString(card)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(endsWith("has been successfully created.")));
    }

    @Test
    public void addCardWithEmptyBody_returnsStatus400() throws Exception {
        this.mvc.perform(post("/cards").contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private String toJsonString(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    private void saveAll(Iterable<Card> cards) {
        for (var card : cards) {
            cardService.addCard(card);
        }
    }
}
