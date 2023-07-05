package org.mwie.game.services;

import org.mwie.game.model.player.Player;
import org.mwie.game.model.player.PlayerNumber;

public interface GameService {

    String createGame();

    Player getPlayer(PlayerNumber playerNumber);
}
