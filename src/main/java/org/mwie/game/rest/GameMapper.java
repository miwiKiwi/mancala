package org.mwie.game.rest;

import org.mwie.game.model.Game;
import org.mwie.game.model.player.PlayerNumber;

import java.util.Collections;

public class GameMapper {

    public GameWeb mapResponse(Game game) {
        Collections.reverse(game.getBoard().getPlayers().get(PlayerNumber.TWO).pits());
        return new GameWeb(game.getBoard().getPlayers());
    }
}
