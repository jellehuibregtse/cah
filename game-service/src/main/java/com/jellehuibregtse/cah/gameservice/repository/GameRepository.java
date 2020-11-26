package com.jellehuibregtse.cah.gameservice.repository;

import com.jellehuibregtse.cah.gameservice.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * The repository for getting games from the database.
 *
 * @author Jelle Huibregtse
 */
public interface GameRepository extends CrudRepository<Game, Long> {

    public Long findIdByGame(Game game);
}