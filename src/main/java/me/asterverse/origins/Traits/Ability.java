package me.asterverse.origins.Traits;

import me.asterverse.origins.Main;
import me.asterverse.origins.Stuff.PlayerOriginChangeEvent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;

import java.util.Arrays;
import java.util.List;

import static me.asterverse.origins.Main.*;

public class Ability implements Listener {

    private class Shulk implements InventoryHolder {
        @Override
        public Inventory getInventory() {
            Inventory inv = Bukkit.createInventory(this, 9, "Shulk Box");
//            inv.setContents(new ItemStack[]);
            return inv;
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem().getType().equals(Material.FIREWORK_STAR) && Arrays.asList(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK).contains(event.getAction())) {
            if (Main.getOrigin(player).equals("Shulk")) {
                player.playSound(player.getLocation(), Sound.BLOCK_SHULKER_BOX_OPEN, 0.5F, 1);
                player.openInventory(new Shulk().getInventory());
            }
            if (Main.getOrigin(player).equals("Enderian") && getCool(player) == 0) {
                setCool(player, 20);
                player.launchProjectile(EnderPearl.class);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, (float) 0.5, (float) 0.5);
            }
            if (Main.getOrigin(player).equals("Arachnid") && getCool(player) == 0) {
//                setCool(player, 600);
//                Snowball snowball = player.launchProjectile(Snowball.class);
//                FallingBlock fallin = player.getWorld().spawnFallingBlock(snowball.getLocation(), Material.COBWEB.createBlockData());
//                fallin.setVelocity(snowball.getVelocity());
//                snowball.remove();
//                player.playSound(player.getLocation(), Sound.ENTITY_SPIDER_HURT, (float) 0.5, (float) 0.5);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                if (player.getPersistentDataContainer().get(new NamespacedKey(getPlugin(), "Climb"), PersistentDataType.INTEGER).equals(1))
                    player.getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "Climb"), PersistentDataType.INTEGER, 0);
                else
                    player.getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "Climb"), PersistentDataType.INTEGER, 1);
            }
            if (Main.getOrigin(player).equals("Slime") && getCool(player) == 0) {
                setCool(player, 1200);
                Location block = event.getClickedBlock().getLocation();
                Entity spawn = player.getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0.5, 0, 0.5).add(event.getBlockFace().getDirection()), EntityType.SLIME);
                Slime slime = (Slime) spawn;
                slime.setSize(4);
            }
            if (Main.getOrigin(player).equals("Evoker") && getCool(player) == 0) {
                RayTraceResult ray = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 10, x -> !x.equals(player) && x instanceof LivingEntity);
                if (ray == null) return;
                setCool(player, 400);
                Entity entity = ray.getHitEntity();
                entity.getWorld().spawnEntity(entity.getLocation(), EntityType.EVOKER_FANGS);
            }
            if (Main.getOrigin(player).equals("Witch") && getCool(player) == 0) {
                setCool(player, 6000);
                List<PotionEffectType> potion = Arrays.asList(
                        PotionEffectType.REGENERATION,
                        PotionEffectType.SPEED,
                        PotionEffectType.FIRE_RESISTANCE,
                        PotionEffectType.HEAL,
                        PotionEffectType.NIGHT_VISION,
                        PotionEffectType.INCREASE_DAMAGE,
                        PotionEffectType.JUMP,
                        PotionEffectType.WATER_BREATHING,
                        PotionEffectType.INVISIBILITY,
                        PotionEffectType.SLOW_FALLING,
                        PotionEffectType.LUCK,

                        PotionEffectType.POISON
                );
                player.addPotionEffect(new PotionEffect(potion.get((int) Math.round(Math.random() * 12)), 600, 0));
                player.playSound(player.getLocation(), Sound.ENTITY_WITCH_DRINK, 1, 1);
            }
            if (Main.getOrigin(player).equals("Phantom") && getCool(player) == 0) {
                setCool(player, 20);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                if (player.getPersistentDataContainer().get(new NamespacedKey(getPlugin(), "Phantom"), PersistentDataType.INTEGER).equals(1))
                    player.getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "Phantom"), PersistentDataType.INTEGER, 0);
                else
                    player.getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "Phantom"), PersistentDataType.INTEGER, 1);
            }
            if (getOrigin(player).equals("Elytrian")) {
                setCool(player, 600);
                player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SHOOT, 1, 0.5F);
                player.setVelocity(player.getVelocity().setY(3));
            }
        }
    }
}
