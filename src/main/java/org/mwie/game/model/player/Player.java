package org.mwie.game.model.player;

import org.mwie.game.model.board.elements.StandardPit;
import org.mwie.game.model.board.elements.Store;

import java.util.List;

public record Player(PlayerNumber playerNumber, List<StandardPit> pits, Store store) {

    public StandardPit getStandardPit(int pitNumber) {
        try {
            return pits.get(pitNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Player must choose pit within bounds");
        }
    }
}
