package me.equaferrous.minecraftrestaurants;

import org.bukkit.Location;

public class Seat {

    private boolean occupied = false;
    private final Location location;

    // ---------------------------------------------

    public Seat(Location location) {
        this.location = location;
    }

    // ---------------------------------------------

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Location getLocation() {
        return location;
    }

    // -----------------------------------------------------
}
