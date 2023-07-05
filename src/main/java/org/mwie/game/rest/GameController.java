package org.mwie.game.rest;

import lombok.RequiredArgsConstructor;
import org.mwie.game.model.player.Player;
import org.mwie.game.model.player.PlayerNumber;
import org.mwie.game.services.GameService;
import org.mwie.game.services.PlayerService;
import org.springframework.web.bind.annotation.*;

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
        Player player = gameService.getPlayer(playerNumber);
        playerService.takeTurn(player, pitNumber);
        //TODO: return game state after player's turn
    }

}
