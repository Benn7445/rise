package me.quartz.rise.files;

import me.quartz.rise.Rise;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomFile {

    private File customConfigFile;
    private FileConfiguration customConfig;

    public CustomFile(String name) {
        createCustomConfig(name);
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    public File getCustomConfigFile() {
        return this.customConfigFile;
    }

    private void createCustomConfig(String name) {
        customConfigFile = new File(Rise.getInstance().getDataFolder(), name + ".yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            Rise.getInstance().saveResource(name + ".yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
