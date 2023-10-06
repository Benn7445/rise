package me.quartz.rise.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static String locationToString(Location location) {
        return location != null ? (location.getWorld().getName() + "," +
                location.getX() + "," +
                location.getY() + "," +
                location.getZ() + "," +
                location.getYaw() + "," +
                location.getPitch()) : "";
    }

    public static List<String> blocksToString(List<Block> blocks) {
        List<String> blocksReturn = new ArrayList<>();
        if (!blocks.isEmpty()) {
            for (Block block : blocks)
                blocksReturn.add(locationToString(block.getLocation()) + "," + block.getType().toString());
        }
        return blocksReturn;
    }
}
