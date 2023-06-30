package org.mwie.game.board;

public class Store extends Pit {

    public Store() {
        super(0);
    }

    public void putStones(int stones) {
        setStones(countStones() + stones);
    }
}
