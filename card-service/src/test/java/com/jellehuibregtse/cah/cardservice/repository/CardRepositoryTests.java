package com.jellehuibregtse.cah.cardservice.repository;

import com.google.common.collect.Lists;
import com.jellehuibregtse.cah.cardservice.model.Card;
import com.jellehuibregtse.cah.cardservice.model.CardType;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

    @ParameterizedTest
    @ValueSource(longs = {3, Long.MIN_VALUE, Long.MAX_VALUE})
    public void getNonExistentCardFromDatabase_returnsEmptyOptional(long id) {
        var card = cardRepository.findById(id);

        Assert.assertFalse(card.isPresent());
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
