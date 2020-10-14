package com.jellehuibregtse.cah.cardservice.controller;

import com.jellehuibregtse.cah.cardservice.model.Card;
import com.jellehuibregtse.cah.cardservice.repository.CardRepository;
import com.jellehuibregtse.cah.cardservice.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardRepository cardRepository;
    private final ICardService cardService;

    @Autowired
    public CardController(CardRepository cardRepository, ICardService cardService) {
        this.cardRepository = cardRepository;
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getCards() {
        return cardService.findAllCards();
    }

    @GetMapping("{id}")
    public ResponseEntity<Card> getCard(@PathVariable long id) {
        return cardService.findCard(id);
    }

    @PostMapping
    public ResponseEntity<String> createCard(@RequestBody Card card) {
        return cardService.addCard(card);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCard(@RequestBody Card updatedCard, @PathVariable long id) {
        return cardService.updateCard(updatedCard, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCard(@PathVariable long id) {
        return cardService.deleteCard(id);
    }
}
