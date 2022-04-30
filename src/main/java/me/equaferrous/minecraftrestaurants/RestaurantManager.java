package me.equaferrous.minecraftrestaurants;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class RestaurantManager {

    private static RestaurantManager instance;
    private HashMap<Player, Restaurant> playerRestaurants = new HashMap<>();

    // ---------------------------------------------------

    public RestaurantManager() {
        if (instance == null) {
            instance = this;
        }
    }

    // ----------------------------------------------------

    public static RestaurantManager getInstance() {
        return instance;
    }

    // -----------------------------------------------------

    public void createRestaurant(Player owner) {
        if (getPlayerRestaurant(owner) == null) {
            playerRestaurants.put(owner, new Restaurant(owner));
        }
    }

    public Restaurant getPlayerRestaurant(Player owner) {
        return playerRestaurants.get(owner);
    }
}
