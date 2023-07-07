package org.mwie.game.model.board.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mwie.game.model.player.PlayerNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PitTest {

    private StandardPit pitOne;
    private StandardPit pitTwo;

    @BeforeEach
    void setup() {
        pitOne = new StandardPit(PlayerNumber.ONE, 6);
        pitTwo = new StandardPit(PlayerNumber.TWO, 6);
    }

    @Test
    void shouldStoreStonesInPit() {
        assertEquals(6, pitOne.getStones());
    }

    @Test
    void shouldBeAbleToTakeAllStonesFromPit() {
        var taken = pitOne.takeOutStones();
        assertEquals(0, pitOne.getStones());
        assertEquals(6, taken);
    }

    @Test
    void shouldBeAbleToSowStoneInPit() {
        pitOne.putStone();
        assertEquals(7, pitOne.getStones());
    }

    @Test
    void shouldHaveNext() {
        Store store = new Store(PlayerNumber.ONE);

        pitOne.setNext(store);
        store.setNext(pitTwo);
        pitTwo.setNext(pitOne);

        assertEquals(store, pitOne.next());
        assertEquals(pitTwo, store.next());
        assertEquals(pitOne, pitTwo.next());
    }

}
