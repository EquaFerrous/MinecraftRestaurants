package me.equaferrous.minecraftrestaurants;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {

    private static CustomerManager instance;

    private List<Customer> customerList = new ArrayList<>();

    // --------------------------------------------------------------

    public CustomerManager() {
        if (instance == null) {
            instance = this;
        }
    }

    // ----------------------------------------------------------------

    public static CustomerManager GetInstance() {
        return instance;
    }

    // ---------------------------------------------------------------

    public void SpawnCustomer(Location location) {
        Customer newCustomer = new Customer(location, 1);
        customerList.add(newCustomer);
    }
}
