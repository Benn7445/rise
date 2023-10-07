package me.quartz.rise.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class FileUtils {

    public static String locationToString(Location location) {
        return location != null ? (location.getWorld().getName() + "," +
                location.getX() + "," +
                location.getY() + "," +
                location.getZ() + "," +
                location.getYaw() + "," +
                location.getPitch()) : "";
    }

    public static Location stringToLocation(String string) {
        String[] s = string.split(",");
        return !string.isEmpty() ?
                new Location(Bukkit.getWorld(s[0]),
                        Float.parseFloat(s[1]),
                        Float.parseFloat(s[2]),
                        Float.parseFloat(s[3]),
                        Float.parseFloat(s[4]),
                        Float.parseFloat(s[5])
                ) : null;
    }

    public static List<String> blocksToString(List<Block> blocks) {
        List<String> blocksReturn = new ArrayList<>();
        if (!blocks.isEmpty()) {
            for (Block block : blocks)
                blocksReturn.add(locationToString(block.getLocation()) + "," + block.getType().toString());
        }
        return blocksReturn;
    }

    public static List<Block> stringToBlocks(List<String> list) {
        List<Block> blocksReturn = new ArrayList<>();
        for (String string : list) {
            Location location = stringToLocation(string.replace("," + string.split(",")[string.split(",").length - 1], ""));
            if(location != null) {
                Block block = location.getBlock();
                block.setType(Material.getMaterial(string.split(",")[string.split(",").length - 1]));
                blocksReturn.add(block);
            }
        }
        return blocksReturn;
    }
}
