package me.jawdex.warps.menu;

import me.jawdex.warps.Warp;
import me.jawdex.warps.Warps;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WarpMenu implements InventoryHolder {
    private Warps plugin;
    private Player player;

    public WarpMenu(Warps plugin, Player player) {
        this.player = player;
        this.plugin = plugin;
    }

    @Override
    public Inventory getInventory() {
        ItemStack[] menuItems = plugin.getWarpManager().getWarps()
                .stream()
                .filter(w -> player.hasPermission("warps.use." + w.getName()))
                .map(this::getMenuItem)
                .toArray(ItemStack[]::new);
        int inventorySize = menuItems.length > 0 ? menuItems.length > 27 ? 54 : 27 : 9;
        Inventory inventory = Bukkit.createInventory(this, inventorySize, ChatColor.AQUA + "Warp Menu");
        if (inventorySize == 9) {
            inventory.setItem(4, getNoWarpsItem());
        } else {
            inventory.addItem(menuItems);
        }
        return inventory;
    }

    public ItemStack getMenuItem(Warp warp) {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', warp.getDisplayName()));
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "Click to warp to " + ChatColor.translateAlternateColorCodes('&', warp.getDisplayName())));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getNoWarpsItem() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "No Warps Found");
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "No available warps have been found."));
        item.setItemMeta(meta);
        return item;
    }
}
