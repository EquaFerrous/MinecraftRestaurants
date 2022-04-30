package me.equaferrous.minecraftrestaurants;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class Seat {

    private boolean occupied = false;
    private final Block block;

    // ---------------------------------------------

    public Seat(Location location) {
        block = location.getWorld().getBlockAt(location);
    }

    // ---------------------------------------------

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Location getLocation() {
        return block.getLocation();
    }

    public Block getBlock() {
        return block;
    }

    // -----------------------------------------------------
}
