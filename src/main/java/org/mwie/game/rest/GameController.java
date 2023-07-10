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

    @PostMapping("/create")
    @ResponseBody
    public Game createGame() {
        return gameService.createGame(6, 6);
    }

    @PutMapping("/turn/{playerNumber}/pit/{pitNumber}")
    @ResponseBody
    public Game takeTurn(@PathVariable PlayerNumber playerNumber, @PathVariable int pitNumber) {
        return gameService.makeAMove(playerNumber, pitNumber);
    }

    @GetMapping
    public String welcome(Model model) {
        Game game = gameService.getGame();
        if (game == null) {
            game = gameService.createGame(6, 6);
        }
        model.addAttribute("game", game);
        return "index";
    }
}
