package com.jellehuibregtse.cah.gameservice.service;

import com.jellehuibregtse.cah.gameservice.model.Game;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The game service interface defining it's functionality.
 *
 * @author Jelle Huibregtse
 */
public interface GameService {

    void startGame(@NotNull Game game);

    List<Game> getGames();

    Game getGame(long id);
}
