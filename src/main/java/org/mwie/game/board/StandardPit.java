package org.mwie.game.board;

public class StandardPit extends Pit {

    public StandardPit() {
        super(6);
    }

    public int takeStones() {
        var result = countStones();
        setStones(0);
        return result;
    }

    public void sow() {
        super.sow(1);
    }
}
