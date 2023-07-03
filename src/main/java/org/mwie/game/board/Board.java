package org.mwie.game.board;

import org.mwie.game.board.elements.StandardPit;
import org.mwie.game.board.elements.Store;
import org.mwie.game.player.Player;
import org.mwie.game.player.PlayerNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Board {

    public static final PlayerNumber ONE = PlayerNumber.ONE;
    public static final PlayerNumber TWO = PlayerNumber.TWO;

    private final List<StandardPit>  pits;
    private final List<Store>  stores;
    private final Map<PlayerNumber, Player> players;

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

    public Map<PlayerNumber, Player> getPlayers() {
        return players;
    }

    public static class BoardBuilder {
        private List<StandardPit> pits;
        private List<Store> stores;
        private Map<PlayerNumber, Player> players;

        public BoardBuilder create(int numberOfPitsPerPlayer) {
            var pitsOne = buildPitsForPlayer(ONE, numberOfPitsPerPlayer);
            var pitsTwo = buildPitsForPlayer(TWO, numberOfPitsPerPlayer);
            pits = new ArrayList<>(pitsOne);
            pits.addAll(pitsTwo);

            stores = new ArrayList<>();
            var storeOne = new Store(ONE);
            var storeTwo = new Store(TWO);
            stores.addAll(Arrays.asList(storeOne, storeTwo));

            setupPitsCycle(pitsOne, pitsTwo, storeOne, storeTwo);
            setupOpposites(pitsOne, pitsTwo);

            players =  new EnumMap<>(PlayerNumber.class);
            Player playerOne = new Player(ONE, pitsOne, storeOne);
            Player playerTwo = new Player(TWO, pitsTwo, storeTwo);
            players.put(ONE, playerOne);
            players.put(TWO, playerTwo);
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
