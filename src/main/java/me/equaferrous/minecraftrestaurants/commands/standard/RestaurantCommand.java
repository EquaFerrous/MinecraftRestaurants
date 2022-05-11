package me.equaferrous.minecraftrestaurants.commands.standard;

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
            player.sendMessage("---- Minecraft Restaurants Help ----\n" +
                                "/restaurant info -> Displays info about your restaurant\n" +
                                "/restaurant create -> Creates a new restaurant\n" +
                                "/restaurant delete -> Deletes your current restaurant\n" +
                                "/restaurant open -> Opens your restaurant to customers\n" +
                                "/restaurant close -> Closes your restaurant to customers\n" +
                                "/restaurant members -> Add or remove other players from your restaurant\n" +
                                "/restaurant update -> Update the name or location of your restaurant");
        }
        return true;
    }
}
