package me.equaferrous.minecraftrestaurants;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class SeatManager {

    private final List<Seat> allSeats = new ArrayList<>();
    private final List<Seat> availableSeats = new ArrayList<>();

    // ------------------------------------------------

    public SeatManager(List<Location> seatLocations) {
        for (Location location : seatLocations) {
            createSeat(location);
        }
    }

    // ------------------------------------------------

    public void occupySeat(Seat seat) {
        availableSeats.remove(seat);
        seat.setOccupied(true);
    }

    public void emptySeat(Seat seat) {
        availableSeats.add(seat);
        seat.setOccupied(false);
    }

    public void createSeat(Location location) {
        allSeats.add(new Seat(location));
        availableSeats.add(new Seat(location));
    }

    public void removeSeat(Seat seat) {
        allSeats.remove(seat);
        availableSeats.remove(seat);
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public List<Seat> getAllSeats() {
        return allSeats;
    }

    // ------------------------------------------------
}
