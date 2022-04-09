package me.equaferrous.minecraftrestaurants;

import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftRestaurants extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getConsoleSender().sendMessage("[Restaurants] Enabled successfully");
    }

    @Override
    public void onDisable() {
        
        getServer().getConsoleSender().sendMessage("[Restaurants] Enabled successfully");
    }
}
