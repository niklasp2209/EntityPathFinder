package de.bukkitnews.pathfinder.entity;

import de.bukkitnews.pathfinder.PathFinder;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomPet_Zombie {

    private Player player;
    private Zombie pet;
    private PathFinder pathFinder;

    public CustomPet_Zombie(PathFinder pathFinder){
        this.pathFinder = pathFinder;
    }

    public CustomPet_Zombie(Player player) {
        this.player = player;
        this.pet = (Zombie) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
        this.pet.setBaby(true);
        this.pet.setCustomName(player.getName() + "'s Pet");
        this.pet.setCustomNameVisible(true);
        this.pet.setMetadata("custom_pet_owner", new FixedMetadataValue(this.pathFinder, player.getUniqueId().toString()));
        this.pet.setInvulnerable(true);
        this.pet.setRemoveWhenFarAway(false);
        this.pet.setSilent(true);
        this.pet.setCanPickupItems(false);
    }

    public void followOwner() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!pet.isValid() || pet.isDead() || !player.isValid() || !player.isOnline()) {
                    cancel();
                    return;
                }
                pet.setTarget(player);
            }
        }.runTaskTimer(this.pathFinder, 0L, 20L);
    }

    public void spawnPet(Location location) {
        this.pet = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        this.pet.setBaby(true);
        this.pet.setCustomName(player.getName() + "'s Pet");
        this.pet.setCustomNameVisible(true);
        this.pet.setMetadata("custom_pet_owner", new FixedMetadataValue(this.pathFinder, player.getUniqueId().toString()));
        this.pet.setInvulnerable(true);
        this.pet.setRemoveWhenFarAway(false);
        this.pet.setSilent(true);
        this.pet.setCanPickupItems(false);

        followOwner();
    }


    public void despawnPet() {
        if (pet != null && !pet.isDead()) {
            pet.remove();
        }
    }

    public Zombie getPet() {
        return this.pet;
    }

}