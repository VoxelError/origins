package me.asterverse.origins.Traits;

import de.jeff_media.morepersistentdatatypes.DataType;
import me.asterverse.origins.Main;
import me.asterverse.origins.Stuff.PlayerOriginChangeEvent;
import me.asterverse.origins.Stuff.PlayerOriginWillChangeEvent;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Biome;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.asterverse.origins.Main.*;
import static me.asterverse.origins.Main.getPlugin;

public class Effect2 implements Listener {

    public Integer getPhantom(Player player) {
        return player.getPersistentDataContainer().get(new NamespacedKey(getPlugin(), "Phantom"), PersistentDataType.INTEGER);
    }

    public void setPhantom(Player player, int phantom) {
        player.getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "Phantom"), PersistentDataType.INTEGER, phantom);
    }

    public ItemStack getElytra() {
        ItemStack elytra = new ItemStack(Material.ELYTRA);
        ItemMeta meta = elytra.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Elytrian Wings");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", 4, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        elytra.setItemMeta(meta);
        return elytra;
    }



    @EventHandler
    public void onPlayerOriginChange(PlayerOriginChangeEvent event) {
        Player player = event.getPlayer();
        setCool(player, 0);
        player.setRemainingAir(300);

        if (Arrays.asList("Arachnid", "Phantom").contains(getOrigin(player)))
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(14);
        else if (Arrays.asList("Wolf", "Fox", "Bee", "Piglin", "Feline").contains(getOrigin(player)))
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(16);

        if (Main.getOrigin(player).equals("Elytrian")) {
            ItemStack chest = player.getInventory().getChestplate();
            if (chest != null) player.getWorld().dropItem(player.getLocation(), chest).setPickupDelay(0);
            player.getInventory().setChestplate(getElytra());
        }
        if (Arrays.asList("Merling", "Guardian").contains(getOrigin(player))) {
            player.getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "Drown"), PersistentDataType.INTEGER, 300);
        }
        if (getOrigin(player).equals("Snow Golem")) {
            player.getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "Damage"), PersistentDataType.INTEGER, 0);
        }
        if (getOrigin(player).equals("Phantom")) {
            player.getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "Phantom"), PersistentDataType.INTEGER, 0);
        }
        if (getOrigin(player).equals("Arachnid")) {
            player.getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "Climb"), PersistentDataType.INTEGER, 0);
        }
        if (getOrigin(player).equals("Shulk")) {
//            ItemStack[] items = new ItemStack[9];
//            Arrays.fill(items, null);
//            player.getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "Shulk"), PersistentDataType.STRING, "new ItemStack(Material.SNOW)");
//            player.performCommand("data get entity @p BukkitValues");
            player.getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "test"),DataType.STRING, "test12345");
            System.out.println(player.getPersistentDataContainer().get(new NamespacedKey(getPlugin(), "test"), DataType.STRING));
//            System.out.println(player.getPersistentDataContainer().get(new NamespacedKey(getPlugin(), "Origin"), PersistentDataType.STRING));
            player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(6);
        }
