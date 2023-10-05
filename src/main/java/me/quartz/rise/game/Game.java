package me.quartz.rise.game;

import me.quartz.rise.Rise;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private final Map map;
    private final List<Player> players;

    public Game() {
        this.map = Rise.getInstance().getMapManager().getMaps().get(new Random().nextInt(Rise.getInstance().getMapManager().getMaps().size()));
        this.players = new ArrayList<>();
    }

    public Map getMap() {
        return map;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        if(map != null && map.isReady()) this.players.add(player);
    }
}
