package com.jellehuibregtse.cah.cardservice.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardTests {

    private Card validCard;
    private Card validCardWithSameValues;
    private Card nullCard;
    private String notEvenACard;

    @BeforeEach
    public void setup() {
        validCard = new Card(CardType.BLACK, "text");
        validCardWithSameValues = new Card(CardType.BLACK, "text");
        nullCard = null;
        notEvenACard = "text";
    }

    @Test
    public void cardEqualsWithSameObject() {
        Assert.assertEquals(validCard, validCard);
    }

    @Test
    public void cardEqualsWithSameValues() {
        Assert.assertEquals(validCard, validCardWithSameValues);
    }

    @Test
    public void cardNotEqualsToNullCard() {
        Assert.assertNotEquals(validCard, nullCard);
    }

    @Test
    public void cardNotEqualsToNotEvenACard() {
        Assert.assertNotEquals(validCard, notEvenACard);
    }
}
