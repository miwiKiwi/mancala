package org.mwie.game.board;

import org.mwie.game.board.elements.StandardPit;
import org.mwie.game.board.elements.Store;
import org.mwie.game.board.player.Player;
import org.mwie.game.board.player.PlayerNumber;
import org.mwie.game.board.player.Players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private final List<StandardPit>  pits;
    private final List<Store>  stores;
    private final Players players;

    private Board(BoardBuilder builder) {
        this.pits = builder.pits;
        this.stores = builder.stores;
        this.players = builder.players;
    }

    public List<StandardPit> getPits() {
        return pits;
    }

    public List<Store> getStores() {
        return stores;
    }

    public Players getPlayers() {
        return players;
    }

    public static class BoardBuilder {
        private List<StandardPit> pits;
        private List<Store> stores;
        private Players players;

        public BoardBuilder create(int numberOfPitsPerPlayer) {
            var pitsOne = buildPitsForPlayer(PlayerNumber.ONE, numberOfPitsPerPlayer);
            var pitsTwo = buildPitsForPlayer(PlayerNumber.TWO, numberOfPitsPerPlayer);
            pits = new ArrayList<>(pitsOne);
            pits.addAll(pitsTwo);

            stores = new ArrayList<>();
            var storeOne = new Store(PlayerNumber.ONE);
            var storeTwo = new Store(PlayerNumber.TWO);
            stores.addAll(Arrays.asList(storeOne, storeTwo));

            setupPitsCycle(pitsOne, pitsTwo, storeOne, storeTwo);
            setupOpposites(pitsOne, pitsTwo);


            Player playerOne = new Player(PlayerNumber.ONE, pitsOne, storeOne);
            Player playerTwo = new Player(PlayerNumber.TWO, pitsTwo, storeTwo);
            players = new Players(playerOne, playerTwo);
            return this;
        }

        private void setupOpposites(LinkedList<StandardPit> pitsOne, LinkedList<StandardPit> pitsTwo) {
            for(int i =0; i< pitsOne.size(); i++) {
                var one = pitsOne.get(i);
                var two = pitsTwo.get(pitsTwo.size() - i -1);
                one.setOpposite(two);
                two.setOpposite(one);
            }
        }

        private LinkedList<StandardPit> buildPitsForPlayer(PlayerNumber playerNumber, int numberOfPitsPerPlayer) {
            LinkedList<StandardPit> playerPits = new LinkedList<>();
            var previous = new StandardPit(playerNumber);
            while(playerPits.size() < numberOfPitsPerPlayer) {
                var next = new StandardPit(playerNumber);
                previous.setNext(next);
                previous = next;
                playerPits.add(next);
            }
            return playerPits;
        }

        private void setupPitsCycle(LinkedList<StandardPit> pitsOne, LinkedList<StandardPit> pitsTwo,
                                    Store storeOne, Store storeTwo) {
            pitsOne.getLast().setNext(storeOne);
            storeOne.setNext(pitsTwo.getFirst());
            pitsTwo.getLast().setNext(storeTwo);
            storeTwo.setNext(pitsOne.getFirst());
        }

        public Board build() {
            return new Board(this);
        }
    }
}
