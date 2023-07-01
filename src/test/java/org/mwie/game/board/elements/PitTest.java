package org.mwie.game.board.elements;

import org.junit.jupiter.api.Test;
import org.mwie.game.board.PlayerNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PitTest {

    @Test
    void shouldStoreStonesInPit() {
        Pit pit = new StandardPit(PlayerNumber.ONE);
        assertEquals(6, pit.countStones());
    }

    @Test
    void shouldBeAbleToTakeStonesFromPit() {
        StandardPit pit = new StandardPit(PlayerNumber.ONE);
        var taken = pit.takeStones();
        assertEquals(0, pit.countStones());
        assertEquals(6, taken);
    }

    @Test
    void shouldBeAbleToSowStoneInPit() {
        StandardPit pit = new StandardPit(PlayerNumber.ONE);
        pit.sow();
        assertEquals(7, pit.countStones());
    }

}
