package me.jawdex.warps.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

public class LocationUtils {

    public static Location getLocation(ConfigurationSection section) {
        World world = Bukkit.getWorld(section.getString("world"));
        double x = section.getDouble("x");
        double y = section.getDouble("y");
        double z = section.getDouble("z");
        if (section.contains("yaw") && section.contains("pitch"))
            return new Location(world, x, y, z, Float.parseFloat(section.getString("yaw")), Float.parseFloat(section.getString("pitch")));
        return new Location(world, x, y, z);
    }

    public static String getPrettyString(Location location) {
        return location.getWorld().getName() + ", " + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ();
    }

}
