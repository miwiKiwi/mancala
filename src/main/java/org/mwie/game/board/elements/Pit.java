package org.mwie.game.board.elements;

import org.mwie.game.player.PlayerNumber;

public abstract class Pit {

    private int stones;

    private final PlayerNumber owner;

    private Pit next;

    private Pit opposite;

    protected Pit(int stones, PlayerNumber owner) {
        this.stones = stones;
        this.owner = owner;
    }

    public int getStones() {
        return stones;
    }

    protected void setStones(int stones) {
        this.stones = stones;
    }

    void add(int stones) {
        setStones(getStones() + stones);
    }

    public PlayerNumber getOwner() {
        return owner;
    }

    public Pit next() {
        return next;
    }

    public void setNext(Pit next) {
        this.next = next;
    }

    public Pit getOpposite() {
        return opposite;
    }

    public void setOpposite(Pit opposite) {
        this.opposite = opposite;
    }
}
