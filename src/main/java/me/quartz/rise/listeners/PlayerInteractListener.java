package me.quartz.rise.listeners;

import me.quartz.rise.Rise;
import me.quartz.rise.game.MapCreator;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        MapCreator mapCreator = Rise.getInstance().getMapManager().getMapCreatorByPlayer(player);
        if(player.getItemInHand() != null) {
            ItemStack itemStack = player.getItemInHand();
            if(itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Map Manager")) {
                if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    mapCreator.setRightClicked(event.getClickedBlock().getLocation());
                    player.sendMessage(ChatColor.YELLOW + "Selected first position.");
                } else if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    mapCreator.setLeftClicked(event.getClickedBlock().getLocation());
                    player.sendMessage(ChatColor.YELLOW + "Selected second position.");
                }
            }
        }
    }
}
