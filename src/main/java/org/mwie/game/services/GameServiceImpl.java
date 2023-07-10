package org.mwie.game.services;

import lombok.RequiredArgsConstructor;
import org.mwie.game.model.Game;
import org.mwie.game.model.board.Board;
import org.mwie.game.model.player.Player;
import org.mwie.game.model.player.PlayerNumber;

import static org.mwie.game.model.player.PlayerNumber.ONE;
import static org.mwie.game.model.player.PlayerNumber.TWO;

@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private Game game;

    private final PlayerService playerService;

    @Override
    public Game createGame() {
        game = Game.create(Board.create(6, 6));
        return game;
    }

    @Override
    public Game makeAMove(PlayerNumber playerNumber, int pitIndex) {
        if(canTakeTurn(playerNumber)) {
            var player = getPlayer(playerNumber);
            playerService.takeTurn(player, pitIndex);
            finishTurn();
        } else {
            //TODO: add warnings to game
            throw new IllegalArgumentException("It's not your turn");
        }
        return game;
    }

    private boolean canTakeTurn(PlayerNumber playerNumber) {
        var activePlayer = game.getActivePlayer();
        return activePlayer.equals(playerNumber);
    }

    public void finishTurn() {
        //TODO: check for end game conditions
        switch (game.getActivePlayer()) {
            case ONE -> game.setActivePlayer(TWO);
            case TWO -> game.setActivePlayer(ONE);
        }
    }

    private Player getPlayer(PlayerNumber playerNumber) {
        return game.getBoard().getPlayers().get(playerNumber);
    }
}
