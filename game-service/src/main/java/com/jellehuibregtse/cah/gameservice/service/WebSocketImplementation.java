package com.jellehuibregtse.cah.gameservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jellehuibregtse.cah.gameservice.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * An implementation of the web socket service.
 *
 * @author Jelle Huibregtse
 */
@Service
public class WebSocketImplementation implements WebSocketService {

    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketImplementation(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public void sendGameData(Game game) throws JsonProcessingException {
        template.convertAndSend("/game/" + game.getId(), new ObjectMapper().writeValueAsString(game));
    }
}
