package org.mwie.game.board;

import org.junit.jupiter.api.Test;
import org.mwie.game.PlayerNumber;
import org.mwie.game.board.elements.StandardPit;
import org.mwie.game.board.elements.Store;

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

    @Test
    void shouldHaveOneStorePerPlayer() {
        Board board = new Board.BoardBuilder().withStores().build();
        var stores = board.getStores().stream().collect(Collectors.groupingBy(Store::getOwner));
        assertEquals(1, stores.get(PlayerNumber.ONE).size());
        assertEquals(1, stores.get(PlayerNumber.TWO).size());
    }
}
