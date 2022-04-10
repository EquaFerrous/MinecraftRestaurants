package me.equaferrous.minecraftrestaurants;

import me.equaferrous.minecraftrestaurants.commands.CreateCustomer;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class MinecraftRestaurants extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("createcustomer").setExecutor(new CreateCustomer());

        new CustomerManager();

        getServer().getConsoleSender().sendMessage("[Restaurants] Enabled successfully");
    }

    @Override
    public void onDisable() {

        List<World> allWorlds = getServer().getWorlds();
        for (World world : allWorlds) {
            List<Entity> allEntities = world.getEntities();
            for (Entity entity : allEntities) {
                if (entity.getScoreboardTags().contains("MinecraftRestaurants")) {
                    entity.remove();
                }
            }
        }

        getServer().getConsoleSender().sendMessage("[Restaurants] Disabled successfully");
    }
}
