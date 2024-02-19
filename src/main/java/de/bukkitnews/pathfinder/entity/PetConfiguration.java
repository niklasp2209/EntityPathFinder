package de.bukkitnews.pathfinder.entity;

import de.bukkitnews.pathfinder.PathFinder;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class PetConfiguration {

    protected Player player;
    protected Entity pet;
    protected PathFinder pathFinder;

    public PetConfiguration(PathFinder pathFinder){
        this.pathFinder = pathFinder;
    }

    public PetConfiguration(PathFinder pathFinder, Player player) {
        this.pathFinder = pathFinder;
        this.player = player;
    }

    // Abstract methods to be implemented by specific pet classes
    public abstract void spawnPet(Location location);
    public abstract void savePetData();
    public abstract void loadPetData();
    public abstract void despawnPet();
    public abstract Entity getPet();
}
