package me.quartz.rise.listeners;

import me.quartz.rise.Rise;
import me.quartz.rise.game.Game;
import me.quartz.rise.game.Map;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Rise.getInstance().getScoreManager().createScoreboard(player);
        Game game = Rise.getInstance().getGameManager().getGame();
        if(game != null && game.getMap() != null && game.getMap().isReady()) {
            Map map = game.getMap();
            player.teleport(map.getWaitingLocation());
            player.getInventory().clear();
            player.getInventory().setArmorContents(new ItemStack[player.getInventory().getArmorContents().length]);
            player.setHealth(20);
            player.setFoodLevel(20);
        } else {
            player.teleport(new Location(player.getWorld(), 233.5, 51, 1353.5, -90, 0));
            player.setGameMode(GameMode.CREATIVE);
        }
    }
}
