package com.jellehuibregtse.cah.gameservice.service;

import com.jellehuibregtse.cah.gameservice.model.Game;
import com.jellehuibregtse.cah.gameservice.model.Round;
import com.jellehuibregtse.cah.gameservice.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImplementation(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void startGame(@NotNull Game game) {
        gameRepository.save(game);
    }

    @Override
    public List<Game> getGames() {
        return (List<Game>) gameRepository.findAll();
    }

    @Override
    public Game getGame(long id) {
        return gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game not found"));
    }

    @Override
    public Long getGameId(Game game) {
        return gameRepository.findIdByGame(game);

    }

    @Override
    public Round newRound(long gameId) {
        var round = new Round();

        var game = getGame(gameId);

        game.getRounds().add(round);

        gameRepository.save(game);

        return round;
    }
}
