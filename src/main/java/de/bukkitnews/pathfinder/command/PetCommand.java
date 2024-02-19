package de.bukkitnews.pathfinder.command;

import de.bukkitnews.pathfinder.PathFinder;
import de.bukkitnews.pathfinder.entity.CustomPet_Cat;
import de.bukkitnews.pathfinder.entity.CustomPet_Sheep;
import de.bukkitnews.pathfinder.entity.CustomPet_Wolf;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PetCommand implements CommandExecutor {

    private final PathFinder pathFinder;
    private final Map<UUID, Object> petMap;

    public PetCommand(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
        this.petMap = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("pet")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("cat")) {
                    spawnCatPet(player);
                    return true;
                } else if (args[0].equalsIgnoreCase("wolf")) {
                    spawnWolfPet(player);
                    return true;
                } else if (args[0].equalsIgnoreCase("sheep")) {
                    spawnSheepPet(player);
                    return true;
                }
            }
            sender.sendMessage("Usage: /pet <cat|wolf|sheep>");
            return true;
        }

        return false;
    }

    private void spawnCatPet(Player player) {
        Location spawnLocation = player.getLocation().add(2, 0, 2);
        CustomPet_Cat catPet = new CustomPet_Cat(pathFinder, player);
        if (catPet.getPet() != null) {
            petMap.put(catPet.getPet().getUniqueId(), catPet);
            pathFinder.getPetConfig().set(player.getUniqueId().toString() + ".type", "cat"); // Save pet type to config
            player.sendMessage("Cat pet spawned!");
        } else {
            // Wenn das Pet null ist, ein neues Pet spawnen und in der Config eintragen
            CustomPet_Cat newCatPet = new CustomPet_Cat(pathFinder, player);
            if (newCatPet.getPet() != null) {
                petMap.put(newCatPet.getPet().getUniqueId(), newCatPet);
                pathFinder.getPetConfig().set(player.getUniqueId().toString() + ".type", "cat"); // Save pet type to config
                player.sendMessage("Cat pet spawned!");
            }
        }
    }

    private void spawnWolfPet(Player player) {
        Location spawnLocation = player.getLocation().add(2, 0, 2);
        CustomPet_Wolf wolfPet = new CustomPet_Wolf(pathFinder, player);
        if (wolfPet.getPet() != null) {
            petMap.put(wolfPet.getPet().getUniqueId(), wolfPet);
            pathFinder.getPetConfig().set(player.getUniqueId().toString() + ".type", "wolf"); // Save pet type to config
            player.sendMessage("Wolf pet spawned!");
        } else {
            // Wenn das Pet null ist, ein neues Pet spawnen und in der Config eintragen
            CustomPet_Wolf newWolfPet = new CustomPet_Wolf(pathFinder, player);
            if (newWolfPet.getPet() != null) {
                petMap.put(newWolfPet.getPet().getUniqueId(), newWolfPet);
                pathFinder.getPetConfig().set(player.getUniqueId().toString() + ".type", "wolf"); // Save pet type to config
                player.sendMessage("Wolf pet spawned!");
            }
        }
    }

    private void spawnSheepPet(Player player) {
        Location spawnLocation = player.getLocation().add(2, 0, 2);
        CustomPet_Sheep sheepPet = new CustomPet_Sheep(pathFinder, player);
        if (sheepPet.getPet() != null) {
            petMap.put(sheepPet.getPet().getUniqueId(), sheepPet);
            pathFinder.getPetConfig().set(player.getUniqueId().toString() + ".type", "sheep"); // Save pet type to config
            player.sendMessage("Sheep pet spawned!");
        } else {
            // Wenn das Pet null ist, ein neues Pet spawnen und in der Config eintragen
            CustomPet_Sheep newSheepPet = new CustomPet_Sheep(pathFinder, player);
            if (newSheepPet.getPet() != null) {
                petMap.put(newSheepPet.getPet().getUniqueId(), newSheepPet);
                pathFinder.getPetConfig().set(player.getUniqueId().toString() + ".type", "sheep"); // Save pet type to config
                player.sendMessage("Sheep pet spawned!");
            }
        }
    }



    public Map<UUID, Object> getPetMap() {
        return petMap;
    }
}
