package me.jawdex.warps.menu;

import me.jawdex.warps.Warp;
import me.jawdex.warps.Warps;
import me.jawdex.warps.utils.TeleportTimer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {
    private Warps plugin;

    public MenuListener(Warps plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() instanceof WarpMenu) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
                Warp warp = plugin.getWarpManager().getWarp(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
                if (warp != null) {
                    event.getWhoClicked().closeInventory();
                    if (warp.getTimeToTeleport() > 0) {
                        new TeleportTimer(warp, event.getWhoClicked(), warp.getTimeToTeleport()).runTaskTimer(plugin, 1L, 20L);
                        return;
                    }
                    event.getWhoClicked().teleport(warp.getLocation());
                    event.getWhoClicked().sendMessage(ChatColor.GRAY + "You have warped to " + ChatColor.translateAlternateColorCodes('&', warp.getDisplayName()));
                }
            }
        }
    }
}
