package me.equaferrous.minecraftrestaurants.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Orders {

    private List<ItemStack> tier1Orders = new ArrayList<>();
    private List<ItemStack> tier2Orders = new ArrayList<>();
    private List<ItemStack> tier3Orders = new ArrayList<>();

    // -----------------------------------------------------

    public Orders() {
        SetupT1Orders();
        SetupT2Orders();
        SetupT3Orders();
    }

    // -------------------------------------------------------

    public List<ItemStack> GetT1Orders() {
        return tier1Orders;
    }

    public List<ItemStack> GetT2Orders() {
        return tier2Orders;
    }

    public List<ItemStack> GetT3Orders() {
        return tier3Orders;
    }

    // -----------------------------------------------------

    private void SetupT1Orders() {
        tier1Orders.add(new ItemStack(Material.APPLE, 4));
        tier1Orders.add(new ItemStack(Material.MELON_SLICE, 16));
        tier1Orders.add(new ItemStack(Material.SWEET_BERRIES, 8));
        tier1Orders.add(new ItemStack(Material.CARROT, 8));

        tier1Orders.add(new ItemStack(Material.COOKIE, 8));
        tier1Orders.add(new ItemStack(Material.BAKED_POTATO, 2));
        tier1Orders.add(new ItemStack(Material.BREAD, 2));

        tier1Orders.add(new ItemStack(Material.COOKED_CHICKEN, 1));
        tier1Orders.add(new ItemStack(Material.COOKED_COD, 1));
        tier1Orders.add(new ItemStack(Material.COOKED_MUTTON, 1));
        tier1Orders.add(new ItemStack(Material.COOKED_PORKCHOP, 1));
        tier1Orders.add(new ItemStack(Material.COOKED_SALMON, 1));
        tier1Orders.add(new ItemStack(Material.COOKED_BEEF, 1));
        tier1Orders.add(new ItemStack(Material.COOKED_RABBIT, 1));
    }

    private void SetupT2Orders() {
        tier2Orders.add(new ItemStack(Material.COOKIE, 32));
        tier2Orders.add(new ItemStack(Material.BAKED_POTATO, 8));
        tier2Orders.add(new ItemStack(Material.BREAD, 6));

        tier2Orders.add(new ItemStack(Material.BEETROOT_SOUP, 2));
        tier2Orders.add(new ItemStack(Material.CAKE, 1));
        tier2Orders.add(new ItemStack(Material.MUSHROOM_STEW, 2));
        tier2Orders.add(new ItemStack(Material.PUMPKIN_PIE, 2));

        tier2Orders.add(new ItemStack(Material.COOKED_CHICKEN, 4));
        tier2Orders.add(new ItemStack(Material.COOKED_COD, 4));
        tier2Orders.add(new ItemStack(Material.COOKED_MUTTON, 4));
        tier2Orders.add(new ItemStack(Material.COOKED_PORKCHOP, 4));
        tier2Orders.add(new ItemStack(Material.COOKED_SALMON, 4));
        tier2Orders.add(new ItemStack(Material.COOKED_BEEF, 4));
        tier2Orders.add(new ItemStack(Material.COOKED_RABBIT, 4));
    }

    private void SetupT3Orders() {
        tier3Orders.add(new ItemStack(Material.BEETROOT_SOUP, 6));
        tier3Orders.add(new ItemStack(Material.CAKE, 3));
        tier3Orders.add(new ItemStack(Material.MUSHROOM_STEW, 4));
        tier3Orders.add(new ItemStack(Material.PUMPKIN_PIE, 4));

        tier3Orders.add(new ItemStack(Material.RABBIT_STEW, 2));
        tier3Orders.add(new ItemStack(Material.CHORUS_FRUIT, 8));
        tier3Orders.add(new ItemStack(Material.GOLDEN_APPLE, 1));
        tier3Orders.add(new ItemStack(Material.GOLDEN_CARROT, 4));

        tier3Orders.add(new ItemStack(Material.COOKED_CHICKEN, 8));
        tier3Orders.add(new ItemStack(Material.COOKED_COD, 8));
        tier3Orders.add(new ItemStack(Material.COOKED_MUTTON, 8));
        tier3Orders.add(new ItemStack(Material.COOKED_PORKCHOP, 8));
        tier3Orders.add(new ItemStack(Material.COOKED_SALMON, 8));
        tier3Orders.add(new ItemStack(Material.COOKED_BEEF, 8));
        tier3Orders.add(new ItemStack(Material.COOKED_RABBIT, 8));
    }
}
