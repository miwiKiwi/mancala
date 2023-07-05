package org.mwie.game.services;

import lombok.RequiredArgsConstructor;
import org.mwie.game.Game;
import org.mwie.game.model.player.Player;
import org.mwie.game.model.player.PlayerNumber;

@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private Game game;

    @Override
    public String createGame() {
        return "New game created!";
    }

    @Override
    public Player getPlayer(PlayerNumber playerNumber) {
        var player = game.getBoard().getPlayers().get(playerNumber);
        var activePlayer = game.getActivePlayer();
        if(player.equals(activePlayer)) {
            return player;
        } else {
            return null; //TODO: add exception
        }
    }
}
