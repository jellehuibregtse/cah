package com.jellehuibregtse.cah.gameservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CardListDto {

    private List<Card> cards;
}
