package me.jawdex.warps.commands;

import me.jawdex.warps.Warps;
import me.jawdex.warps.utils.LocationUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateWarpCommand implements ICommand {
    private Warps plugin;

    public CreateWarpCommand(Warps plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String... args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("warps.create")) {
                player.sendMessage(ChatColor.RED + "You do not have permission to use that.");
                return;
            }
            if (args.length > 0) {
                String warpName = ChatColor.translateAlternateColorCodes('&', args[0]);
                int time = 0;
                if (plugin.getWarpManager().warpIsLoaded(ChatColor.stripColor(warpName))) {
                    player.sendMessage(ChatColor.RED + "That warp already exists.");
                    return;
                }
                if (args.length > 1) {
                    try {
                        time = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "If you wish to add a teleport delay, please specify a valid number.");
                        return;
                    }
                }
                plugin.getWarpManager().addWarp(ChatColor.stripColor(warpName), warpName, player.getLocation(), time);
                player.sendMessage(ChatColor.GRAY + "You have added a warp called " + ChatColor.AQUA + warpName + ChatColor.GRAY + " at " + ChatColor.AQUA
                        + LocationUtils.getPrettyString(player.getLocation()));
            }
        }
    }
}
