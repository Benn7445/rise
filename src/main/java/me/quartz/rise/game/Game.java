package me.quartz.rise.game;

import me.quartz.rise.Rise;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private Map map;

    // Changeable variables
    private final List<Player> players;
    private boolean started;
    private int timer;

    public Game() {
        if(!Rise.getInstance().getMapManager().getReadyMaps().isEmpty())
            this.map = Rise.getInstance().getMapManager().getReadyMaps().get(new Random().nextInt(Rise.getInstance().getMapManager().getReadyMaps().size()));
        else this.map = null;
        this.players = new ArrayList<>();
        this.started = false;
        this.timer = 0;
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

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
