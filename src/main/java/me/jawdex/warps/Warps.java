package me.jawdex.warps;

import me.jawdex.warps.commands.CreateWarpCommand;
import me.jawdex.warps.commands.DeleteWarpCommand;
import me.jawdex.warps.commands.WarpCommand;
import me.jawdex.warps.manager.WarpManager;
import me.jawdex.warps.menu.MenuListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class Warps extends JavaPlugin {
    @Getter private WarpManager warpManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.warpManager = new WarpManager(this);
        getCommand("warp").setExecutor(new WarpCommand(this));
        getCommand("createwarp").setExecutor(new CreateWarpCommand(this));
        getCommand("deletewarp").setExecutor(new DeleteWarpCommand(this));
        getServer().getPluginManager().registerEvents(new MenuListener(this), this);
    }
}
