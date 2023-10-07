package me.quartz.rise.listeners;

import me.quartz.rise.Rise;
import me.quartz.rise.game.Game;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Rise.getInstance().getScoreManager().removeScoreboard(player);
        event.setQuitMessage(null);
        if(Rise.getInstance().getGameManager().isInGame(player)) {
            Game game = Rise.getInstance().getGameManager().getGame();
            game.removePlayer(player);
            event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + player.getName() + " left your game (" + game.getPlayers().size() + "/8).");
        }
    }
}
