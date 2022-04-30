package me.equaferrous.minecraftrestaurants;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Restaurant {

    private CustomerManager customerManager;
    private SeatManager seatManager;

    private Player owner;
    private boolean open = false;

    private int level = 1;
    private String name;

    // -------------------------------------------------------

    public Restaurant(Player owner) {
        seatManager = new SeatManager(new ArrayList<>());
        customerManager = new CustomerManager(seatManager);

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
        }
    }

    // --------------------------------------------------------
}
