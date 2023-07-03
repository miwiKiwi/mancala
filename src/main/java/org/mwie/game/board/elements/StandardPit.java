package org.mwie.game.board.elements;

import org.mwie.game.player.PlayerNumber;

public class StandardPit extends Pit {

    public StandardPit(PlayerNumber owner) {
        super(6, owner);
    }

    public int takeStones() {
        var result = getStones();
        setStones(0);
        return result;
    }

    public void putStone() {
        super.add(1);
    }
}
