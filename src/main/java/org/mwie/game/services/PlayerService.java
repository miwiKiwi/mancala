package org.mwie.game.services;

import org.mwie.game.model.board.elements.Pit;
import org.mwie.game.model.player.Player;

public interface PlayerService {

    Pit takeTurn(Player player, int pitNumber);
}
