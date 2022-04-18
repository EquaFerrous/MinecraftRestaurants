package me.equaferrous.minecraftrestaurants.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Rewards {

    private static Rewards instance;

    private List<ItemStack> tier1Rewards = new ArrayList<>();
    private List<ItemStack> tier2Rewards = new ArrayList<>();
    private List<ItemStack> tier3Rewards = new ArrayList<>();

    // -----------------------------------------------------

    public Rewards() {
        if (instance == null) {
            instance = this;
        }

        SetupT1Rewards();
        SetupT2Rewards();
        SetupT3Rewards();
    }

    public static Rewards GetInstance() {
        return instance;
    }

    // -------------------------------------------------------

    public List<ItemStack> GetT1Rewards() {
        return tier1Rewards;
    }

    public List<ItemStack> GetT2Rewards() {
        return tier2Rewards;
    }

    public List<ItemStack> GetT3Rewards() {
        return tier3Rewards;
    }

    // -----------------------------------------------------

    private void SetupT1Rewards() {
        tier1Rewards.add(new ItemStack(Material.EMERALD, 1));
    }

    private void SetupT2Rewards() {
        tier2Rewards.add(new ItemStack(Material.EMERALD, 2));
    }

    private void SetupT3Rewards() {
        tier3Rewards.add(new ItemStack(Material.EMERALD, 4));
    }
}
