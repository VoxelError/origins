package me.asterverse.origins.Stuff;

import me.asterverse.origins.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Set {
    public void setOrigin(Player player, String origin) {
        Main.getPlugin().getServer().getPluginManager().callEvent(new PlayerOriginWillChangeEvent(player));
        Main.setOrigin(player, origin);
        player.sendMessage(ChatColor.GREEN + "Set Origin: " + ChatColor.AQUA + origin);
        Main.getPlugin().getServer().getPluginManager().callEvent(new PlayerOriginChangeEvent(player));
    }
}
