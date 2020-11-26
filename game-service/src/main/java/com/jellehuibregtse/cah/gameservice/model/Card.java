package com.jellehuibregtse.cah.gameservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Card {

    private long id;

    private CardType cardType;

    private String cardText;
}
