package me.jesusmx.hubcore.permissions.type;

import me.jesusmx.hubcore.permissions.PermissionCore;
import me.activated.core.plugin.AquaCoreAPI;
import org.bukkit.entity.Player;

public class AquaCorePermissionCore implements PermissionCore {

    @Override
    public String getRankColor(Player player) {
        return AquaCoreAPI.INSTANCE.getPlayerRank(player.getUniqueId()).getDisplayName();
    }

    @Override
    public String getRank(Player player) {
        return AquaCoreAPI.INSTANCE.getPlayerRank(player.getUniqueId()).getName();
    }
}
