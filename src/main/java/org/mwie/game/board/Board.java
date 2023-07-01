package org.mwie.game.board;

import org.mwie.game.PlayerNumber;
import org.mwie.game.board.elements.StandardPit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board {

    //TODO: board should have two players, 12 standardPits and 2 stores

    private List<StandardPit>  pits;

    public List<StandardPit> getPits() {
        return pits;
    }

    private Board(BoardBuilder builder) {
        this.pits = builder.pits;
    }

    public static class BoardBuilder {
        private List<StandardPit> pits;
        private int stores;

        public BoardBuilder withPits(int numberOfPitsPerPlayer) {
            LinkedList<StandardPit> pitsOne = buildPitsForPlayer(PlayerNumber.ONE, numberOfPitsPerPlayer);
            LinkedList<StandardPit> pitsTwo = buildPitsForPlayer(PlayerNumber.TWO, numberOfPitsPerPlayer);
            pits = new ArrayList<>(pitsOne);
            pits.addAll(pitsTwo);
            return this;
        }

        private LinkedList<StandardPit> buildPitsForPlayer(PlayerNumber playerNumber, int numberOfPitsPerPlayer) {
            LinkedList<StandardPit> playerPits = new LinkedList<>();
            playerPits.addLast(new StandardPit(playerNumber));
            while(playerPits.size() < numberOfPitsPerPlayer) {
                playerPits.addLast(new StandardPit(playerNumber));
            }
            return playerPits;
        }

        public Board build() {
            return new Board(this);
        }
    }
}
