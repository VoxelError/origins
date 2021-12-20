package me.asterverse.origins.Traits;

import me.asterverse.origins.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Arrays;
import java.util.List;

public class Immune implements Listener {

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (Main.getOrigin(player).equals("Enderian") && event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
            event.setCancelled(true);
            player.teleport(event.getTo());
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Player player = (Player) event.getEntity();
        List<EntityDamageEvent.DamageCause> burn = Arrays.asList(EntityDamageEvent.DamageCause.FIRE, EntityDamageEvent.DamageCause.FIRE_TICK, EntityDamageEvent.DamageCause.LAVA, EntityDamageEvent.DamageCause.HOT_FLOOR);
        if (burn.contains(event.getCause()) && Arrays.asList("Blazeborn", "Strider").contains(Main.getOrigin(player))) event.setCancelled(true);
        if (burn.contains(event.getCause()) && Main.getOrigin(player).equals("Piglin") && player.getWorld().getName().equals("world")) event.setCancelled(true);
        if (Arrays.asList("Feline", "Slime").contains(Main.getOrigin(player)) && event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();
        if (Main.getOrigin(player).equals("Slime") && event.getDamager().getType().equals(EntityType.SLIME)) event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        Player player = (Player) event.getEntity();
        Block block = event.getDamager();
        if (Main.getOrigin(player).equals("Fox") && block.getType().equals(Material.SWEET_BERRY_BUSH)) event.setCancelled(true);
    }
}
