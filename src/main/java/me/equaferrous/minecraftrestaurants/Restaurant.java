package me.equaferrous.minecraftrestaurants;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private CustomerManager customerManager;

    private Player owner;
    private List<Player> moderators = new ArrayList<>();
    private boolean open = false;
    private Location location;

    private int level = 1;
    private String name;

    // -------------------------------------------------------

    public Restaurant(Player owner, Location location) {
        customerManager = new CustomerManager();
        this.owner = owner;
        AddModerator(owner);
        SetLocation(location);

        name = this.owner.getDisplayName() + "'s Restaurant";
    }

    // --------------------------------------------------------

    public void AddModerator(Player moderator) {
        moderators.add(moderator);
    }

    public void OpenRestaurant() {
        if (!open) {
            open = true;
        }
    }

    public void CloseRestaurant() {
        if (open) {
            open = false;
        }
    }

    public void SetLocation(Location location) {
        this.location = location;
    }

    // --------------------------------------------------------
}
