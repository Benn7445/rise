package me.quartz.rise.game;

import org.bukkit.entity.Player;

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
}
