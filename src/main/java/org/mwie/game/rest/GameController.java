package org.mwie.game.rest;

import lombok.RequiredArgsConstructor;
import org.mwie.game.services.GameService;
import org.mwie.game.services.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final PlayerService playerService;

    @GetMapping
    public String createGame() {
        //TODO: implement me!
        return gameService.createGame();
    }

    @PutMapping("/pit/{pitNumber}")
    public void takeTurn(@PathVariable int pitNumber) {
        playerService.takeTurn(pitNumber);
        //TODO: implement me!
    }

    @PutMapping("/reset")
    public void resetGame() {
        //TODO: implement me!
    }

}
