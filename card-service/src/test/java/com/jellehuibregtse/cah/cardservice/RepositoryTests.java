package com.jellehuibregtse.cah.cardservice;

import com.google.common.collect.Lists;
import com.jellehuibregtse.cah.cardservice.models.Card;
import com.jellehuibregtse.cah.cardservice.models.CardType;
import com.jellehuibregtse.cah.cardservice.repositories.CardRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;

@DataJpaTest
@ActiveProfiles("tests")
public class RepositoryTests {

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

    @Test
    public void amountOfCardsInDatabase() {
        Assert.assertEquals(2, cardRepository.findAll().size());
    }

    @Test
    public void oneOfEachCardInDatabase() {
        var cardOne = cardRepository.findById(1L);
        var cardTwo = cardRepository.findById(2L);

        Assert.assertTrue(cardOne.isPresent());
        Assert.assertTrue(cardTwo.isPresent());
        Assert.assertEquals(CardType.BLACK, cardOne.get().getCardType());
        Assert.assertEquals(CardType.WHITE, cardTwo.get().getCardType());
        Assert.assertEquals("This is text on the black card.", cardOne.get().getCardText());
        Assert.assertEquals("This is text on the white card.", cardTwo.get().getCardText());
    }

    @Test
    public void cardWithIdThreeDoesNotExistInDatabase() {
        var cardThree = cardRepository.findById(3L);

        Assert.assertFalse(cardThree.isPresent());
    }

    @Test
    public void cardWithNegativeIdDoesNotExistInDatabase() {
        var cardMinusOne = cardRepository.findById(-1L);

        Assert.assertFalse(cardMinusOne.isPresent());
    }

    @Test
    public void addingCardDoesReturnFromDatabase() {
        var card = new Card(CardType.WHITE, "Another card with text.");

        cardRepository.save(card);
        var cardFromDatabase = cardRepository.findById(card.getId());
        Assert.assertTrue(cardFromDatabase.isPresent());
        Assert.assertEquals(CardType.WHITE, cardFromDatabase.get().getCardType());
        Assert.assertEquals("Another card with text.", cardFromDatabase.get().getCardText());
        Assert.assertEquals(card, cardFromDatabase.get());
    }

    private void persistAll(Collection<?> entities) {
        for (var entity : entities) {
            entityManager.persist(entity);
        }
    }
}
