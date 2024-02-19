package de.bukkitnews.pathfinder.listener;

import de.bukkitnews.pathfinder.PathFinder;
import de.bukkitnews.pathfinder.entity.CustomPet_Cat;
import de.bukkitnews.pathfinder.entity.CustomPet_Sheep;
import de.bukkitnews.pathfinder.entity.CustomPet_Wolf;
import de.bukkitnews.pathfinder.entity.PetConfiguration;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private final PathFinder pathFinder;

    public PlayerConnectionListener(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        if (pathFinder.getPetConfig().contains(playerUUID)) {
            String petType = pathFinder.getPetConfig().getString(playerUUID + ".type");

            if (petType != null) {
                Location spawnLocation = player.getLocation().add(2, 0, 2);

                switch (petType.toLowerCase()) {
                    case "cat":
                        CustomPet_Cat catPet = new CustomPet_Cat(pathFinder, player);
                        pathFinder.getPetMap().put(player.getUniqueId(), catPet);
                        break;
                    case "wolf":
                        CustomPet_Wolf wolfPet = new CustomPet_Wolf(pathFinder, player);
                        pathFinder.getPetMap().put(player.getUniqueId(), wolfPet);
                        break;
                    case "sheep":
                        CustomPet_Sheep sheepPet = new CustomPet_Sheep(pathFinder, player);
                        pathFinder.getPetMap().put(player.getUniqueId(), sheepPet);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        // Update pet position instead of despawning it
        if (pathFinder.getPetConfig().contains(playerUUID)) {
            Object petObject = pathFinder.getPetMap().get(player.getUniqueId());
            if (petObject != null && petObject instanceof PetConfiguration) {
                PetConfiguration pet = (PetConfiguration) petObject;
                pet.despawnPet();
            }
        }
    }
}