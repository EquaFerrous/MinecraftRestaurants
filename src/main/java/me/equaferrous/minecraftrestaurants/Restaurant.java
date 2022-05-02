package me.equaferrous.minecraftrestaurants;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private final CustomerManager customerManager;
    private final SeatManager seatManager;

    private Player owner;
    private boolean open = false;

    private int level = 1;
    private String name;

    // -------------------------------------------------------

    public Restaurant(Player owner) {
        List<Player> messageRecipients = new ArrayList<>();
        messageRecipients.add(owner);

        seatManager = new SeatManager(new ArrayList<>());
        customerManager = new CustomerManager(seatManager, messageRecipients);

        this.owner = owner;

        name = this.owner.getDisplayName() + "'s Restaurant";
    }

    // --------------------------------------------------------

    public void OpenRestaurant() {
        if (!open) {
            open = true;
            customerManager.startCustomerSpawning();
        }
    }

    public void CloseRestaurant() {
        if (open) {
            open = false;
            customerManager.stopCustomerSpawning();
            
            List<Customer> allCustomers = new ArrayList<>(customerManager.getAllCustomers());
            for (Customer customer : allCustomers) {
                customerManager.CustomerLeave(customer);
            }
        }
    }

    public SeatManager getSeatManager() {
        return seatManager;
    }

    public boolean isOpen() {
        return open;
    }

    // --------------------------------------------------------
}
