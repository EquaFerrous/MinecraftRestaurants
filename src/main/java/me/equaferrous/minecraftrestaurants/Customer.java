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
    private BukkitTask tickTask;
    private int timeToLeave;
    private int expTimerIncrement;

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
            timeToLeave = 30;
            entity.setVillagerLevel(2);
            expTimerIncrement = 60 / timeToLeave;
            entity.setVillagerExperience(69);
        }
        else if (tier == 2) {
            timeToLeave = 45;
            entity.setVillagerLevel(3);
            expTimerIncrement = 80 / timeToLeave;
            entity.setVillagerExperience(149);
        }
        else if (tier == 3) {
            timeToLeave = 60;
            entity.setVillagerLevel(4);
            expTimerIncrement = 100 / timeToLeave;
            entity.setVillagerExperience(249);
        }
    }

    private void CustomerTickCheck() {
        if (CheckIfServed()) {
            CancelTickTask();
            LeaveHappy();
        }

        timeToLeave -= 1;
        if (timeToLeave <= 0) {
            CancelTickTask();
            LeaveBad();
        }
        else {
            entity.setVillagerExperience(entity.getVillagerExperience() - expTimerIncrement);
        }
    }

    private void CancelTickTask() {
        tickTask.cancel();
        tickTask = null;
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
        Location location = entity.getLocation();
        entity.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location.getX(), location.getY() + 0.75, location.getZ(), 25, 0.5, 0.75, 0.5);
    }

    private void LeaveBad() {
        entity.remove();
        Bukkit.broadcastMessage("Customer left.");
        Location location = entity.getLocation();
        entity.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, location.getX(), location.getY() + 0.75, location.getZ(), 7, 0.5, 0.75, 0.5);
    }
}
