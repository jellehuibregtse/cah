package com.jellehuibregtse.cah.cardservice.controllers;

import com.google.common.collect.Lists;
import com.jellehuibregtse.cah.cardservice.models.Card;
import com.jellehuibregtse.cah.cardservice.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getCards() {
        return ResponseEntity.ok(Lists.newArrayList(cardRepository.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<Card> getCard(@PathVariable long id) {
        return ResponseEntity.ok(cardRepository.findById(id)
                                               .orElseThrow(() -> new ResourceNotFoundException(String.format(
                                                       "Card with id %s not found.",
                                                       id))));
    }

    @PostMapping
    public ResponseEntity<String> createCard(@RequestBody Card card) {
        cardRepository.save(card);

        return ResponseEntity.ok(String.format("A %s card with the text %s has been successfully created.",
                                               card.getCardType(),
                                               card.getCardText()));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCard(@RequestBody Card updatedCard, @PathVariable long id) {
        cardRepository.findById(id).map(card -> {
            card.setCardText(updatedCard.getCardText());
            card.setCardType(updatedCard.getCardType());
            return cardRepository.save(card);
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("Card with id %s not found.", id)));

        return ResponseEntity.ok(String.format("Card %s with the text %s and type %s has been successfully updated.",
                                               id,
                                               updatedCard.getCardText(),
                                               updatedCard.getCardText()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCard(@PathVariable long id) {
        cardRepository.deleteById(id);

        return ResponseEntity.ok(String.format("Card with id %s has been successfully deleted.", id));
    }
}