//        if (Arrays.asList("Blazeborn", "Strider", "Piglin").contains(Main.getOrigin(player))) player.setHealth(0);
    }

    @EventHandler
    public void onPlayerOriginWillChange(PlayerOriginWillChangeEvent event) {
        Player player = event.getPlayer();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        if (getOrigin(player).equals("Merling")) player.setGravity(true);
        if (Arrays.asList("Merling", "Guardian").contains(getOrigin(player))) {
            player.getPersistentDataContainer().remove(new NamespacedKey(getPlugin(), "Drown"));
        }
        if (getOrigin(player).equals("Snow Golem")) {
            player.getPersistentDataContainer().remove(new NamespacedKey(getPlugin(), "Damage"));
        }
        if (getOrigin(player).equals("Phantom")) {
            player.getPersistentDataContainer().remove(new NamespacedKey(getPlugin(), "Phantom"));
        }
        if (getOrigin(player).equals("Arachnid")) {
            player.getPersistentDataContainer().remove(new NamespacedKey(getPlugin(), "Climb"));
        }
        if (getOrigin(player).equals("Shulk")) {
            player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);
        }
        if (getOrigin(player).equals("Elytrian")) {
            player.getInventory().setChestplate(null);
        }
    }

    @EventHandler
    public void onEntityTargetLivingEntity(EntityTargetLivingEntityEvent event) {
        Player player = (Player) event.getTarget();
        Entity targeter = event.getEntity();
        if (Main.getOrigin(player).equals("Bee") && targeter.getType().equals(EntityType.BEE)) event.setCancelled(true);
        if (Main.getOrigin(player).equals("Slime") && targeter.getType().equals(EntityType.SLIME))
            event.setCancelled(true);
        if (Main.getOrigin(player).equals("Feline") && targeter.getType().equals(EntityType.CREEPER)) {
            event.setCancelled(true);
        }
        if (Main.getOrigin(player).equals("Piglin") && Arrays.asList(EntityType.PIGLIN, EntityType.PIGLIN_BRUTE).contains(targeter.getType())) {
            LivingEntity entity = (LivingEntity) targeter;
            event.setCancelled(true);
            if (entity.getMemory(MemoryKey.ANGRY_AT) == null) return;
            if (Bukkit.getPlayer(entity.getMemory(MemoryKey.ANGRY_AT)).getName().equals(player.getName()))
                event.setCancelled(false);
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType().equals(EntityType.ARROW)) {
            Arrow arrow = (Arrow) event.getDamager();
            if (!(arrow.getShooter() instanceof Player)) return;
            Player player = (Player) arrow.getShooter();
            if (!getOrigin(player).equals("Piglin")) return;
            if (!player.getInventory().getItemInMainHand().getType().equals(Material.CROSSBOW)) return;
            event.setDamage(event.getDamage() * 1.75);
        }
        if (event.getEntity().getType().equals(EntityType.PLAYER)) {
            Player player = (Player) event.getEntity();
            LivingEntity damager = (LivingEntity) event.getDamager();
            if (Main.getOrigin(player).equals("Arachnid") && getCool(player) == 0) {
                setCool(player, 600);
                player.getWorld().getBlockAt(event.getDamager().getLocation()).setType(Material.COBWEB);
            }
            if (Main.getOrigin(player).equals("Guardian")) damager.damage(2);
            if (Main.getOrigin(player).equals("Phantom") && getPhantom(player) == 1 && event.getDamager().getType().equals(EntityType.PLAYER)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityExhaustion(EntityExhaustionEvent event) {
        if (!event.getEntity().getType().equals(EntityType.PLAYER)) return;
        Player player = (Player) event.getEntity();
        if (getOrigin(player).equals("Phantom") && getPhantom(player) == 1) {
            event.setExhaustion((float) (event.getExhaustion() * 1.5));
        }
        if (getOrigin(player).equals("Shulk")) {
            event.setExhaustion((float) (event.getExhaustion() * 1.5));
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Snowball)) return;
        Snowball snowball = (Snowball) event.getEntity();
        if (!(event.getHitEntity() instanceof LivingEntity)) return;
        LivingEntity entity = (LivingEntity) event.getHitEntity();

        List<Biome> cold = Arrays.asList(
                Biome.SNOWY_TAIGA,
                Biome.SNOWY_TUNDRA,
                Biome.SNOWY_BEACH,
                Biome.SNOWY_MOUNTAINS,
                Biome.SNOWY_TAIGA_HILLS,
                Biome.SNOWY_TAIGA_MOUNTAINS,
                Biome.ICE_SPIKES
        );
        if (cold.contains(event.getHitEntity().getLocation().getBlock().getBiome()))
            entity.damage(2, (Entity) snowball.getShooter());
        else entity.damage(1, (Entity) snowball.getShooter());
    }

    @EventHandler
    public void onEntityTame(EntityTameEvent event) {
        Player player = (Player) event.getOwner();
        if (Main.getOrigin(player).equals("Wolf") && event.getEntity().getType().equals(EntityType.WOLF)) {
            Tameable wolf = (Tameable) event.getEntity();
            event.setCancelled(true);
            wolf.setOwner(player);
            wolf.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(24);
            wolf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5);
        }
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (Main.getOrigin(player).equals("Fox") && event.getItem().getType().equals(Material.SWEET_BERRIES)) {
            player.setFoodLevel(player.getFoodLevel() + 4);
            player.setSaturation(player.getSaturation() + 0.8f);
        }
        if (Main.getOrigin(player).equals("Witch") && event.getItem().getType().equals(Material.POTION)) {
            if (Math.round(Math.random() * 100 + 1) > 69) return;
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.getInventory().setItemInMainHand(event.getItem());
                }
            }.runTaskLater(Main.getPlugin(), 1);
        }
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        if (Main.getOrigin(player).equals("Avian") && event.getBed().getLocation().getY() < 75 && event.getBedEnterResult().equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
            event.setCancelled(true);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("You must sleep at a higher elevation"));
        }
