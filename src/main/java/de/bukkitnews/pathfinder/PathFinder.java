package de.bukkitnews.pathfinder;

import de.bukkitnews.pathfinder.command.PetCommand;
import de.bukkitnews.pathfinder.entity.CustomPet_Zombie;
import org.bukkit.plugin.java.JavaPlugin;

public class PathFinder extends JavaPlugin {

    private CustomPet_Zombie customPetZombie;

    @Override
    public void onEnable(){
        this.customPetZombie = new CustomPet_Zombie(this);

        initCommands();
    }

    @Override
    public void onDisable(){

    }

    private void initCommands(){
        this.getCommand("pet").setExecutor(new PetCommand(this));
    }
}
