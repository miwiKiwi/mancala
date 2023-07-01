package org.mwie.game.board.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PitTest {

    @Test
    void shouldStoreStonesInPit() {
        Pit pit = new StandardPit();
        assertEquals(6, pit.countStones());
    }

    @Test
    void shouldBeAbleToTakeStonesFromPit() {
        StandardPit pit = new StandardPit();
        var taken = pit.takeStones();
        assertEquals(0, pit.countStones());
        assertEquals(6, taken);
    }

    @Test
    void shouldBeAbleToSowStoneInPit() {
        StandardPit pit = new StandardPit();
        pit.sow();
        assertEquals(7, pit.countStones());
    }


    //store tests
    @Test
    void shouldInitializeStoreWithNoStones() {
        Store store = new Store();
        assertEquals(0, store.countStones());
    }

    @Test
    void shouldAcceptMoreThanOneStone() {
        Store store = new Store();
        store.putStones(5);
        assertEquals(5, store.countStones());
    }

    @Test
    void shouldBeAbleToSowManyStones() {
        Store store = new Store();
        store.sow(4);
        assertEquals(4, store.countStones());
    }

}
