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

public class CreateSeat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Restaurant restaurant = RestaurantManager.getInstance().getPlayerRestaurant(player);
            if (!(restaurant == null)) {
                Location location = player.getLocation();
                Seat seat = restaurant.getSeatManager().createSeat(location);
                if (!(seat == null)) {
                    String positionText = location.getBlockX() +" "+ location.getBlockY() +" "+ location.getBlockZ();
                    sender.sendMessage(ChatColor.GREEN + "Seat successfully created at "+ positionText +".");
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Another seat already occupies this position.");
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
