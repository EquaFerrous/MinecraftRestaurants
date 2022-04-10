package me.equaferrous.minecraftrestaurants.commands;

import me.equaferrous.minecraftrestaurants.Customer;
import me.equaferrous.minecraftrestaurants.CustomerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateCustomer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("createcustomer")) {
            CustomerManager.GetInstance().SpawnCustomer(player.getLocation());
        }

        return true;
    }
}
