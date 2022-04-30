package me.equaferrous.minecraftrestaurants.commands;

import me.equaferrous.minecraftrestaurants.RestaurantManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateRestaurant implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("CreateRestaurant")) {
                RestaurantManager restaurantManager = RestaurantManager.getInstance();

                if (restaurantManager.getPlayerRestaurant(player) == null) {
                    restaurantManager.createRestaurant(player);
                    player.sendMessage(ChatColor.GREEN + "Restaurant successfully created!");
                }
                else {
                    sender.sendMessage(ChatColor.RED + "You already have a restaurant!");
                }
            }
        }
        else {
            sender.sendMessage("[INVALID SENDER] Command can only be executed by players.");
        }

        return true;
    }
}
