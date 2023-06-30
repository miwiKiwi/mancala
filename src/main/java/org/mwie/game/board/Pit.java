package org.mwie.game.board;

public abstract class Pit {

    private int stones;

    protected Pit(int stones) {
        this.stones = stones;
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

}
