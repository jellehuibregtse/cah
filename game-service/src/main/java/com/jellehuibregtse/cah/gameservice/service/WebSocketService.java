package com.jellehuibregtse.cah.gameservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jellehuibregtse.cah.gameservice.model.Game;

/**
 * The web socket service interface defining it's functionality.
 *
 * @author Jelle Huibregtse
 */
public interface WebSocketService {

    void sendGameData(Game game) throws JsonProcessingException;
}
