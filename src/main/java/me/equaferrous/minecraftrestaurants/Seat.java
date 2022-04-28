package me.equaferrous.minecraftrestaurants;

import org.bukkit.Location;

public class Seat {

    private boolean occupied = false;
    private Customer occupant;
    private final Location location;

    // ---------------------------------------------

    public Seat(Location location) {
        this.location = location;
    }

    // ---------------------------------------------

    public void setOccupant(Customer customer) {
        occupant = customer;
        occupied = true;
    }

    public void removeOccupant() {
        occupant = null;
        occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Customer getOccupant() {
        return occupant;
    }

    public Location getLocation() {
        return location;
    }

    // -----------------------------------------------------
}
