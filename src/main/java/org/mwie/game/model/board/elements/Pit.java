package org.mwie.game.model.board.elements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mwie.game.model.player.Player;
import org.mwie.game.model.player.PlayerNumber;

public abstract class Pit {

    private int stones;

    private final PlayerNumber owner;

    @JsonIgnore
    private Pit next;

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

    public void putStone() {
        add(1);
    }

    public abstract boolean canPutStones(Player player);

    public void add(int stones) {
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
}
