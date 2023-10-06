package me.quartz.rise.game;

import me.quartz.rise.Rise;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private Map map;
    private final List<Player> players;

    public Game() {
        if(!Rise.getInstance().getMapManager().getReadyMaps().isEmpty())
            this.map = Rise.getInstance().getMapManager().getReadyMaps().get(new Random().nextInt(Rise.getInstance().getMapManager().getReadyMaps().size()));
        else this.map = null;
        this.players = new ArrayList<>();
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
}
