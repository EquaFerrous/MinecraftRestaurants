package me.equaferrous.minecraftrestaurants;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class Customer {

    private int tier;
    private Villager entity;
    private double expTimerIncrement;
    private double villagerExp;

    private boolean served = false;
    private double timeToLeave;
    private Seat seat;

    // ---------------------------------------------------------------------------

    public Customer(Seat seat, int tier, List<MerchantRecipe> orders) {
        this.seat = seat;
        Location location = this.seat.getLocation();
        entity = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);

        SetCustomerTier(tier);

        entity.setProfession(Villager.Profession.TOOLSMITH);
        entity.setRecipes(orders);
        entity.setAI(false);
        entity.addScoreboardTag("MinecraftRestaurants");
    }

    // -------------------------------------------------------------------------

    public void Leave() {
        if (served) {
            LeaveHappy();
        }
        else {
            LeaveBad();
        }
    }

    public boolean CheckToLeave() {
        if (timeToLeave <= 0 || CheckIfServed()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void Update() {
        timeToLeave -= 1;
        villagerExp = villagerExp - expTimerIncrement;
        UpdateVillagerExp();
    }

    public Seat getSeat() {
        return seat;
    }

    // -------------------------------------------------------------------------

    private void SetCustomerTier(int customerTier) {
        tier = Math.max(1, Math.min(3, customerTier));
        entity.setCustomName("Tier "+ tier +" Customer");

        if (tier == 1) {
            timeToLeave = 30;
            entity.setVillagerLevel(2);
            expTimerIncrement = 60 / timeToLeave;
            villagerExp = 69;
        }
        else if (tier == 2) {
            timeToLeave = 45;
            entity.setVillagerLevel(3);
            expTimerIncrement = 80 / timeToLeave;
            villagerExp = 149;
        }
        else if (tier == 3) {
            timeToLeave = 60;
            entity.setVillagerLevel(4);
            expTimerIncrement = 100 / timeToLeave;
            villagerExp = 249;
        }
        UpdateVillagerExp();
    }

    private boolean CheckIfServed() {
        boolean served = true;
        for (MerchantRecipe recipe : entity.getRecipes()) {
            if (recipe.getUses() < recipe.getMaxUses()) {
                served = false;
            }
        }
        this.served = served;
        return served;
    }

    private void LeaveHappy() {
        entity.remove();
        Bukkit.broadcastMessage("Customer served.");
        Location location = entity.getLocation();
        entity.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location.getX(), location.getY() + 0.75, location.getZ(), 25, 0.5, 0.75, 0.5);
    }

    private void LeaveBad() {
        entity.remove();
        Bukkit.broadcastMessage("Customer left.");
        Location location = entity.getLocation();
        entity.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, location.getX(), location.getY() + 0.75, location.getZ(), 7, 0.5, 0.75, 0.5);
    }

    private void UpdateVillagerExp() {
        entity.setVillagerExperience((int) villagerExp);
    }
}
