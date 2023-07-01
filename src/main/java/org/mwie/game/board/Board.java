package org.mwie.game.board;

import org.mwie.game.board.elements.StandardPit;
import org.mwie.game.board.elements.Store;
import org.mwie.game.board.player.Player;
import org.mwie.game.board.player.PlayerNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private final List<StandardPit>  pits;
    private final List<Store>  stores;
    private final List<Player> players;

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

    public List<Player> getPlayers() {
        return players;
    }

    public static class BoardBuilder {
        private List<StandardPit> pits;
        private List<Store> stores;
        private List<Player> players;

        public BoardBuilder create(int numberOfPitsPerPlayer) {
            var pitsOne = buildPitsForPlayer(PlayerNumber.ONE, numberOfPitsPerPlayer);
            var pitsTwo = buildPitsForPlayer(PlayerNumber.TWO, numberOfPitsPerPlayer);
            pits = new ArrayList<>(pitsOne);
            pits.addAll(pitsTwo);

            stores = new ArrayList<>();
            var storeOne = new Store(PlayerNumber.ONE);
            var storeTwo = new Store(PlayerNumber.TWO);
            stores.addAll(Arrays.asList(storeOne, storeTwo));

            players = new ArrayList<>();
            Player playerOne = new Player(PlayerNumber.ONE, pitsOne, storeOne);
            Player playerTwo = new Player(PlayerNumber.TWO, pitsTwo, storeTwo);
            players.addAll(Arrays.asList(playerOne, playerTwo));
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
