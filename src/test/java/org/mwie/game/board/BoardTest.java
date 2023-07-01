package org.mwie.game.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    @Test
    void shouldHaveTwelvePits() {
        Board board = new Board.BoardBuilder().withPits(12).build();
        assertEquals(12, board.getPits().size());
    }
}
