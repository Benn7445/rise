package me.quartz.rise;

import me.quartz.rise.commands.MapCommand;
import me.quartz.rise.files.FileManager;
import me.quartz.rise.game.Game;
import me.quartz.rise.game.GameManager;
import me.quartz.rise.game.MapManager;
import me.quartz.rise.listeners.BlockBreakListener;
import me.quartz.rise.listeners.PlayerInteractListener;
import me.quartz.rise.listeners.PlayerJoinListener;
import me.quartz.rise.listeners.PlayerQuitListener;
import me.quartz.rise.scoreHelper.ScoreManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rise extends JavaPlugin {

    private static Rise instance;

    private FileManager fileManager;
    private MapManager mapManager;
    private ScoreManager scoreManager;
    private GameManager gameManager;

    public static Rise getInstance() {
        return instance;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    @Override
    public void onEnable() {
        instance = this;
        registerManagers();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        unregisterRise();
    }

    private void registerManagers() {
        fileManager = new FileManager();
        mapManager = new MapManager();
        scoreManager = new ScoreManager();
        gameManager = new GameManager();
    }

    private void registerCommands() {
        getCommand("map").setExecutor(new MapCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
    }

    private void unregisterRise() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer("");
        }
    }
}
