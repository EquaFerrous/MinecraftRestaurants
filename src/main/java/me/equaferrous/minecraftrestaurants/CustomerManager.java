package me.equaferrous.minecraftrestaurants;

import me.equaferrous.minecraftrestaurants.recipes.CustomerTrades;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CustomerManager {

    private static CustomerManager instance;

    private List<Customer> customerList = new ArrayList<>();
    private HashMap<Integer, Double> customerTierWeights = new HashMap<>();
    private Random random = new Random();
    private BukkitTask customerUpdateTask;

    // --------------------------------------------------------------

    public CustomerManager() {
        if (instance == null) {
            instance = this;
        }

        SetupTierWeights();
        customerUpdateTask = Bukkit.getScheduler().runTaskTimer(MinecraftRestaurants.GetInstance(), this::CustomerUpdate, 20, 20);
    }

    // ----------------------------------------------------------------

    public static CustomerManager GetInstance() {
        return instance;
    }

    // ---------------------------------------------------------------

    public void SpawnCustomer(Location location) {
        int tier = GetRandomCustomerTier();
        List<MerchantRecipe> customerOrders = new ArrayList<>();
        customerOrders.add(CustomerTrades.GetInstance().GetRandomOrder(tier));

        Customer newCustomer = new Customer(location, tier, customerOrders);
        customerList.add(newCustomer);
    }

    public void CustomerLeave(Customer customer) {
        customer.Leave();
        customerList.remove(customer);
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

    // Updates all active customers
    private void CustomerUpdate() {
        List<Customer> allCustomers = new ArrayList<>(customerList);
        for (Customer customer : allCustomers) {
            customer.Update();

            if (customer.CheckToLeave()) {
                CustomerLeave(customer);
            }
        }

    }
}
