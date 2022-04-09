package me.equaferrous.minecraftrestaurants.commands;

import me.equaferrous.minecraftrestaurants.Customer;
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
            new Customer(player.getLocation(), 1);
            new Customer(player.getLocation().add(2,0,0), 2);
            new Customer(player.getLocation().add(4,0,0), 3);
        }
        return true;
    }
}
