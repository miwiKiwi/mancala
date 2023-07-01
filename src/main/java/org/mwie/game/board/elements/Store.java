package org.mwie.game.board.elements;

import org.mwie.game.PlayerNumber;

public class Store extends Pit {

    public Store(PlayerNumber owner) {
        super(0, owner);
    }

    public void putStones(int stones) {
        setStones(countStones() + stones);
    }
}
