package me.asterverse.origins.Traits;

import me.asterverse.origins.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.stream.Stream;

import static me.asterverse.origins.Main.*;

public class Effect extends BukkitRunnable {

    private JavaPlugin plugin = Main.getPlugin();

    public Integer getDamage(Player player) {
        return player.getPersistentDataContainer().get(new NamespacedKey(plugin, "Damage"), PersistentDataType.INTEGER);
    }

    public void setDamage(Player player, int damage) {
        player.getPersistentDataContainer().set(new NamespacedKey(plugin, "Damage"), PersistentDataType.INTEGER, damage);
    }

    public Integer getDrown(Player player) {
        return player.getPersistentDataContainer().get(new NamespacedKey(plugin, "Drown"), PersistentDataType.INTEGER);
    }

    public void setDrown(Player player, int drown) {
        player.getPersistentDataContainer().set(new NamespacedKey(plugin, "Drown"), PersistentDataType.INTEGER, drown);
    }

    public Integer getPhantom(Player player) {
        return player.getPersistentDataContainer().get(new NamespacedKey(plugin, "Phantom"), PersistentDataType.INTEGER);
    }

    public void setPhantom(Player player, int phantom) {
        player.getPersistentDataContainer().set(new NamespacedKey(plugin, "Phantom"), PersistentDataType.INTEGER, phantom);
    }

    public Integer getClimb(Player player) {
        return player.getPersistentDataContainer().get(new NamespacedKey(plugin, "Climb"), PersistentDataType.INTEGER);
    }

    public void setClimb(Player player, int climb) {
        player.getPersistentDataContainer().set(new NamespacedKey(plugin, "Climb"), PersistentDataType.INTEGER, climb);
    }

    @Override
    public void run() {

        for (Player player : plugin.getServer().getOnlinePlayers()) {

            String origin = Main.getOrigin(player);

            if (origin.equals("Elytrian")) {
                if (player.isGliding()) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5, 1, false, false));
                }

                List<Material> armors = Arrays.asList(
                        Material.IRON_HELMET,
                        Material.DIAMOND_HELMET,
                        Material.NETHERITE_HELMET,
                        Material.IRON_LEGGINGS,
                        Material.DIAMOND_LEGGINGS,
                        Material.NETHERITE_LEGGINGS,
                        Material.IRON_BOOTS,
                        Material.DIAMOND_BOOTS,
                        Material.NETHERITE_BOOTS
                );
                PlayerInventory inv = player.getInventory();

                if (inv.getHelmet() != null && armors.contains(player.getInventory().getHelmet().getType())) {
                    Item item = player.getWorld().dropItem(player.getEyeLocation(), player.getInventory().getHelmet());
                    item.setPickupDelay(50);
                    item.setVelocity(player.getEyeLocation().getDirection().multiply(0.35));
                    player.getInventory().setHelmet(null);
                }
                if (inv.getLeggings() != null && armors.contains(player.getInventory().getLeggings().getType())) {
                    Item item = player.getWorld().dropItem(player.getEyeLocation(), player.getInventory().getLeggings());
                    item.setPickupDelay(50);
                    item.setVelocity(player.getEyeLocation().getDirection().multiply(0.35));
                    player.getInventory().setLeggings(null);
                }
                if (inv.getBoots() != null && armors.contains(player.getInventory().getBoots().getType())) {
                    Item item = player.getWorld().dropItem(player.getEyeLocation(), player.getInventory().getBoots());
                    item.setPickupDelay(50);
                    item.setVelocity(player.getEyeLocation().getDirection().multiply(0.35));
                    player.getInventory().setBoots(null);
                }

