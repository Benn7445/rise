package me.quartz.rise.game;

import org.bukkit.entity.Player;

public class GameManager {

    private Game game;

    public Game getGame() {
        if(game == null) setupGame();
        if(game.getMap() == null) game.setMap();
        return game;
    }

    public void setupGame() {
        game = new Game();
    }

    public boolean isInGame(Player player) {
        return game != null && game.getPlayers().contains(player);
    }
}
