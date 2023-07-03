package org.mwie.game.services;

import org.mwie.game.player.Player;
import org.mwie.game.player.PlayerNumber;

public interface GameService {

    String createGame();

    Player getPlayer(PlayerNumber playerNumber);
}
