package com.jellehuibregtse.cah.gameservice.service;

import com.jellehuibregtse.cah.gameservice.model.Game;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * An implementation of the web socket service.
 *
 * @author Jelle Huibregtse
 */
@Service
public class GameServiceImplementation implements GameService {

    @Override
    public void startGame(@NotNull Game game) {

    }

    @Override
    public List<Game> getGames() {
        return null;
    }

    @Override
    public Game getGame(long id) {
        return null;
    }
}
