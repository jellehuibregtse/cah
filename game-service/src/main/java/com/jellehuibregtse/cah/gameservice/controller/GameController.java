package com.jellehuibregtse.cah.gameservice.controller;

import com.jellehuibregtse.cah.gameservice.model.Game;
import com.jellehuibregtse.cah.gameservice.service.GameServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final GameServiceImplementation gameService;

    @Autowired
    public GameController(GameServiceImplementation gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<Game> startGame() {
        return ResponseEntity.ok(new Game());
    }
}