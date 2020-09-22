package com.jellehuibregtse.cah.cardservice.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private CardType cardType;

    @NotNull
    private String cardText;

    public Card() {
    }

    public Card(@NotNull CardType cardType, @NotNull String cardText) {
        this.cardType = cardType;
        this.cardText = cardText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }
}
