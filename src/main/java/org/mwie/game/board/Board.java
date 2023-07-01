package org.mwie.game.board;

import org.mwie.game.PlayerNumber;
import org.mwie.game.board.elements.StandardPit;
import org.mwie.game.board.elements.Store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {

    //TODO: board should have two players, 12 standardPits and 2 stores

    private List<StandardPit>  pits;
    private List<Store>  stores;

    private Board(BoardBuilder builder) {
        this.pits = builder.pits;
        this.stores = builder.stores;
    }

    public List<StandardPit> getPits() {
        return pits;
    }

    public List<Store> getStores() {
        return stores;
    }

    public static class BoardBuilder {
        private List<StandardPit> pits;
        private List<Store> stores;

        public BoardBuilder withPits(int numberOfPitsPerPlayer) {
            var pitsOne = buildPitsForPlayer(PlayerNumber.ONE, numberOfPitsPerPlayer);
            var pitsTwo = buildPitsForPlayer(PlayerNumber.TWO, numberOfPitsPerPlayer);
            pits = new ArrayList<>(pitsOne);
            pits.addAll(pitsTwo);
            return this;
        }

        public BoardBuilder withStores() {
            stores = new ArrayList<>();
            var storeOne = new Store(PlayerNumber.ONE);
            var storeTwo = new Store(PlayerNumber.TWO);
            stores.addAll(Arrays.asList(storeOne, storeTwo));
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
