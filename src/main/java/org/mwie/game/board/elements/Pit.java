package org.mwie.game.board.elements;

import org.mwie.game.board.PlayerNumber;

public abstract class Pit {

    private int stones;

    private final PlayerNumber owner;

    protected Pit(int stones, PlayerNumber owner) {
        this.stones = stones;
        this.owner = owner;
    }

    public int countStones() {
        return stones;
    }

    protected void setStones(int stones) {
        this.stones = stones;
    }

    void sow(int stones) {
        setStones(countStones() + stones);
    }

    public PlayerNumber getOwner() {
        return owner;
    }
}
