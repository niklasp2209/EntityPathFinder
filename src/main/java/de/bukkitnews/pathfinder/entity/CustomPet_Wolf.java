package de.bukkitnews.pathfinder.entity;

import de.bukkitnews.pathfinder.PathFinder;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomPet_Wolf extends PetConfiguration{

    public CustomPet_Wolf(PathFinder pathFinder){
        super(pathFinder);
    }

    public CustomPet_Wolf(PathFinder pathFinder, Player player) {
        super(pathFinder, player);
    }

    @Override
    public void spawnPet(Location location) {
        this.pet = location.getWorld().spawnEntity(location, EntityType.SHEEP);
        this.pet.setCustomName(player.getName() + "'s Haustier");
        this.pet.setCustomNameVisible(true);
        this.pet.setMetadata("custom_pet_owner", new FixedMetadataValue(this.pathFinder, player.getUniqueId().toString()));
        this.pet.setInvulnerable(true);
        this.followOwner();
    }

    @Override
    public void savePetData() {
        // Implementierung zum Speichern von Haustierdaten in der Konfiguration
    }

    @Override
    public void loadPetData() {
        // Implementierung zum Laden von Haustierdaten aus der Konfiguration
    }

    @Override
    public void despawnPet() {
        if (pet != null && !pet.isDead()) {
            pet.remove();
        }
    }

    @Override
    public Entity getPet() {
        return pet;
    }

    public void followOwner() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!pet.isValid() || pet.isDead() || !player.isValid() || !player.isOnline()) {
                    cancel();
                    return;
                }
                // Überprüfen, ob das Haustier in der Nähe des Spielers ist und es bewegen
                if (pet.getLocation().distanceSquared(player.getLocation()) > 3) {
                    pet.teleport(player.getLocation());
                }
            }
        }.runTaskTimer(this.pathFinder, 0L, 20L);
    }
}