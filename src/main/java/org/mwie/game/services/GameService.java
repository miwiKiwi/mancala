package org.mwie.game.services;

import org.mwie.game.model.Game;
import org.mwie.game.model.player.PlayerNumber;

public interface GameService {

    Game createGame(int pitsPerPlayer, int numberOfStones);

    Game getGame();

    Game makeAMove(PlayerNumber playerNumber, int pitIndex);
}
