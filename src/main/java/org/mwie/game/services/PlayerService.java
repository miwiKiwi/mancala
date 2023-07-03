package org.mwie.game.services;

import org.mwie.game.board.elements.Pit;

public interface PlayerService {

    public Pit takeTurn(int pitNumber);
}
