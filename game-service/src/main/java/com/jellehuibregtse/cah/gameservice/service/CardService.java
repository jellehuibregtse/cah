package com.jellehuibregtse.cah.gameservice.service;

import com.jellehuibregtse.cah.gameservice.model.Card;
import com.jellehuibregtse.cah.gameservice.model.CardListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class CardService {

    private final RestTemplate restTemplate;
    private static final String SERVICE_URL = "http://card-service/";

    @Autowired
    public CardService(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Card getNewBlackCard() {
        return restTemplate.getForObject(SERVICE_URL + "/cards/newBlackCard", Card.class);
    }

    public List<Card> getNewWhiteCard(int amount) {
        if (amount <= 0) {
            return Collections.emptyList();
        }

        return Objects.requireNonNull(restTemplate.getForObject(SERVICE_URL + "/cards/newWhiteCard/{amount}",
                                                                CardListDto.class)).getCards();
    }
}
