package me.equaferrous.minecraftrestaurants.commands.standard;

import me.equaferrous.minecraftrestaurants.Restaurant;
import me.equaferrous.minecraftrestaurants.RestaurantManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RestaurantCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[INVALID SENDER] Command can only be executed by players.");
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.YELLOW+"---------- "+ChatColor.WHITE+"Restaurants Help"+ChatColor.YELLOW+" ----------\n" +
                                ChatColor.GOLD+"/restaurant info"+ChatColor.WHITE+": Get info about your restaurant\n" +
                                ChatColor.GOLD+"/restaurant create"+ChatColor.WHITE+": Create a new restaurant\n" +
                                ChatColor.GOLD+"/restaurant delete"+ChatColor.WHITE+": Delete your current restaurant\n" +
                                ChatColor.GOLD+"/restaurant open"+ChatColor.WHITE+": Open your restaurant to customers\n" +
                                ChatColor.GOLD+"/restaurant close"+ChatColor.WHITE+": Close your restaurant to customers\n" +
                                ChatColor.GOLD+"/restaurant members"+ChatColor.WHITE+": Commands for managing restaurant members\n" +
                                ChatColor.GOLD+"/restaurant update"+ChatColor.WHITE+": Commands for updating restaurant settings\n" +
                                ChatColor.YELLOW+"-----------------------------------");
            return true;
        }

        RestaurantManager restaurantManager = RestaurantManager.getInstance();

        if (args[0].equalsIgnoreCase("info")) {
            Restaurant restaurant = restaurantManager.getPlayerRestaurant(player);

            if (restaurant == null) {
                player.sendMessage(ChatColor.RED+"You do not have a restaurant. Create one using "+ChatColor.GOLD+"/restaurant create");
                return true;
            }
            if (args.length > 1) {
                player.sendMessage(ChatColor.RED+"Invalid command. Use "+ChatColor.GOLD+"/restaurant info");
                return true;
            }

            player.sendMessage(ChatColor.YELLOW+"---------- "+ChatColor.WHITE+"Restaurant Info"+ChatColor.YELLOW+" ----------\n" +
                    ChatColor.WHITE+"Name: "+ChatColor.YELLOW + restaurant.getName()+"\n" +
                    ChatColor.WHITE+"Level: "+ChatColor.YELLOW + restaurant.getLevel()+"\n" +
                    ChatColor.YELLOW+"-----------------------------------");
        }
        else if (args[0].equalsIgnoreCase("create")) {
            player.sendMessage("Create");
        }
        else if (args[0].equalsIgnoreCase("delete")) {
            player.sendMessage("Delete");
        }
        else if (args[0].equalsIgnoreCase("open")) {
            player.sendMessage("Open");
        }
        else if (args[0].equalsIgnoreCase("close")) {
            player.sendMessage("Close");
        }
        else if (args[0].equalsIgnoreCase("members")) {
            player.sendMessage("Members");
        }
        else if (args[0].equalsIgnoreCase("update")) {
            player.sendMessage("Update");
        }
        return true;
    }
}
