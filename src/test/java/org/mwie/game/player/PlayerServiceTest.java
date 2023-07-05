package org.mwie.game.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mwie.game.board.Board;
import org.mwie.game.services.PlayerServiceImpl;

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
        board = new Board.BoardBuilder().create(3, 6).build();
        one = board.getPlayers().get(PlayerNumber.ONE);
        two = board.getPlayers().get(PlayerNumber.TWO);
    }

//    private Player setupPlayer(PlayerNumber playerNumber) {
//        StandardPit first = new StandardPit(playerNumber, 6);
//        StandardPit second = new StandardPit(playerNumber, 6);
//        StandardPit third = new StandardPit(playerNumber, 6);
//        Store store = new Store(playerNumber);
//
//        first.setNext(second);
//        second.setNext(third);
//        third.setNext(store);
//
//        return new Player(
//                playerNumber,
//                Arrays.asList(first, second, third, fourth, fifth, sixth),
//                store);
//    }

    @Test
    void shouldBeAbleToMoveStones() {
        playerService.takeTurn(one, 1);
        assertEquals(0, one.getStandardPit(1).getStones());
        assertEquals(7, one.getStandardPit(2).getStones());
        assertEquals(7, one.getStandardPit(3).getStones());
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
        var result = playerService.takeTurn(one, 3);
        //Pit result = playerService.takeTurn(one, 3);

        assertEquals(one.getStandardPit(2), result);
        assertEquals(7, one.getStandardPit(1).getStones());
        assertEquals(7, one.getStandardPit(2).getStones());
        assertEquals(0, one.getStandardPit(3).getStones());
        assertEquals(1, one.store().getStones());
        assertEquals(0, two.store().getStones());
    }

    @Test
    void shouldCaptureStonesFromOppositePit() {
        playerService.takeTurn(one, 1);
        var result = playerService.takeTurn(one, 2);

        assertEquals(0, result.getStones());
        assertEquals(11, one.store().getStones());
    }
}
