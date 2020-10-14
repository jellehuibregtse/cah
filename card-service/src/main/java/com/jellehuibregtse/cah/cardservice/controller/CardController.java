package com.jellehuibregtse.cah.cardservice.controller;

import com.jellehuibregtse.cah.cardservice.model.Card;
import com.jellehuibregtse.cah.cardservice.repository.CardRepository;
import com.jellehuibregtse.cah.cardservice.service.CardService;
import com.jellehuibregtse.cah.cardservice.service.ICardService;
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
