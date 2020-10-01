package com.jellehuibregtse.cah.cardservice.controllers;

import com.jellehuibregtse.cah.cardservice.models.Card;
import com.jellehuibregtse.cah.cardservice.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/card")
@Transactional
public class CardController {

    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @CrossOrigin
    @GetMapping("/getAll")
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/get")
    public Card getCard(@RequestParam("cardId") long cardId) {
        var card = cardRepository.findById(cardId);

        return card.orElseGet(Card::new);
    }

    @CrossOrigin
    @PostMapping("/add")
    public Card addCard(@Valid @RequestBody Card card) {
        return cardRepository.save(card);
    }
}
