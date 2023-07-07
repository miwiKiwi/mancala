package org.mwie.game.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mwie.game.model.player.PlayerNumber;
import org.mwie.game.services.GameServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    private GameServiceImpl gameService;

    @BeforeEach
    void setup() {
        gameService.createGame();
    }


    @Test
    void shouldRejectInactivePlayersTurn() {
        assertThrows(IllegalArgumentException.class, () -> gameService.makeAMove(PlayerNumber.TWO, 1));
    }
}
