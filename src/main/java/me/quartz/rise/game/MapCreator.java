package me.quartz.rise.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MapCreator {

    private final UUID id;
    private Location leftClicked;
    private Location rightClicked;

    public MapCreator(Player player) {
        this.id = player.getUniqueId();
        this.leftClicked = null;
        this.rightClicked = null;
    }

    public UUID getId() {
        return id;
    }

    public Location getLeftClicked() {
        return leftClicked;
    }

    public void setLeftClicked(Location leftClicked) {
        this.leftClicked = leftClicked;
    }

    public Location getRightClicked() {
        return rightClicked;
    }

    public void setRightClicked(Location rightClicked) {
        this.rightClicked = rightClicked;
    }
}
