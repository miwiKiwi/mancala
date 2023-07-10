package org.mwie.game.model.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mwie.game.model.board.elements.Pit;
import org.mwie.game.model.player.Player;
import org.mwie.game.model.player.PlayerNumber;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    private Board board;
    private Map<PlayerNumber, Player> players;

    @BeforeEach
    void setup() {
        board = Board.create(6, 6);
        players = board.getPlayers();
    }

    @Test
    void shouldHaveSixPitsPerPlayer() {
        Map<PlayerNumber, Player> players = board.getPlayers();
        assertEquals(6, players.get(PlayerNumber.ONE).pits().size());
        assertEquals(6, players.get(PlayerNumber.TWO).pits().size());
    }

    @Test
    void shouldHavePitsInCycle() {
        var firstPit = board.getPlayers().get(PlayerNumber.ONE).pits().get(0);
        Pit pit = firstPit;
        Set<Pit> allPits = new HashSet<>();
        allPits.add(pit);

        for (int i=0; i< 14; i++) {
            pit = pit.next();
            allPits.add(pit);
        }

        assertEquals(14, allPits.size());
        assertEquals(firstPit, pit);
    }

    @Test
    void pitsShouldHaveOpposites() {
        var pitsOne = players.get(PlayerNumber.ONE).pits();
        var pitsTwo = players.get(PlayerNumber.TWO).pits();

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
