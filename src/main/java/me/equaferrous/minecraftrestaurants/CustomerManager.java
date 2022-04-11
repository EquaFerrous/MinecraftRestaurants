package me.equaferrous.minecraftrestaurants;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CustomerManager {

    private static CustomerManager instance;

    private List<Customer> customerList = new ArrayList<>();
    private HashMap<Integer, Double> customerTierWeights = new HashMap<>();
    private Random random = new Random();

    // --------------------------------------------------------------

    public CustomerManager() {
        if (instance == null) {
            instance = this;
        }

        SetupTierWeights();
    }

    // ----------------------------------------------------------------

    public static CustomerManager GetInstance() {
        return instance;
    }

    // ---------------------------------------------------------------

    public void SpawnCustomer(Location location) {
        Customer newCustomer = new Customer(location, GetRandomCustomerTier());
        customerList.add(newCustomer);
    }

    // -----------------------------------------------------------------

    // Sets up the dictionary of tier weights
    private void SetupTierWeights() {
        customerTierWeights.putIfAbsent(1, 40.0);
        customerTierWeights.putIfAbsent(2, 35.0);
        customerTierWeights.putIfAbsent(3, 25.0);
    }

    // Chooses a random customer tier
    private int GetRandomCustomerTier() {
        double totalWeight = 0;
        for (Double tierWeight : customerTierWeights.values()) {
            totalWeight += tierWeight;
        }

        double choice = random.nextDouble() * totalWeight;
        Bukkit.broadcastMessage(String.valueOf(choice));

        for (Integer tier : customerTierWeights.keySet()) {
            double weight = customerTierWeights.get(tier);
            if (choice <= weight) {
                return tier;
            }
            else {
                choice -= weight;
            }
        }
        return 0;
    }
}
