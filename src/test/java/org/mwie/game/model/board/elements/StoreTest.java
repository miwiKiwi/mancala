package org.mwie.game.model.board.elements;

import org.junit.jupiter.api.Test;
import org.mwie.game.model.player.PlayerNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StoreTest {

    @Test
    void shouldInitializeStoreWithNoStones() {
        Store store = new Store(PlayerNumber.ONE);
        assertEquals(0, store.getStones());
    }

    @Test
    void shouldAcceptMoreThanOneStone() {
        Store store = new Store(PlayerNumber.ONE);
        store.putStones(5);
        assertEquals(5, store.getStones());
    }

    @Test
    void shouldBeAbleToSowManyStones() {
        Store store = new Store(PlayerNumber.ONE);
        store.add(4);
        assertEquals(4, store.getStones());
    }
}
