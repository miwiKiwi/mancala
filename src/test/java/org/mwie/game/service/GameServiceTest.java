package org.mwie.game.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mwie.game.model.Game;
import org.mwie.game.model.player.PlayerNumber;
import org.mwie.game.services.GameServiceImpl;
import org.mwie.game.services.PlayerServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    private GameServiceImpl gameService;
    @Mock
    private PlayerServiceImpl playerService;

    @Test
    void shouldRejectInactivePlayersTurn() {
        gameService.createGame(6, 6);
        assertThrows(IllegalArgumentException.class, () -> gameService.makeAMove(PlayerNumber.TWO, 1));
    }

    @Test
    void playerShouldGetAnotherTurnIfFinishedInStore() {
        gameService.createGame(3, 3);
        var players = gameService.getGame().getBoard().getPlayers();

        when(playerService.takeTurn(players.get(PlayerNumber.ONE), 1))
                .thenReturn(players.get(PlayerNumber.ONE).store());
        when(playerService.hasAnyStones(players.get(PlayerNumber.ONE)))
                .thenReturn(true);
        when(playerService.hasAnyStones(players.get(PlayerNumber.TWO)))
                .thenReturn(true);

        gameService.makeAMove(PlayerNumber.ONE, 1);

        assertEquals(Game.Status.ACTIVE, gameService.getGame().getStatus());
        assertEquals(PlayerNumber.ONE, gameService.getGame().getActivePlayer());
    }

    @Test
    void shouldEndGameWhenPlayerHasNoStones() {
        when(playerService.hasAnyStones(gameService.getGame().getBoard().getPlayers().get(PlayerNumber.ONE)))
                .thenReturn(false);

        gameService.createGame(2, 1);
        gameService.makeAMove(PlayerNumber.ONE, 1);

        assertEquals(Game.Status.DRAW, gameService.getGame().getStatus());
    }
}
