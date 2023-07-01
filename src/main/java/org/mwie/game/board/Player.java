package org.mwie.game.board;

import org.mwie.game.board.elements.StandardPit;
import org.mwie.game.board.elements.Store;

import java.util.List;

public record Player(PlayerNumber playerNumber, List<StandardPit> pits, Store store) {}
