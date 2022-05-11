package me.equaferrous.minecraftrestaurants;

import me.equaferrous.minecraftrestaurants.commands.CreateRestaurant;
import me.equaferrous.minecraftrestaurants.commands.CreateSeat;
import me.equaferrous.minecraftrestaurants.commands.ToggleRestaurantOpen;
import me.equaferrous.minecraftrestaurants.commands.standard.RestaurantCommand;
import me.equaferrous.minecraftrestaurants.recipes.CustomerTrades;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class MinecraftRestaurants extends JavaPlugin {

    private static Plugin instance;

    // ------------------------------------------------------------------

    @Override
    public void onEnable() {
        instance = this;

        new CustomerTrades();
        new RestaurantManager();

        this.getCommand("createRestaurant").setExecutor(new CreateRestaurant());
        this.getCommand("createSeat").setExecutor(new CreateSeat());
        this.getCommand("toggleRestaurantOpen").setExecutor(new ToggleRestaurantOpen());
        this.getCommand("restaurant").setExecutor(new RestaurantCommand());

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

    // -------------------------------------------------------------------------

    public static Plugin GetInstance() {
        return instance;
    }
}
