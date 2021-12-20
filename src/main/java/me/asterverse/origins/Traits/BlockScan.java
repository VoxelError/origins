package me.asterverse.origins.Traits;

import me.asterverse.origins.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class BlockScan {

    private JavaPlugin plugin = Main.getPlugin();

    public boolean scanSphere(Player player, int r, Material material) {

        Location center = player.getLocation();

        for (double x = center.getX() - r; x <= center.getX() + r; x++) {
            for (double y = center.getY() - r; y <= center.getY() + r; y++) {
                for (double z = center.getZ() - r; z <= center.getZ() + r; z++) {
                    Location block = new Location(player.getWorld(), x, y, z);
                    if (block.distanceSquared(center) <= r * r && block.getBlock().getType().equals(material)) return true;
                }
            }
        }

        return false;
    }

    public void traits() {

        new BukkitRunnable() {
            public void run() {
                for (Player player : plugin.getServer().getOnlinePlayers()) {
                    String origin = player.getPersistentDataContainer().get(new NamespacedKey(plugin, "Origin"), PersistentDataType.STRING);

                    if (Arrays.asList("Enderian", "Blazeborn", "Strider").contains(origin) && player.isInWater()) player.damage(1);

//                    if (scanSphere(player, 5, Material.SAND)) player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 3));
                }
            }
        }.runTaskTimer(plugin, 0, 1);
    }
}