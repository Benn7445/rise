package me.quartz.rise.game;

import me.quartz.rise.Rise;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private Map map;

    // Changeable variables
    private final List<Player> players;
    private boolean setupped;
    private ArenaType round;
    private int timer;

    public Game() {
        if(!Rise.getInstance().getMapManager().getReadyMaps().isEmpty())
            this.map = Rise.getInstance().getMapManager().getReadyMaps().get(new Random().nextInt(Rise.getInstance().getMapManager().getReadyMaps().size()));
        else this.map = null;
        this.players = new ArrayList<>();
        this.setupped = false;
        this.round = null;
        this.timer = 10;
    }

    public Map getMap() {
        return map;
    }

    public void setMap() {
        if(!Rise.getInstance().getMapManager().getReadyMaps().isEmpty())
            this.map = Rise.getInstance().getMapManager().getReadyMaps().get(new Random().nextInt(Rise.getInstance().getMapManager().getReadyMaps().size()));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        if(map != null && map.isReady()) this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public boolean isSetupped() {
        return setupped;
    }

    public void setSetupped(boolean setupped) {
        this.setupped = setupped;
    }

    public ArenaType getRound() {
        return round;
    }

    public void setRound(ArenaType round) {
        this.round = round;
    }

    public int getTimer() {
        return timer;
    }

    public void countdown() {
        this.timer--;
    }

    public void startGame() {
        if(players.size() == 8) {
            new BukkitRunnable() {
                public void run() {
                    if(timer == 10 || (timer <= 5 && timer > 0) ) Bukkit.broadcastMessage(ChatColor.YELLOW + "Game is starting in " + ChatColor.GOLD + timer + "s" + ChatColor.YELLOW + ".");
                    else if(timer == 0) {
                        Bukkit.broadcastMessage(ChatColor.YELLOW + "Game is starting in " + ChatColor.GOLD + timer + "s" + ChatColor.YELLOW + ".");
                        int arena = 0;
                        int position = 0;
                        for(Player player : players) {
                            player.teleport(getMap().getArenas().get(arena).getSpawnLocations().get(position));
                            if(position == 0) position++;
                            else {
                                position--;
                                arena++;
                            }
                        }
                        this.cancel();
                    }
                    countdown();
                }
            }.runTaskTimer(Rise.getInstance(), 0, 20);
        }
    }
}
