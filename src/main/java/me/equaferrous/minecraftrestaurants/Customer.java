package me.equaferrous.minecraftrestaurants;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int tier;
    private Villager entity;

    // ---------------------------------------------------------------------------

    public Customer(Location location, int tier) {
        entity = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        this.tier = tier;

        SetCustomerTier(tier);
        entity.setProfession(Villager.Profession.TOOLSMITH);

        entity.setAI(false);
        entity.addScoreboardTag("MinecraftRestaurants");

        MerchantRecipe trade = new MerchantRecipe(new ItemStack(Material.EMERALD, 1), 0, 1, false, 0, 0);
        trade.addIngredient(new ItemStack(Material.COOKIE, 1));
        List<MerchantRecipe> merchantRecipes = new ArrayList<>();
        merchantRecipes.add(trade);

        entity.setRecipes(merchantRecipes);
    }

    // -------------------------------------------------------------------------

    private void SetCustomerTier(int customerTier) {
        tier = Math.max(1, Math.min(3, customerTier));
        entity.setCustomName("Tier "+ tier +" Customer");

        if (tier == 1) {
            entity.setVillagerLevel(2);
        }
        else if (tier == 2) {
            entity.setVillagerLevel(3);
        }
        else if (tier == 3) {
            entity.setVillagerLevel(4);
        }
    }
}
