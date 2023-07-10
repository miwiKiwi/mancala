package org.mwie.game.model;

import org.mwie.game.model.board.Board;
import org.mwie.game.model.player.PlayerNumber;

import java.util.UUID;

public class Game {

    private String id;
    private Board board;
    private Status status;
    private PlayerNumber activePlayer;

    public static Game create(Board board) {
        Game game = new Game();
        game.id = UUID.randomUUID().toString();
        game.board = board;
        game.activePlayer = PlayerNumber.ONE;
        game.status = Status.ACTIVE;
        return game;
    }

    public String getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public Status getStatus() {
        return status;
    }

    public PlayerNumber getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(PlayerNumber activePlayer) {
        this.activePlayer = activePlayer;
    }

    private enum Status {
        ACTIVE,
        PLAYER_ONE_WIN,
        PLAYER_TWO_WIN
    }
}
