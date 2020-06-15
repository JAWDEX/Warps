package me.jawdex.warps.utils;

import me.jawdex.warps.Warp;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportTimer extends BukkitRunnable {
    private Warp warp;
    private HumanEntity player;
    private int time;
    private Location startingLocation;

    public TeleportTimer(Warp warp, HumanEntity player, int time) {
        this.warp = warp;
        this.player = player;
        this.time = time;
        this.startingLocation = player.getLocation();
    }

    @Override
    public void run() {
        if (startingLocation.getBlockX() != player.getLocation().getBlockX() || startingLocation.getBlockY() != player.getLocation().getBlockY() ||
                startingLocation.getBlockZ() != player.getLocation().getBlockZ()) {
            player.sendMessage(ChatColor.RED + "You moved from your starting position, therefore the teleport has been cancelled.");
            cancel();
            return;
        }
        if (time == 0) {
            player.teleport(warp.getLocation());
            player.sendMessage(ChatColor.GRAY + "You have warped to " + ChatColor.translateAlternateColorCodes('&', warp.getDisplayName()));
            cancel();
            return;
        }
        player.sendMessage(ChatColor.GRAY + "There are " + ChatColor.AQUA + time + ChatColor.GRAY + " second" + (time == 1 ? "s" : "" + "until you will be teleported."));
        time--;
    }
}
