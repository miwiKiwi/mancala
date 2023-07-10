package org.mwie.game.rest;

import lombok.RequiredArgsConstructor;
import org.mwie.game.model.Game;
import org.mwie.game.model.player.PlayerNumber;
import org.mwie.game.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class GameController {

    private final GameService gameService;
    private final GameMapper gameMapper;

    @PostMapping("/game")
    @ResponseBody
    public Game createGame() {
        return gameService.createGame();
    }

    @PutMapping("/turn/{playerNumber}/pit/{pitNumber}")
    @ResponseBody
    public void takeTurn(@PathVariable PlayerNumber playerNumber, @PathVariable int pitNumber) {
        gameService.makeAMove(playerNumber, pitNumber);
        //TODO: return game state after player's turn
    }

    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("game", gameMapper.mapResponse(gameService.createGame()));
        return "index";
    }
}
