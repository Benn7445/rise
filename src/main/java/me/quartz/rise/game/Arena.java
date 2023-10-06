package me.quartz.rise.game;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class Arena {

    private final int id;
    private final Map map;
    private final ArenaType arenaType;
    private final List<Location> spawnLocations;
    private List<Block> stair;
    private List<Block> door;

    public Arena(int id, Map map, ArenaType arenaType) {
        this.id = id;
        this.map = map;
        this.arenaType = arenaType;
        this.spawnLocations = new Vector<Location>() {{setSize(2);}};
        this.stair = new ArrayList<>();
        this.door = new ArrayList<>();
    }

    public Arena(int id, Map map, ArenaType arenaType, List<Location> spawnLocations, List<Block> stair, List<Block> door) {
        this.id = id;
        this.map = map;
        this.arenaType = arenaType;
        this.spawnLocations = spawnLocations;
        this.stair = stair;
        this.door = door;
    }

    public int getId() {
        return id;
    }

    public ArenaType getArenaType() {
        return arenaType;
    }

    public List<Location> getSpawnLocations() {
        return spawnLocations;
    }

    public void setSpawnLocation(int number, Location spawnLocation) {
        this.spawnLocations.set(number - 1, spawnLocation);
        map.serialize();
    }

    public List<Block> getStair() {
        return stair;
    }

    public void setStair(List<Block> stair) {
        this.stair = stair;
        map.serialize();
    }

    public List<Block> getDoor() {
        return door;
    }

    public void setDoor(List<Block> door) {
        this.door = door;
        map.serialize();
    }
}
