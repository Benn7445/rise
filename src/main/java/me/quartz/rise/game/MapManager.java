package me.quartz.rise.game;

import me.quartz.rise.Rise;
import me.quartz.rise.files.CustomFile;
import me.quartz.rise.utils.FileUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapManager {

    private List<Map> maps = new ArrayList<>();
    private List<MapCreator> mapCreators = new ArrayList<>();

    public List<Map> getMaps() {
        return maps;
    }

    public List<Map> getReadyMaps() {
        return maps.stream().filter(Map::isReady).collect(Collectors.toList());
    }

    public MapManager() {
        deserialize();
    }

    public Map createMap() {
        Map map = new Map();
        maps.add(map);
        map.serialize();
        return map;
    }

    public int getNewMapId() {
        return maps.size() + 1;
    }

    public Map getMapById(int id) {
        return maps.stream().filter(map -> map.getId() == id).findFirst().orElse(null);
    }

    public MapCreator getMapCreatorByPlayer(Player player) {
        MapCreator creator = mapCreators.stream().filter(mapCreator -> mapCreator.getId() == player.getUniqueId()).findFirst().orElse(null);
        if(creator == null) {
            creator = new MapCreator(player);
            mapCreators.add(creator);
        }
        return creator;
    }

    public void deserialize() {
        CustomFile file = Rise.getInstance().getFileManager().getMapsFile();
        for(String stringId : file.getCustomConfig().getKeys(false)) {
            int id = Integer.parseInt(stringId);
            Location location = FileUtils.stringToLocation(file.getCustomConfig().getString(id + ".waiting"));
            Map map = new Map(id, location, new ArrayList<>());
            List<Arena> arenas = new ArrayList<>();
            for(String stringArenaId : file.getCustomConfig().getConfigurationSection(id + ".arenas").getKeys(false)) {
                int arenaId = Integer.parseInt(stringArenaId);
                ArenaType arenaType = ArenaType.valueOf(file.getCustomConfig().getString(id + ".arenas." + arenaId + ".type"));
                List<Location> locations = file.getCustomConfig().getStringList(id + ".arenas." + arenaId + ".spawns").stream().map(FileUtils::stringToLocation).collect(Collectors.toList());
                List<Block> stairs = FileUtils.stringToBlocks(file.getCustomConfig().getStringList(id + ".arenas." + arenaId + ".stair"));
                List<Block> door = FileUtils.stringToBlocks(file.getCustomConfig().getStringList(id + ".arenas." + arenaId + ".door"));
                arenas.add(new Arena(arenaId, map, arenaType, locations, stairs, door));
            }
            map.setArenas(arenas);
            maps.add(map);
        }
    }
}
