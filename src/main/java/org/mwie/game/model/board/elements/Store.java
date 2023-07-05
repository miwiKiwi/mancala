package org.mwie.game.model.board.elements;

import org.mwie.game.model.player.Player;
import org.mwie.game.model.player.PlayerNumber;

public class Store extends Pit {

    public Store(PlayerNumber owner) {
        super(0, owner);
    }

    public void putStones(int stones) {
        setStones(getStones() + stones);
    }

    @Override
    public boolean canPutStones(Player player) {
        return player.playerNumber().equals(getOwner());
    }
}
