package org.mwie.game.model.board.elements;

import org.mwie.game.model.player.Player;
import org.mwie.game.model.player.PlayerNumber;

public class StandardPit extends Pit {

    private StandardPit opposite;

    public StandardPit(PlayerNumber owner, int initialNumberOfStones) {
        super(initialNumberOfStones, owner);
    }

    public int takeOutStones() {
        var result = getStones();
        setStones(0);
        return result;
    }

    public boolean hasStones() {
        return getStones() > 0;
    }

    public StandardPit getOpposite() {
        return opposite;
    }

    public void setOpposite(StandardPit opposite) {
        this.opposite = opposite;
    }

    @Override
    public boolean canPutStones(Player player) {
        return true;
    }
}