//        if (Main.getOrigin(player).equals("Strider")) {
//            event.setUseBed(Event.Result.ALLOW);
//            event.getPlayer().setBedSpawnLocation(event.getBed().getLocation());
//        }
    }

    @EventHandler
    public void onPlayerVelocity(PlayerVelocityEvent event) {
        Player player = event.getPlayer();
        if (Arrays.asList("Merling", "Guardian").contains(getOrigin(player)) && player.isSwimming()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Location nether = new Location(Bukkit.getWorld("world_nether"), 0.5, 32, 0.5);
        if (Arrays.asList("Blazeborn", "Strider", "Piglin").contains(Main.getOrigin(player)) && !event.isBedSpawn() && !event.isAnchorSpawn() && !event.getRespawnLocation().equals(nether)) {
            player.setBedSpawnLocation(nether, true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.teleport(nether);
                }
            }.runTaskLater(Main.getPlugin(), 1);
        }
    }

    @EventHandler
    public void onEntityResurrect(EntityResurrectEvent event) {
        if (!event.getEntity().getType().equals(EntityType.PLAYER)) return;
        Player player = (Player) event.getEntity();
        if (Main.getOrigin(player).equals("Evoker")) {
            if (Math.round(Math.random() * 100 + 1) > 15) return;
            if (player.getInventory().getItemInMainHand().getType().equals(Material.TOTEM_OF_UNDYING)) {
                player.getInventory().setItemInMainHand(new ItemStack(Material.TOTEM_OF_UNDYING));
            } else if (player.getInventory().getItemInOffHand().getType().equals(Material.TOTEM_OF_UNDYING)) {
                player.getInventory().setItemInOffHand(new ItemStack(Material.TOTEM_OF_UNDYING));
            }
        }
    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {
        Player player = event.getPlayer();
        if (getOrigin(player).equals("Piglin")) {
            List<Material> gold = Arrays.asList(
                    Material.GOLDEN_HELMET,
                    Material.GOLDEN_CHESTPLATE,
                    Material.GOLDEN_LEGGINGS,
                    Material.GOLDEN_BOOTS
            );
            if (gold.contains(event.getItem().getType()) && Math.floor(Math.random() * 2 + 1) == 1)
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getWhoClicked().getType().equals(EntityType.PLAYER)) return;
        if (!(event.getClickedInventory() instanceof PlayerInventory)) return;
        Player player = (Player) event.getWhoClicked();
        if (!getOrigin(player).equals("Elytrian")) return;
        if (event.getCurrentItem().getType().equals(Material.ELYTRA) && event.getCurrentItem().getItemMeta().isUnbreakable())
            event.setCancelled(true);
    }

    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (event.getBlock().getType().equals(Material.STONE) && player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(event.getBlock().getType()));
        }
    }
}