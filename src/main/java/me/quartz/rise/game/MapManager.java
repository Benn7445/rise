package me.quartz.rise.game;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MapManager {

    private List<Map> maps = new ArrayList<>();
    private List<MapCreator> mapCreators = new ArrayList<>();

    public List<Map> getMaps() {
        return maps;
    }

    public Map createMap() {
        Map map = new Map();
        maps.add(map);
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
