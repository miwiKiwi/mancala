package org.mwie.game.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mwie.game.board.Board;
import org.mwie.game.board.elements.Pit;
import org.mwie.game.board.elements.StandardPit;
import org.mwie.game.board.elements.Store;
import org.mwie.game.services.PlayerServiceImpl;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @InjectMocks
    private PlayerServiceImpl playerService;

    private Board board;

    private Player one;
    private Player two;

    @BeforeEach
    void setup() {
        board = new Board.BoardBuilder().create(6).build();
        one = board.getPlayers().get(PlayerNumber.ONE);
        two = board.getPlayers().get(PlayerNumber.TWO);
    }

    private Player setupPlayer(PlayerNumber playerNumber) {
        StandardPit first = new StandardPit(playerNumber);
        StandardPit second = new StandardPit(playerNumber);
        StandardPit third = new StandardPit(playerNumber);
        StandardPit fourth = new StandardPit(playerNumber);
        StandardPit fifth = new StandardPit(playerNumber);
        StandardPit sixth = new StandardPit(playerNumber);
        Store store = new Store(playerNumber);

        first.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        fourth.setNext(fifth);
        fifth.setNext(sixth);
        sixth.setNext(store);

        return new Player(
                playerNumber,
                Arrays.asList(first, second, third, fourth, fifth, sixth),
                store);
    }

    @Test
    void shouldBeAbleToMoveStones() {
        playerService.takeTurn(one, 1);
        assertEquals(0, one.getStandardPit(1).getStones());
        assertEquals(7, one.getStandardPit(2).getStones());
        assertEquals(7, one.getStandardPit(3).getStones());
        assertEquals(7, one.getStandardPit(4).getStones());
        assertEquals(7, one.getStandardPit(5).getStones());
        assertEquals(7, one.getStandardPit(6).getStones());
        assertEquals(1, one.store().getStones());
    }

    @Test
    void shouldThrowExceptionWhenEmptyPitChosen() {
        playerService.takeTurn(one, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            playerService.takeTurn(one, 1);
        });
    }

    @Test
    void shouldSkipOpponentStore() {
        //TODO: make standard pit take initial number of stones as argument in constructor
        // and fix tests here
        playerService.takeTurn(one, 1);
        playerService.takeTurn(two, 2);
        playerService.takeTurn(one, 3);
        Pit result = playerService.takeTurn(one, 6);

        assertEquals(one.getStandardPit(1), result);
        assertEquals(1, one.getStandardPit(1).getStones());
        assertEquals(3, one.store().getStones());
        assertEquals(0, two.store().getStones());
    }

    @Test
    void shouldCaptureStonesFromOppositePit() {
        playerService.takeTurn(one, 1);
        playerService.takeTurn(one, 2);
        Pit result = playerService.takeTurn(one, 6);

        assertEquals(0, result.getStones());
        assertEquals(11, one.store().getStones());
    }
}
