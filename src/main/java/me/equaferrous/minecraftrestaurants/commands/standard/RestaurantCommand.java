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
            player.sendMessage(ChatColor.LIGHT_PURPLE+"---------- "+ChatColor.WHITE+"Restaurants Help"+ChatColor.LIGHT_PURPLE+" ----------\n" +
                    ChatColor.WHITE+"Use "+ChatColor.GOLD+"/restaurant"+ChatColor.WHITE+" followed by:\n" +
                    ChatColor.GOLD+" info"+ChatColor.WHITE+": Get info about your restaurant\n" +
                    ChatColor.GOLD+" create"+ChatColor.WHITE+": Create a new restaurant\n" +
                    ChatColor.GOLD+" delete"+ChatColor.WHITE+": Delete your current restaurant\n" +
                    ChatColor.GOLD+" open"+ChatColor.WHITE+": Open your restaurant to customers\n" +
                    ChatColor.GOLD+" close"+ChatColor.WHITE+": Close your restaurant to customers\n" +
                    ChatColor.GOLD+" members"+ChatColor.WHITE+": Commands for managing restaurant members\n" +
                    ChatColor.GOLD+" update"+ChatColor.WHITE+": Commands for updating restaurant settings\n" +
                    ChatColor.LIGHT_PURPLE+"-----------------------------------");
            return true;
        }

        RestaurantManager restaurantManager = RestaurantManager.getInstance();

        if (args[0].equalsIgnoreCase("info")) {
            Restaurant restaurant = restaurantManager.getPlayerRestaurant(player);

            if (args.length > 1) {
                player.sendMessage(ChatColor.RED+"Invalid command. Use "+ChatColor.GOLD+"/restaurant info");
                return true;
            }
            if (restaurant == null) {
                player.sendMessage(ChatColor.RED+"You do not have a restaurant. Create one using "+ChatColor.GOLD+"/restaurant create");
                return true;
            }

            player.sendMessage(ChatColor.YELLOW+"---------- "+ChatColor.WHITE+"Restaurant Info"+ChatColor.YELLOW+" ----------\n" +
                    ChatColor.WHITE+"Name: "+ChatColor.LIGHT_PURPLE + restaurant.getName()+"\n" +
                    ChatColor.WHITE+"Level: "+ChatColor.LIGHT_PURPLE + restaurant.getLevel()+"\n" +
                    ChatColor.YELLOW+"-----------------------------------");
        }

        else if (args[0].equalsIgnoreCase("create")) {
            Restaurant restaurant = restaurantManager.getPlayerRestaurant(player);

            if (args.length > 1) {
                player.sendMessage(ChatColor.RED+"Invalid command. Use "+ChatColor.GOLD+"/restaurant create");
                return true;
            }
            if (!(restaurant == null)) {
                player.sendMessage(ChatColor.RED+"You already have a restaurant.");
                return true;
            }

            restaurantManager.createRestaurant(player);
            player.sendMessage(ChatColor.GREEN+"Restaurant created!");
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

        else {
            player.sendMessage(ChatColor.RED+"Invalid command. Use "+ChatColor.GOLD+"/restaurant"+ChatColor.RED+" for help.");
        }
        return true;
    }
}
