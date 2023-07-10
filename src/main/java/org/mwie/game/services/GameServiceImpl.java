package org.mwie.game.services;

import lombok.RequiredArgsConstructor;
import org.mwie.game.model.Game;
import org.mwie.game.model.board.Board;
import org.mwie.game.model.board.elements.Pit;
import org.mwie.game.model.board.elements.Store;
import org.mwie.game.model.player.Player;
import org.mwie.game.model.player.PlayerNumber;

import static org.mwie.game.model.player.PlayerNumber.ONE;
import static org.mwie.game.model.player.PlayerNumber.TWO;

@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private Game game;

    private final PlayerService playerService;

    @Override
    public Game createGame(int pitsPerPlayer, int numberOfStones) {
        game = Game.create(Board.create(pitsPerPlayer, numberOfStones));
        return game;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public Game makeAMove(PlayerNumber playerNumber, int pitIndex) {
        if(canTakeTurn(playerNumber)) {
            var player = getPlayer(playerNumber);
            var landed = playerService.takeTurn(player, pitIndex);
            finishPlayersTurn(landed);
        } else {
            throw new IllegalArgumentException("It's not your turn");
        }
        return game;
    }

    private boolean canTakeTurn(PlayerNumber playerNumber) {
        var activePlayer = game.getActivePlayer();
        return activePlayer.equals(playerNumber);
    }

    public void finishPlayersTurn(Pit landed) {
        if (gameIsFinished()) {
            moveStonesToStores();
            declareWinner();
        } else {
            setActivePlayer(landed);
        }
    }

    private void setActivePlayer(Pit landed) {
        if (finishedInStore(landed)) {
            game.setActivePlayer(game.getActivePlayer());
        } else {
            switch (game.getActivePlayer()) {
                case ONE -> game.setActivePlayer(TWO);
                case TWO -> game.setActivePlayer(ONE);
            }
        }
    }

    private boolean finishedInStore(Pit landed) {
        return landed instanceof Store && landed.getOwner().equals(game.getActivePlayer());
    }

    private void declareWinner() {
        var stonesOne = game.getBoard().getPlayers().get(ONE).store().getStones();
        var stonesTwo = game.getBoard().getPlayers().get(TWO).store().getStones();
        if(stonesOne > stonesTwo) {
            game.setStatus(Game.Status.PLAYER_ONE_WIN);
        } else if (stonesOne == stonesTwo) {
            game.setStatus(Game.Status.DRAW);
        } else {
            game.setStatus(Game.Status.PLAYER_TWO_WIN);
        }
    }

    private void moveStonesToStores() {
        game.getBoard().getPlayers().forEach((number, player) ->
            player.pits().forEach(pit -> {
                int stones = pit.takeOutStones();
                player.store().putStones(stones);
            })
        );
    }

    private boolean gameIsFinished() {
        return !playerService.hasAnyStones(game.getBoard().getPlayers().get(ONE))
                || !playerService.hasAnyStones(game.getBoard().getPlayers().get(TWO));
    }

    private Player getPlayer(PlayerNumber playerNumber) {
        return game.getBoard().getPlayers().get(playerNumber);
    }
}
