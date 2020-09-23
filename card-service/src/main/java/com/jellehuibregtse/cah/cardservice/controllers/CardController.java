package com.jellehuibregtse.cah.cardservice.controllers;

import com.jellehuibregtse.cah.cardservice.models.Card;
import com.jellehuibregtse.cah.cardservice.models.CardType;
import com.jellehuibregtse.cah.cardservice.repositories.CardRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @CrossOrigin
    @RequestMapping("/get/{cardId}")
    public Card getCatalog(@PathVariable("cardId") String cardId) {
        return new Card(CardType.BLACK, cardId);
    }
}
