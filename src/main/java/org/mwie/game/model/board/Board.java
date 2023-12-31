package org.mwie.game.model.board;

import org.mwie.game.model.board.elements.StandardPit;
import org.mwie.game.model.board.elements.Store;
import org.mwie.game.model.player.Player;
import org.mwie.game.model.player.PlayerNumber;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

public class Board {

    public static final PlayerNumber ONE = PlayerNumber.ONE;
    public static final PlayerNumber TWO = PlayerNumber.TWO;

    private Map<PlayerNumber, Player> players;

    private Board() {
    }

    public Map<PlayerNumber, Player> getPlayers() {
        return players;
    }


    public static Board create(int numberOfPitsPerPlayer, int initialNumberOfStonesInPit) {
        Board board = new Board();
        var pitsOne = buildPitsForPlayer(ONE, numberOfPitsPerPlayer, initialNumberOfStonesInPit);
        var pitsTwo = buildPitsForPlayer(TWO, numberOfPitsPerPlayer, initialNumberOfStonesInPit);

        var storeOne = new Store(ONE);
        var storeTwo = new Store(TWO);

        setupPitsCycle(pitsOne, pitsTwo, storeOne, storeTwo);
        setupOpposites(pitsOne, pitsTwo);

        board.players = new EnumMap<>(PlayerNumber.class);
        Player playerOne = new Player(ONE, pitsOne, storeOne);
        Player playerTwo = new Player(TWO, pitsTwo, storeTwo);
        board.players.put(ONE, playerOne);
        board.players.put(TWO, playerTwo);
        return board;
    }

    private static void setupOpposites(LinkedList<StandardPit> pitsOne, LinkedList<StandardPit> pitsTwo) {
        for (int i = 0; i < pitsOne.size(); i++) {
            var one = pitsOne.get(i);
            var two = pitsTwo.get(pitsTwo.size() - i - 1);
            one.setOpposite(two);
            two.setOpposite(one);
        }
    }

    private static LinkedList<StandardPit> buildPitsForPlayer(PlayerNumber playerNumber, int pitsPerPlayer, int stonesPerPit) {
        LinkedList<StandardPit> playerPits = new LinkedList<>();
        var previous = new StandardPit(playerNumber, stonesPerPit);

        while (playerPits.size() < pitsPerPlayer) {
            var next = new StandardPit(playerNumber, stonesPerPit);
            previous.setNext(next);
            previous = next;
            playerPits.addLast(next);
        }
        return playerPits;
    }

    private static void setupPitsCycle(LinkedList<StandardPit> pitsOne, LinkedList<StandardPit> pitsTwo,
                                       Store storeOne, Store storeTwo) {
        pitsOne.getLast().setNext(storeOne);
        storeOne.setNext(pitsTwo.getFirst());
        pitsTwo.getLast().setNext(storeTwo);
        storeTwo.setNext(pitsOne.getFirst());
    }
}
