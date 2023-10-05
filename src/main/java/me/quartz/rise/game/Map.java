package me.quartz.rise.game;

import me.quartz.rise.Rise;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {

    private final int id;
    private Location waitingLocation;
    private List<Arena> arenas;

    public Map() {
        this.id = Rise.getInstance().getMapManager().getNewMapId();
        this.waitingLocation = null;
        generateArenas();
    }

    public Map(int id, Location waitingLocation, List<Arena> arenas) {
        this.id = id;
        this.waitingLocation = waitingLocation;
        this.arenas = arenas;
    }

    public int getId() {
        return id;
    }

    public Location getWaitingLocation() {
        return waitingLocation;
    }

    public void setWaitingLocation(Location waitingLocation) {
        this.waitingLocation = waitingLocation;
    }

    public List<Arena> getArenas() {
        return arenas;
    }

    public void generateArenas() {
        this.arenas = new ArrayList<>();
        for(int i = 0; i < 4; i++) arenas.add(new Arena(arenas.size() + 1, ArenaType.ROUND));
        for(int i = 0; i < 2; i++) arenas.add(new Arena(arenas.size() + 1, ArenaType.SEMI_FINAL));
        arenas.add(new Arena(arenas.size() + 1, ArenaType.FINAL));
    }

    public boolean isReady() {
        boolean ready = waitingLocation != null;
        if(arenas.stream().anyMatch(arena -> arena.getSpawnLocations().size() < 2 || arena.getDoor() == null || arena.getStair() == null)) ready = false;
        return ready;
    }
}
