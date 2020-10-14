package com.jellehuibregtse.cah.cardservice.service;

import com.jellehuibregtse.cah.cardservice.model.Card;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICardService {

    ResponseEntity<List<Card>> findAllCards();

    ResponseEntity<Card> findCard(long id);

    ResponseEntity<String> addCard(Card card);

    ResponseEntity<String> updateCard(Card updatedCard, long id);

    void deleteCard();
}
