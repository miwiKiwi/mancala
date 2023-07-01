package org.mwie.game.board;

import org.mwie.game.board.elements.StandardPit;

import java.util.ArrayList;
import java.util.List;

public class Board {

    //TODO: board should have two players, 12 standardPits and 2 stores

    private List<StandardPit> pits;

    public List<StandardPit> getPits() {
        return pits;
    }

    private Board(BoardBuilder builder) {
        this.pits = builder.pits;
    }

    public static class BoardBuilder {
        private int playerNumber;
        private List<StandardPit> pits = new ArrayList<>();
        private int stores;

        public BoardBuilder withPits(int pits) {
            while(this.pits.size() < pits) {
                this.pits.add(new StandardPit());
            }
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}
