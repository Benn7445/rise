package me.quartz.rise.scoreHelper;

import me.quartz.rise.Rise;
import me.quartz.rise.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreManager {

    public ScoreManager() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) updateScoreboard(player);
            }
        }.runTaskTimer(Rise.getInstance(), 20L, 20L);
    }

    public void createScoreboard(Player player) {
        ScoreHelper helper = ScoreHelper.createScore(player);
        helper.setTitle("&a&lRise");
        boolean isInGame = Rise.getInstance().getGameManager().isInGame(player);
        if(isInGame) {
            helper.setSlot(10, "&6&lYou");
            helper.setSlot(9, player.getName());
            helper.setSlot(8, " ");
            helper.setSlot(7, "&6&lRound");
            helper.setSlot(6, "Round Starting...");
            helper.setSlot(5, " ");
            helper.setSlot(4, "&6&lServer");
            helper.setSlot(3, "RISE");
            helper.setSlot(2, " ");
            helper.setSlot(1, "&cminebadmc.com");
        } else {
            helper.setSlot(7, "&6&lYou");
            helper.setSlot(6, player.getName());
            helper.setSlot(5, " ");
            helper.setSlot(4, "&6&lMode");
            helper.setSlot(3, "Setup Mode");
            helper.setSlot(2, " ");
            helper.setSlot(1, "&cminebadmc.com");
        }
    }

    public void updateScoreboard(Player player) {
        if(ScoreHelper.hasScore(player)) {
            ScoreHelper helper = ScoreHelper.getByPlayer(player);
            // helper.setSlot(7, " ");
        }
    }

    public void removeScoreboard(Player player) {
        if(ScoreHelper.hasScore(player)) ScoreHelper.removeScore(player);
    }
}