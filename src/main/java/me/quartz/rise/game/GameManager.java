package me.quartz.rise.game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GameManager {

    private Game game;

    public Game getGame() {
        if(game == null) setupGame();
        if(game.getMap() == null) game.setMap();

        if(!game.isStarted()) startGame();

        return game;
    }

    public void setupGame() {
        game = new Game();
    }

    private void startGame() {
        game.setStarted(true);
        if(game.getMap() != null) {
            for(Arena arena : game.getMap().getArenas()) {
                for(Block block : arena.getStair()) block.setType(Material.AIR);
            }
        }
    }

    public boolean isInGame(Player player) {
        return game != null && game.getPlayers().contains(player);
    }
}
