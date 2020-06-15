package me.jawdex.warps.manager;

import me.jawdex.warps.Warp;
import me.jawdex.warps.Warps;
import me.jawdex.warps.utils.LocationUtils;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class WarpManager {
    private Warps plugin;
    @Getter List<Warp> warps = new ArrayList<>();

    public WarpManager(Warps plugin) {
        this.plugin = plugin;
        loadWarps();
    }

    private void loadWarps() {
        ConfigurationSection config = plugin.getConfig();
        if (config.contains("warps")) {
            for (String s : config.getKeys(false)) {
                if (warps.stream().anyMatch(w -> w.getName().equalsIgnoreCase(s))) {
                    System.out.println(ChatColor.GRAY + "Tried to add warp " + ChatColor.AQUA + s + ChatColor.GRAY + " but it already exists...");
                    continue;
                }
                ConfigurationSection warpSection = config.getConfigurationSection("warps." + s);
                if (warpSection.contains("location")) {
                    Warp warp = new Warp(s, warpSection.getString("display-name"), LocationUtils.getLocation(warpSection.getConfigurationSection("location")), warpSection.getInt("time-to-warp", 0));
                    warps.add(warp);
                    System.out.println(ChatColor.GRAY + "Loaded and enabled warp " + ChatColor.AQUA + s);
                    continue;
                }
                System.out.println(ChatColor.GRAY + "Tried to add warp " + ChatColor.AQUA + s + ChatColor.GRAY + " but there was some missing information...");
            }
        }
    }

    public Warp getWarp(String name) {
        return warps.stream().filter(w -> w.getName().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    public void addWarp(String name, String displayName, Location location, int timeToWarp) {
        warps.add(new Warp(name, displayName, location, timeToWarp));
        ConfigurationSection section = plugin.getConfig().createSection("warps." + name);
        section.set("display-name", displayName);
        section.set("location.x", location.getX());
        section.set("location.y", location.getY());
        section.set("location.z", location.getZ());
        section.set("location.yaw", location.getYaw());
        section.set("location.pitch", location.getPitch());
        section.set("time-to-warp", timeToWarp);
        plugin.saveConfig();
    }

    public void deleteWarp(Warp warp) {
        plugin.getConfig().set("warps." + warp.getName(), null);
        warps.remove(warp);
    }

    public boolean warpIsLoaded(String name) {
        return warps.stream().anyMatch(w -> w.getName().equalsIgnoreCase(name));
    }
}
