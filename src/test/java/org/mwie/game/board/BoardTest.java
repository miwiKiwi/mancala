package org.mwie.game.board;

import org.junit.jupiter.api.Test;
import org.mwie.game.board.elements.Pit;
import org.mwie.game.board.elements.StandardPit;
import org.mwie.game.board.elements.Store;
import org.mwie.game.player.PlayerNumber;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    @Test
    void shouldHaveSixPitsPerPlayer() {
        Board board = new Board.BoardBuilder().create(6).build();
        var pits = board.getPits().stream().collect(Collectors.groupingBy(StandardPit::getOwner));
        assertEquals(6, pits.get(PlayerNumber.ONE).size());
        assertEquals(6, pits.get(PlayerNumber.TWO).size());
    }

    @Test
    void shouldHaveOneStorePerPlayer() {
        Board board = new Board.BoardBuilder().create(6).build();
        var stores = board.getStores().stream().collect(Collectors.groupingBy(Store::getOwner));
        assertEquals(1, stores.get(PlayerNumber.ONE).size());
        assertEquals(1, stores.get(PlayerNumber.TWO).size());
    }

    @Test
    void shouldHavePitsInCycle() {
        Board board = new Board.BoardBuilder().create(6).build();
        var storeOne = board.getPits().get(0);
        Pit pit = storeOne;
        Set<Pit> allPits = new HashSet<>();
        allPits.add(pit);

        for (int i=0; i< 14; i++) {
            pit = pit.next();
            allPits.add(pit);
        }

        assertEquals(14, allPits.size());
        assertEquals(storeOne, pit);
    }

    @Test
    void pitsShouldHaveOpposites() {
        Board board = new Board.BoardBuilder().create(6).build();
        var players = board.getPlayers();
        var pitsOne = players.playerOne().pits();
        var pitsTwo = players.playerTwo().pits();

        assertEquals(pitsOne.get(0).getOpposite(), pitsTwo.get(5));
        assertEquals(pitsOne.get(1).getOpposite(), pitsTwo.get(4));
        assertEquals(pitsOne.get(2).getOpposite(), pitsTwo.get(3));
        assertEquals(pitsOne.get(3).getOpposite(), pitsTwo.get(2));
        assertEquals(pitsOne.get(4).getOpposite(), pitsTwo.get(1));
        assertEquals(pitsOne.get(5).getOpposite(), pitsTwo.get(0));

        assertEquals(pitsTwo.get(0).getOpposite(), pitsOne.get(5));
        assertEquals(pitsTwo.get(1).getOpposite(), pitsOne.get(4));
        assertEquals(pitsTwo.get(2).getOpposite(), pitsOne.get(3));
        assertEquals(pitsTwo.get(3).getOpposite(), pitsOne.get(2));
        assertEquals(pitsTwo.get(4).getOpposite(), pitsOne.get(1));
        assertEquals(pitsTwo.get(5).getOpposite(), pitsOne.get(0));
    }
}
