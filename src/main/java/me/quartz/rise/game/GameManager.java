package me.quartz.rise.game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GameManager {

    private Game game;

    public Game getGame() {
        if(game == null) createGame();
        if(game.getMap() == null) game.setMap();

        if(!game.isSetupped()) setupGame();

        return game;
    }

    public void createGame() {
        game = new Game();
    }

    private void setupGame() {
        game.setSetupped(true);
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
