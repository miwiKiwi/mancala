package org.mwie.game;

import org.junit.jupiter.api.Test;
import org.mwie.game.model.Game;
import org.mwie.game.model.board.Board;
import org.mwie.game.model.player.PlayerNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mwie.game.model.player.PlayerNumber.ONE;

class GameTest {

    @Test
    void playerOneShouldBegin() {
        Game game = Game.create(Board.create(6, 6));
        PlayerNumber activePlayer = game.getActivePlayer();
        assertEquals(ONE, activePlayer);
    }

}
