package org.mwie.game.board;

import org.junit.jupiter.api.Test;
import org.mwie.game.PlayerNumber;

class PlayerTest {

    @Test
    void shouldBeCreated() {
        Player player = new Player(PlayerNumber.ONE);
    }
}
