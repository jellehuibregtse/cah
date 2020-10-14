package com.jellehuibregtse.cah.cardservice;

import com.google.common.collect.Lists;
import com.jellehuibregtse.cah.cardservice.model.Card;
import com.jellehuibregtse.cah.cardservice.model.CardType;
import com.jellehuibregtse.cah.cardservice.repository.CardRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;

@DataJpaTest
@ActiveProfiles("test")
public class CardRepositoryTests {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setup() {
        var blackCard = new Card(CardType.BLACK, "This is text on the black card.");
        var whiteCard = new Card(CardType.WHITE, "This is text on the white card.");
        persistAll(Lists.newArrayList(blackCard, whiteCard));
    }

    @AfterEach
    public void cleanup() {
        entityManager.clear();
    }

    @Test
    public void getAmountOfCardsInDatabase_isTwo() {
        Assert.assertEquals(2, Lists.newArrayList(cardRepository.findAll()).size());
    }

    @Test
    public void getTwoCardsFromDatabase_returnsBothCards() {
        var cards = Lists.newArrayList(cardRepository.findAll());
        var cardOne = cards.get(0);
        var cardTwo = cards.get(1);

        Assert.assertEquals(CardType.BLACK, cardOne.getCardType());
        Assert.assertEquals(CardType.WHITE, cardTwo.getCardType());
        Assert.assertEquals("This is text on the black card.", cardOne.getCardText());
        Assert.assertEquals("This is text on the white card.", cardTwo.getCardText());
    }

    @Test
    public void getNonExistentCardFromDatabase_returnsEmptyOptional() {
        var cardThree = cardRepository.findById(3L);

        Assert.assertFalse(cardThree.isPresent());
    }

    @Test
    public void getNonExistentCardNegativeFromDatabase_returnsEmptyOptional() {
        var cardMinusOne = cardRepository.findById(-1L);

        Assert.assertFalse(cardMinusOne.isPresent());
    }

    @Test
    public void addCardToDatabase_canRetrieveThatCardFromDatabase() {
        var card = new Card(CardType.WHITE, "Another card with text.");

        cardRepository.save(card);
        var cardFromDatabase = cardRepository.findById(card.getId());
        Assert.assertTrue(cardFromDatabase.isPresent());
        Assert.assertEquals(card, cardFromDatabase.get());
    }

    @Test
    public void removeAllCardsFromDatabase_amountOfCardsInDatabase_IsZero() {
        cardRepository.deleteAll();

        Assert.assertEquals(0, Lists.newArrayList(cardRepository.findAll()).size());
    }

    @Test
    public void removeOneCardFromDatabase_amountOfCardsInDatabase_IsOne() {
        cardRepository.deleteById(1L);

        Assert.assertEquals(1, Lists.newArrayList(cardRepository.findAll()).size());
    }

    private void persistAll(Collection<?> entities) {
        for (var entity : entities) {
            entityManager.persist(entity);
        }
    }
}