                List<Block> ceil = new ArrayList<>();
                for (int x = 0; x < 3; x++) {
                    Location ploc = player.getLocation().add(0, 2, 0);
                    Location loc = new Location(player.getWorld(), ploc.getX(), ploc.getY() + x, ploc.getZ());
                    if (loc.getBlock().getType() != Material.AIR) ceil.add(loc.getBlock());
                }
                if (ceil.size() > 0) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 4, 1, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 4, 1, false, false));
                }
            }

            if (origin.equals("Strider")) {
                Location loc = player.getLocation().subtract(0, 1, 0).getBlock().getLocation().add(0.5, 0, 0.5);
                if (loc.getBlock().getType().equals(Material.LAVA)) {
                    ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                    stand.setMarker(true);
                    stand.setSmall(true);
                    stand.setInvisible(true);
                    stand.addScoreboardTag("Walk");
                    stand.getLocation().getBlock().setType(Material.MAGMA_BLOCK);
                }
            }

            if (Arrays.asList("Avian", "Bee").contains(origin))
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 4, 0, false, false));

            if (origin.equals("Avian"))
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4, 0, false, false));

            if (origin.equals("Merling")) {
                if (player.isInWater()) player.setGravity(false);
                else if (!player.isInWater()) player.setGravity(true);
            }

            if (Arrays.asList("Merling", "Guardian").contains(getOrigin(player))) {

                Material eyeblock = player.getEyeLocation().getBlock().getType();

                if (eyeblock.equals(Material.WATER)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 4, 0, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 4, 0, false, false));
                }

                if (eyeblock.equals(Material.WATER) || player.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
                    if (getDrown(player) < 300) setDrown(player, getDrown(player) + 4);
                } else setDrown(player, getDrown(player) - 1);
                if (getDrown(player) > 300) setDrown(player, 300);
                if (getDrown(player) < -20) {
                    player.damage(2);
                    setDrown(player, 0);
                }
                if (getDrown(player) >= 300 || getDrown(player) <= 0) player.setRemainingAir(-10);
                else player.setRemainingAir(getDrown(player) - (getDrown(player) % 30) + 15);

            }

            if (origin.equals("Fox")) {
                for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
                    if (entity.getType().equals(EntityType.PLAYER) && player.getLocation().distance(entity.getLocation()) <= 5) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4, 4, false, false));
                    }
                    if (entity.getType().equals(EntityType.DROPPED_ITEM) && player.getLocation().distance(entity.getLocation()) <= 3) {
                        Item item = (Item) entity;
                        if (item.getPickupDelay() <= 0) item.teleport(player);
                    }
                }
            }

            if (origin.equals("Feline")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 4, 0, false, false));
                if (player.isSprinting()) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 4, 1, false, false));
                }
            }

            if (origin.equals("Slime") && player.getHealth() < 4) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 4, 0, false, false));
            }

            if (origin.equals("Wolf") && player.getWorld().getTime() > 18000 && player.getWorld().getTime() < 21000) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 4, 0, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 4, 0, false, false));
            }

            if (origin.equals("Blazeborn")) {
                if (player.getFireTicks() > 0)
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 4, 0, false, false));
                if (player.getPotionEffect(PotionEffectType.POISON) != null)
                    player.removePotionEffect(PotionEffectType.POISON);
                if (player.getPotionEffect(PotionEffectType.HUNGER) != null)
                    player.removePotionEffect(PotionEffectType.HUNGER);
            }

            if (origin.equals("Bee")) {
                Location loc = player.getLocation();
                int radius = 4;
                List<Block> blocks = new ArrayList<>();
                for (double x = loc.getX() - radius; x <= loc.getX() + radius; x++) {
                    for (double y = loc.getY() - radius; y <= loc.getY() + radius; y++) {
                        for (double z = loc.getZ() - radius; z <= loc.getZ() + radius; z++) {
                            Location block = new Location(player.getWorld(), x, y, z);
                            if (Tag.FLOWERS.getValues().contains(block.getBlock().getType()))
                                blocks.add(block.getBlock());
                        }
                    }
                }
                if (blocks.stream().count() < 3) return;
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 0, false, false));
            }

            if (origin.equals("Arachnid")) {
                List<Block> blocks = new ArrayList<>();
                blocks.add(player.getLocation().add(1, 0, 0).getBlock());
                blocks.add(player.getLocation().add(0, 0, 1).getBlock());
                blocks.add(player.getLocation().add(-1, 0, 0).getBlock());
                blocks.add(player.getLocation().add(0, 0, -1).getBlock());
                if (player.isSneaking() && blocks.stream().anyMatch(x -> x.getType().isSolid()) && getClimb(player) == 1)
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 5, 1, false, false));
            }

            if (origin.equals("Snow Golem")) {
                if (player.getLocation().getBlock().getType().isAir() && !player.getLocation().subtract(0, 1, 0).getBlock().isPassable()) {
                    player.getLocation().getBlock().setType(Material.SNOW);
                }
                List<Biome> warm = Arrays.asList(
                        Biome.DESERT,
                        Biome.SAVANNA,
                        Biome.SAVANNA_PLATEAU,
                        Biome.SHATTERED_SAVANNA,
                        Biome.BADLANDS,
                        Biome.ERODED_BADLANDS,
                        Biome.WOODED_BADLANDS_PLATEAU
                );
                if (warm.contains(player.getLocation().getBlock().getBiome())) setDamage(player, getDamage(player) + 1);
                if (getDamage(player) > 20) {
                    player.damage(1);
                    setDamage(player, 0);
                }
            }

            if (origin.equals("Piglin")) {
                if (player.getWorld().getName().equals("world")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 4, false, false));
                }

                Location loc = player.getLocation();
                int radius = 7;
                for (double x = loc.getX() - radius; x <= loc.getX() + radius; x++) {
                    for (double y = loc.getY() - radius; y <= loc.getY() + radius; y++) {
                        for (double z = loc.getZ() - radius; z <= loc.getZ() + radius; z++) {
                            Location block = new Location(player.getWorld(), x, y, z);
                            if (block.getBlock().getType().equals(Material.SOUL_FIRE))
                                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 5, 0, false, false));
                        }
                    }
                }

                List<Material> gold = Arrays.asList(
                        Material.GOLDEN_SWORD,
                        Material.GOLDEN_AXE,
                        Material.GOLDEN_HOE,
                        Material.GOLDEN_PICKAXE,
                        Material.GOLDEN_SHOVEL
                );
                if (gold.contains(player.getInventory().getItemInMainHand().getType()))
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5, 0, false, false));

                if (!player.getInventory().getHelmet().getType().equals(Material.NETHERITE_HELMET)) return;
                if (!player.getInventory().getChestplate().getType().equals(Material.NETHERITE_CHESTPLATE)) return;
                if (!player.getInventory().getLeggings().getType().equals(Material.NETHERITE_LEGGINGS)) return;
                if (!player.getInventory().getBoots().getType().equals(Material.NETHERITE_BOOTS)) return;
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5, 2, false, false));
            }

            if (origin.equals("Phantom")) {
                if (player.getLocation().getBlock().getLightFromSky() == 15 && !(player.getWorld().getTime() < 23000 && player.getWorld().getTime() > 13000) && getPhantom(player) == 0) {
                    player.setFireTicks(20);
                }
                if (getPhantom(player) == 1) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 5, 0, false, false));
                }
            }

            if (origin.equals("Shulk")) {
                if (player.getInventory().getItemInMainHand().getType().equals(Material.SHIELD)) {
                    Item item = player.getWorld().dropItem(player.getEyeLocation(), player.getInventory().getItemInMainHand());
                    item.setPickupDelay(50);
                    item.setVelocity(player.getEyeLocation().getDirection().multiply(0.35));
                    player.getInventory().setItemInMainHand(null);
                }
                if (player.getInventory().getItemInOffHand().getType().equals(Material.SHIELD)) {
                    Item item = player.getWorld().dropItem(player.getEyeLocation(), player.getInventory().getItemInOffHand());
                    item.setPickupDelay(50);
                    item.setVelocity(player.getEyeLocation().getDirection().multiply(0.35));
                    player.getInventory().setItemInOffHand(null);
                }
            }
        }

        for (Entity entity : Bukkit.selectEntities(Bukkit.getConsoleSender(), "@e[tag=Walk]")) {
            if (!entity.getType().equals(EntityType.ARMOR_STAND)) return;
            entity.getLocation().getBlock().setType(Material.MAGMA_BLOCK);
            if (entity.getNearbyEntities(1, 2, 1).stream().filter(x -> x.getType().equals(EntityType.PLAYER) && Main.getOrigin((Player) x).equals("Strider")).count() == 0) {
                entity.getLocation().getBlock().setType(Material.LAVA);
                entity.remove();
            }
        }

        for (Entity entity : Bukkit.selectEntities(Bukkit.getConsoleSender(), "@e[type=minecraft:slime]")) {
            Slime slime = (Slime) entity;
            if (!slime.getTarget().getType().equals(EntityType.PLAYER)) return;
            Player player = (Player) slime.getTarget();
            if (!Main.getOrigin(player).equals("Slime")) return;
            slime.setTarget(null);
        }

        for (Entity entity : Bukkit.selectEntities(Bukkit.getConsoleSender(), "@e[type=minecraft:creeper]")) {
            Creeper creeper = (Creeper) entity;
            if (!creeper.getTarget().getType().equals(EntityType.PLAYER)) return;
            Player player = (Player) creeper.getTarget();
            if (!Main.getOrigin(player).equals("Feline")) return;
            creeper.setTarget(null);
        }

        for (Entity entity : Bukkit.selectEntities(Bukkit.getConsoleSender(), "@e[type=minecraft:bee]")) {
            Bee bee = (Bee) entity;
            if (!bee.getTarget().getType().equals(EntityType.PLAYER)) return;
            Player player = (Player) bee.getTarget();
            if (!Main.getOrigin(player).equals("Bee")) return;
            bee.setTarget(null);
            bee.setAnger(0);
        }

        for (Entity entity : Bukkit.selectEntities(Bukkit.getConsoleSender(), "@e[type=minecraft:iron_golem]")) {
            IronGolem golem = (IronGolem) entity;
            for (Entity tity : golem.getNearbyEntities(16, 16, 16)) {
                if (tity.getType().equals(EntityType.PLAYER) && Arrays.asList("Witch", "Evoker").contains(getOrigin((Player) tity))) {
                    golem.setTarget((LivingEntity) tity);
                    golem.setPlayerCreated(false);
                }
            }
        }
    }
}
