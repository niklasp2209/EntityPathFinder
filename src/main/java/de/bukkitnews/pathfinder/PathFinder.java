package de.bukkitnews.pathfinder;

import de.bukkitnews.pathfinder.command.PetCommand;
import de.bukkitnews.pathfinder.listener.PlayerConnectionListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PathFinder extends JavaPlugin {

    private FileConfiguration petConfig;
    private final Map<UUID, Object> petMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Register commands
        getCommand("pet").setExecutor(new PetCommand(this));

        // Register event listeners
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(this), this);

        // Load or create the pet configuration file
        petConfig = getConfig();
        petConfig.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        // Save the pet configuration file
        saveConfig();
    }

    public FileConfiguration getPetConfig() {
        return petConfig;
    }

    public Map<UUID, Object> getPetMap() {
        return petMap;
    }
}
