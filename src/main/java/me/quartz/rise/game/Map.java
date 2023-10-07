package me.quartz.rise.game;

import me.quartz.rise.Rise;
import me.quartz.rise.files.CustomFile;
import me.quartz.rise.utils.FileUtils;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        serialize();
    }

    public List<Arena> getArenas() {
        return arenas;
    }

    public void setArenas(List<Arena> arenas) {
        this.arenas = arenas;
    }

    public void generateArenas() {
        this.arenas = new ArrayList<>();
        for(int i = 0; i < 4; i++) arenas.add(new Arena(arenas.size() + 1, this, ArenaType.ROUND));
        for(int i = 0; i < 2; i++) arenas.add(new Arena(arenas.size() + 1, this, ArenaType.SEMI_FINAL));
        arenas.add(new Arena(arenas.size() + 1, this, ArenaType.FINAL));
    }

    public boolean isReady() {
        boolean ready = waitingLocation != null;
        if(arenas.stream().anyMatch(arena -> arena.getSpawnLocations().size() < 2)) ready = false;
        if(arenas.stream().anyMatch(arena -> (arena.getDoor() == null || arena.getStair() == null) && arena.getArenaType() != ArenaType.FINAL)) ready = false;
        return ready;
    }

    public void serialize() {
        CustomFile file = Rise.getInstance().getFileManager().getMapsFile();
        file.getCustomConfig().set(id + ".waiting", FileUtils.locationToString(waitingLocation));
        for(Arena arena : arenas) {
            file.getCustomConfig().set(id + ".arenas." + arena.getId() + ".type", arena.getArenaType().toString());
            file.getCustomConfig().set(id + ".arenas." + arena.getId() + ".spawns", arena.getSpawnLocations().stream().map(FileUtils::locationToString).collect(Collectors.toList()));
            file.getCustomConfig().set(id + ".arenas." + arena.getId() + ".door", FileUtils.blocksToString(arena.getDoor()));
            file.getCustomConfig().set(id + ".arenas." + arena.getId() + ".stair", FileUtils.blocksToString(arena.getStair()));
        }
        try {
            file.getCustomConfig().save(file.getCustomConfigFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
