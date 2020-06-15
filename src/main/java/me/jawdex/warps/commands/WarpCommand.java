package me.jawdex.warps.commands;

import me.jawdex.warps.Warps;
import me.jawdex.warps.menu.WarpMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements ICommand {
    private Warps plugin;

    public WarpCommand(Warps plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String... args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this.");
            return;
        }
        Player player = (Player) sender;
        WarpMenu menu = new WarpMenu(plugin, player);
        player.openInventory(menu.getInventory());

    }
}
