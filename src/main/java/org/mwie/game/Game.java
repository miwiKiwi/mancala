package org.mwie.game;

import org.mwie.game.board.Board;
import org.mwie.game.player.Player;

public class Game {

    private Board board;
    private Status status;
    private Player activePlayer;

    public Board getBoard() {
        return board;
    }

    public Status getStatus() {
        return status;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    private enum Status {
        ACTIVE,
        PLAYER_ONE_WIN,
        PLAYER_TWO_WIN
    }
}
