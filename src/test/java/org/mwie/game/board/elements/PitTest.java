package org.mwie.game.board.elements;

import org.junit.jupiter.api.Test;
import org.mwie.game.player.PlayerNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PitTest {

    @Test
    void shouldStoreStonesInPit() {
        Pit pit = new StandardPit(PlayerNumber.ONE);
        assertEquals(6, pit.getStones());
    }

    @Test
    void shouldBeAbleToTakeAllStonesFromPit() {
        StandardPit pit = new StandardPit(PlayerNumber.ONE);
        var taken = pit.takeStones();
        assertEquals(0, pit.getStones());
        assertEquals(6, taken);
    }

    @Test
    void shouldBeAbleToSowStoneInPit() {
        StandardPit pit = new StandardPit(PlayerNumber.ONE);
        pit.putStone();
        assertEquals(7, pit.getStones());
    }

    @Test
    void shouldHaveNext() {
        StandardPit one = new StandardPit(PlayerNumber.ONE);
        Store store = new Store(PlayerNumber.ONE);
        StandardPit two = new StandardPit(PlayerNumber.ONE);

        one.setNext(store);
        store.setNext(two);
        two.setNext(one);

        assertEquals(store, one.next());
        assertEquals(two, store.next());
        assertEquals(one, two.next());
    }

}
