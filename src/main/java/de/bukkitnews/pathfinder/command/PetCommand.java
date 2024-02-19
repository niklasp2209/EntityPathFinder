package de.bukkitnews.pathfinder.command;

import de.bukkitnews.pathfinder.PathFinder;
import de.bukkitnews.pathfinder.entity.CustomPet_Zombie;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PetCommand implements CommandExecutor {

    private final PathFinder pathFinder;

    public PetCommand(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command.");
            return true;
        }

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("pet") && args.length == 1 && args[0].equalsIgnoreCase("zombie")) {
            spawnZombiePet(player);
            return true;
        }

        return false;
    }

    private void spawnZombiePet(Player player) {
        Location spawnLocation = player.getLocation().add(2, 0, 2);

        CustomPet_Zombie zombiePet = new CustomPet_Zombie(player);
        zombiePet.spawnPet(spawnLocation);

        player.sendMessage("Zombie pet spawned!");
    }
}