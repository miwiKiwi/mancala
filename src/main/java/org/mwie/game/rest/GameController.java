package org.mwie.game.rest;

import lombok.RequiredArgsConstructor;
import org.mwie.game.player.PlayerNumber;
import org.mwie.game.services.GameService;
import org.mwie.game.services.PlayerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final PlayerService playerService;

    @PostMapping
    public String createGame() {
        //TODO: implement me!
        return gameService.createGame();
    }

    @PutMapping("turn/{playerNumber}/pit/{pitNumber}")
    public void takeTurn(@PathVariable PlayerNumber playerNumber, @PathVariable int pitNumber) {
        var player = gameService.getPlayer(playerNumber);
        playerService.takeTurn(player,pitNumber);
        //TODO: return game state after player's turn
    }

}
