package org.mwie.game.services;

import org.mwie.game.board.elements.Pit;
import org.mwie.game.player.Player;

public interface PlayerService {

    Pit takeTurn(Player player, int pitNumber);
}
