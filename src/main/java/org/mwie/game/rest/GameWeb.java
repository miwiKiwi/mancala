package org.mwie.game.rest;

import org.mwie.game.model.player.Player;
import org.mwie.game.model.player.PlayerNumber;

import java.util.Map;

public record GameWeb(Map<PlayerNumber, Player> players) { }
