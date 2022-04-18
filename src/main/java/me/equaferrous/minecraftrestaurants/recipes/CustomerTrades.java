package me.equaferrous.minecraftrestaurants.recipes;

import me.equaferrous.minecraftrestaurants.items.Orders;
import me.equaferrous.minecraftrestaurants.items.Rewards;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class CustomerTrades {

    private static CustomerTrades instance;

    private Orders orders;
    private Rewards rewards;

    private List<MerchantRecipe> tier1Orders = new ArrayList<>();
    private List<MerchantRecipe> tier2Orders = new ArrayList<>();
    private List<MerchantRecipe> tier3Orders = new ArrayList<>();

    // -----------------------------------------------------

    public CustomerTrades() {
        if (instance == null) {
            instance = this;
        }

        orders = new Orders();
        rewards = new Rewards();

        SetupT1Orders();
        SetupT2Orders();
        SetupT3Orders();
    }

    public static CustomerTrades GetInstance() {
        return instance;
    }

    // -------------------------------------------------------

    public List<MerchantRecipe> GetT1Orders() {
        return tier1Orders;
    }

    public List<MerchantRecipe> GetT2Orders() {
        return tier2Orders;
    }

    public List<MerchantRecipe> GetT3Orders() {
        return tier3Orders;
    }

    // -----------------------------------------------------

    private void SetupT1Orders() {
        for (ItemStack order : orders.GetT1Orders()) {
            for (ItemStack reward : rewards.GetT1Rewards()) {
                MerchantRecipe recipe = new MerchantRecipe(reward, 0, 1, false, 0, 0);
                recipe.addIngredient(order);
                tier1Orders.add(recipe);
            }
        }
    }

    private void SetupT2Orders() {
        for (ItemStack order : orders.GetT2Orders()) {
            for (ItemStack reward : rewards.GetT2Rewards()) {
                MerchantRecipe recipe = new MerchantRecipe(reward, 0, 1, false, 0, 0);
                recipe.addIngredient(order);
                tier1Orders.add(recipe);
            }
        }
    }

    private void SetupT3Orders() {
        for (ItemStack order : orders.GetT3Orders()) {
            for (ItemStack reward : rewards.GetT3Rewards()) {
                MerchantRecipe recipe = new MerchantRecipe(reward, 0, 1, false, 0, 0);
                recipe.addIngredient(order);
                tier1Orders.add(recipe);
            }
        }
    }
}
