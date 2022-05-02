package me.equaferrous.minecraftrestaurants;

import me.equaferrous.minecraftrestaurants.recipes.CustomerTrades;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CustomerManager {

    private final SeatManager seatManager;

    private final List<Customer> allCustomers = new ArrayList<>();
    private final HashMap<Integer, Double> customerTierWeights = new HashMap<>();
    private final Random random = new Random();
    private BukkitTask customerUpdateTask;

    private BukkitTask customerSpawnTask;
    private final int maxSpawnTime = 15;

    private final List<Player> messageRecipients;

    // --------------------------------------------------------------

    public CustomerManager(SeatManager seatManager, List<Player> messageRecipients) {
        this.seatManager = seatManager;
        this.messageRecipients = messageRecipients;

        SetupTierWeights();
        customerUpdateTask = Bukkit.getScheduler().runTaskTimer(MinecraftRestaurants.GetInstance(), this::CustomerUpdate, 20, 20);
    }

    // ----------------------------------------------------------------

    public void startCustomerSpawning() {
        customerSpawnTask = Bukkit.getScheduler().runTaskTimer(MinecraftRestaurants.GetInstance(), this::spawnRandomCustomer, 0, maxSpawnTime * 20);
    }

    public void stopCustomerSpawning() {
        customerSpawnTask.cancel();
    }

    // Spawns a customer
    public void spawnRandomCustomer() {
        int tier = GetRandomCustomerTier();
        List<MerchantRecipe> orders = new ArrayList<>();
        orders.add(CustomerTrades.GetInstance().GetRandomOrder(tier));

        List<Seat> availableSeats = seatManager.getAvailableSeats();
        if (availableSeats.size() > 0) {
            Seat seat = availableSeats.get(random.nextInt(availableSeats.size()));
            createCustomer(seat, tier, orders);
        }
    }

    public void CustomerLeave(Customer customer) {
        customer.Leave(messageRecipients);
        allCustomers.remove(customer);
        seatManager.emptySeat(customer.getSeat());
    }

    public List<Customer> getAllCustomers() {
        return allCustomers;
    }

    // -----------------------------------------------------------------

    // Sets up the dictionary of tier weights
    private void SetupTierWeights() {
        customerTierWeights.putIfAbsent(1, 40.0);
        customerTierWeights.putIfAbsent(2, 35.0);
        customerTierWeights.putIfAbsent(3, 25.0);
    }

    private void createCustomer(Seat seat, int tier, List<MerchantRecipe> orders) {
        Customer newCustomer = new Customer(seat, tier, orders);
        seatManager.occupySeat(seat);
        allCustomers.add(newCustomer);
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
        List<Customer> allCustomers = new ArrayList<>(this.allCustomers);
        for (Customer customer : allCustomers) {
            customer.Update();

            if (customer.CheckToLeave()) {
                CustomerLeave(customer);
            }
        }

    }
}
