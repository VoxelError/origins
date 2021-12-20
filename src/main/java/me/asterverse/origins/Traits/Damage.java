package me.asterverse.origins.Traits;

import me.asterverse.origins.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class Damage extends BukkitRunnable {

    private JavaPlugin plugin = Main.getPlugin();

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            String origin = player.getPersistentDataContainer().get(new NamespacedKey(plugin, "Origin"), PersistentDataType.STRING);

            if (Arrays.asList("Enderian", "Blazeborn", "Strider").contains(origin) && player.isInWater()) player.damage(1);
        }
    }
}