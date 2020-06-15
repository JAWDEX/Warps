package me.jawdex.warps;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Location;

@Data
@AllArgsConstructor
public class Warp {
    private String name;
    private String displayName;
    private Location location;
    private int timeToTeleport;
}
