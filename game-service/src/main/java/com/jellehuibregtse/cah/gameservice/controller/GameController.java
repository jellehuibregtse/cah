package com.jellehuibregtse.cah.gameservice.controller;

import com.jellehuibregtse.cah.gameservice.model.Game;
import com.jellehuibregtse.cah.gameservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The game controller.
 *
 * @author Jelle Huibregtse
 */
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<String> startGame(@RequestBody Game game) {
        gameService.startGame(game);
        var id = gameService.getGameId(game);

        return ResponseEntity.ok(String.format("Game with id %s has started", id));
    }
}