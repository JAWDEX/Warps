package me.jawdex.warps.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public interface ICommand extends CommandExecutor {

    @Override
    default boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        execute(sender, args);
        return true;
    }

    void execute(CommandSender sender, String... args);
}
