package com.jellehuibregtse.cah.cardservice.service;

import com.google.common.collect.Lists;
import com.jellehuibregtse.cah.cardservice.model.Card;
import com.jellehuibregtse.cah.cardservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService implements ICardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public ResponseEntity<List<Card>> findAllCards() {
        return ResponseEntity.ok(Lists.newArrayList(cardRepository.findAll()));
    }

    @Override
    public ResponseEntity<Card> findCard(long id) {
        return ResponseEntity.ok(cardRepository.findById(id)
                                               .orElseThrow(() -> new ResourceNotFoundException(String.format(
                                                       "Card with id %s not found.",
                                                       id))));
    }

    @Override
    public ResponseEntity<String> addCard(Card card) {
        cardRepository.save(card);

        return ResponseEntity.ok(String.format("A %s card with the text %s has been successfully created.",
                      card.getCardType(),
                      card.getCardText()));
    }

    @Override
    public void updateCard() {

    }

    @Override
    public void deleteCard() {

    }
}
