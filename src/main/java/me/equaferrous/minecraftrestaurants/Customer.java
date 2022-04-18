package me.equaferrous.minecraftrestaurants;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class Customer {

    private int tier;
    private Villager entity;
    private BukkitTask tickTask;

    // ---------------------------------------------------------------------------

    public Customer(Location location, int tier, List<MerchantRecipe> orders) {
        entity = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);

        SetCustomerTier(tier);

        entity.setProfession(Villager.Profession.TOOLSMITH);
        entity.setRecipes(orders);
        entity.setAI(false);
        entity.addScoreboardTag("MinecraftRestaurants");

        tickTask = Bukkit.getScheduler().runTaskTimer(MinecraftRestaurants.GetInstance(), this::CustomerTickCheck, 0, 20);
    }

    // -------------------------------------------------------------------------

    public void Leave() {
        if (CheckIfServed()) {
            LeaveHappy();
        }
    }

    public Villager GetVillager() {
        return entity;
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

    private void CustomerTickCheck() {
        if (CheckIfServed()) {
            tickTask.cancel();
            tickTask = null;
            LeaveHappy();
        }
    }

    private boolean CheckIfServed() {
        boolean finished = true;
        for (MerchantRecipe recipe : entity.getRecipes()) {
            if (recipe.getUses() < recipe.getMaxUses()) {
                finished = false;
            }
        }
        return finished;
    }

    private void LeaveHappy() {
        entity.remove();
        Bukkit.broadcastMessage("Customer served.");
    }
}
