package com.jellehuibregtse.cah.gameservice.repository;

import com.jellehuibregtse.cah.gameservice.model.Game;
import org.springframework.data.repository.CrudRepository;

/**
 * The repository for getting games from the database.
 *
 * @author Jelle Huibregtse
 */
public interface GameRepository extends CrudRepository<Game, Long> {}