package me.equaferrous.minecraftrestaurants.commands;

import me.equaferrous.minecraftrestaurants.Restaurant;
import me.equaferrous.minecraftrestaurants.RestaurantManager;
import me.equaferrous.minecraftrestaurants.Seat;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleRestaurantOpen implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Restaurant restaurant = RestaurantManager.getInstance().getPlayerRestaurant(player);
            if (!(restaurant == null)) {
                if (restaurant.getSeatManager().getAllSeats().size() > 0) {
                    if (restaurant.isOpen()) {
                        restaurant.CloseRestaurant();
                        sender.sendMessage(ChatColor.GREEN +"Your restaurant has closed!");
                    }
                    else {
                        restaurant.OpenRestaurant();
                        sender.sendMessage(ChatColor.GREEN +"Your restaurant has opened!");
                    }
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Your restaurant has no seats! Create some using /createSeat.");
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "You do not have a restaurant. Create one using /createRestaurant.");
            }
        }
        else {
            sender.sendMessage("[INVALID SENDER] Command can only be executed by players.");
        }

        return true;
    }
}
