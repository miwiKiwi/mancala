package org.mwie.game.board;

import org.junit.jupiter.api.Test;
import org.mwie.game.PlayerNumber;
import org.mwie.game.board.elements.StandardPit;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    @Test
    void shouldHaveSixPitsPerPlayer() {
        Board board = new Board.BoardBuilder().withPits(6).build();
        var pits = board.getPits().stream().collect(Collectors.groupingBy(StandardPit::getOwner));
        assertEquals(6, pits.get(PlayerNumber.ONE).size());
        assertEquals(6, pits.get(PlayerNumber.TWO).size());
    }
}
