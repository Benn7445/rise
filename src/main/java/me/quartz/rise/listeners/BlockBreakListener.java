package me.quartz.rise.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(player.getItemInHand() != null) {
            ItemStack itemStack = player.getItemInHand();
            if(itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Map Manager")) {
                event.setCancelled(true);
            }
        }
    }
}
