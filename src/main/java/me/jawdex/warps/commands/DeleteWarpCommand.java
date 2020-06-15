package me.jawdex.warps.commands;

import me.jawdex.warps.Warp;
import me.jawdex.warps.Warps;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteWarpCommand implements ICommand {
    private Warps plugin;

    public DeleteWarpCommand(Warps plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String... args) {
        if (!sender.hasPermission("warps.remove")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use that.");
            return;
        }
        if (args.length > 0) {
            Warp warp = plugin.getWarpManager().getWarp(args[0]);
            if (warp == null) {
                sender.sendMessage(ChatColor.RED + "That warp doesn't exist.");
                return;
            }
            plugin.getWarpManager().deleteWarp(warp);
            sender.sendMessage(ChatColor.GRAY + "The warp " + ChatColor.AQUA + args[0] + ChatColor.GRAY + " has been deleted.");
        }
    }
}
