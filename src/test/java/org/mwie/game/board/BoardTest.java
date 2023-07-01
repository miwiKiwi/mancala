package org.mwie.game.board;

import org.junit.jupiter.api.Test;
import org.mwie.game.board.elements.Pit;
import org.mwie.game.board.elements.StandardPit;
import org.mwie.game.board.elements.Store;
import org.mwie.game.board.player.Player;
import org.mwie.game.board.player.PlayerNumber;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    @Test
    void shouldHaveTwoPlayers() {
        Board board = new Board.BoardBuilder().create(6).build();
        var players = board.getPlayers().stream().collect(Collectors.groupingBy(Player::playerNumber));
        assertEquals(1, players.get(PlayerNumber.ONE).size());
        assertEquals(1, players.get(PlayerNumber.TWO).size());
    }

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
        //TODO: finish test
    }
}
