package org.mwie.game.services;

import lombok.RequiredArgsConstructor;
import org.mwie.game.model.board.elements.Pit;
import org.mwie.game.model.board.elements.StandardPit;
import org.mwie.game.model.board.elements.Store;
import org.mwie.game.model.player.Player;

@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    @Override
    public Pit takeTurn(Player player, int pitNumber) {
        var pit = player.getStandardPit(pitNumber);
        if (!pit.hasStones()) {
            throw new IllegalArgumentException("Pit must have stones in order to take turn");
        }
        return takeTurn(player, pit);
    }

    private Pit takeTurn(Player player, StandardPit pit) {
        var seeds = pit.takeOutStones();
        Pit endPit = pit;

        while (seeds > 0) {
            endPit = endPit.next();
            if (endPit.canPutStones(player)) {
                seeds--;
                endPit.putStone();
            }
        }
        if (canCaptureStones(player, endPit)) {
            captureStones(player, (StandardPit) endPit);
        }
        if (canGoAgain(player, endPit)) {
            //TODO: another turn
        }
        return endPit;
    }

    private boolean canGoAgain(Player player, Pit endPit) {
        return endPit instanceof Store
                && endPit.getOwner().equals(player.playerNumber());
    }

    private static boolean canCaptureStones(Player player, Pit endPit) {
        return endPit instanceof StandardPit
                && endPit.getOwner().equals(player.playerNumber())
                && endPit.getStones() == 1;
    }

    private void captureStones(Player player, StandardPit pit) {
        var stones = pit.takeOutStones() + pit.getOpposite().takeOutStones();
        player.store().putStones(stones);
    }
}
