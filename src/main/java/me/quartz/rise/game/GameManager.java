package me.quartz.rise.game;

import org.bukkit.entity.Player;

public class GameManager {

    private Game game;

    public Game getGame() {
        return game;
    }

    public void setupGame() {
        game = new Game();
    }

    public boolean isInGame(Player player) {
        return game != null && game.getPlayers().contains(player);
    }
}
