package com.jellehuibregtse.cah.cardservice.controllers;

import com.jellehuibregtse.cah.cardservice.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CardController {

    @Autowired
    private CardRepository cardRepository;
}
