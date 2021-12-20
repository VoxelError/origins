package me.asterverse.origins.Stuff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Gui implements InventoryHolder {

    private ItemStack newItem(String name, Material material, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
//        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    private Inventory inventory;

    public Gui() {
        inventory = Bukkit.createInventory(this, 54, "Origins");

        Description stats = new Description();

        inventory.setItem(13, newItem(ChatColor.AQUA + "Human", Material.PLAYER_HEAD, stats.human));

        inventory.setItem(19, newItem(ChatColor.AQUA + "Enderian", Material.ENDER_PEARL, stats.enderian));
        inventory.setItem(20, newItem(ChatColor.AQUA + "Elytrian", Material.ELYTRA, stats.elytrian));
        inventory.setItem(21, newItem(ChatColor.AQUA + "Arachnid", Material.COBWEB, stats.arachnid));
        inventory.setItem(22, newItem(ChatColor.AQUA + "Merling", Material.COD, stats.merling));
        inventory.setItem(23, newItem(ChatColor.AQUA + "Blazeborn", Material.BLAZE_ROD, stats.blazeborn));
        inventory.setItem(24, newItem(ChatColor.AQUA + "Shulk", Material.SHULKER_SHELL, stats.shulk));
        inventory.setItem(25, newItem(ChatColor.AQUA + "Phantom", Material.PHANTOM_MEMBRANE, stats.phantom));

        inventory.setItem(28, newItem(ChatColor.AQUA + "Avian", Material.FEATHER, stats.avian));
        inventory.setItem(29, newItem(ChatColor.AQUA + "Feline", Material.ORANGE_WOOL, stats.feline));
        inventory.setItem(30, newItem(ChatColor.AQUA + "Wolf", Material.BONE, stats.wolf));
        inventory.setItem(31, newItem(ChatColor.AQUA + "Slime", Material.SLIME_BLOCK, stats.slime));
        inventory.setItem(32, newItem(ChatColor.AQUA + "Guardian", Material.PRISMARINE_SHARD, stats.guardian));
        inventory.setItem(33, newItem(ChatColor.AQUA + "Evoker", Material.TOTEM_OF_UNDYING, stats.evoker));
        inventory.setItem(34, newItem(ChatColor.AQUA + "Strider", Material.WARPED_FUNGUS_ON_A_STICK, stats.strider));

        inventory.setItem(38, newItem(ChatColor.AQUA + "Snow Golem", Material.SNOWBALL, stats.snow_golem));
        inventory.setItem(39, newItem(ChatColor.AQUA + "Witch", Material.BREWING_STAND, stats.witch));
        inventory.setItem(40, newItem(ChatColor.AQUA + "Fox", Material.SWEET_BERRIES, stats.fox));
        inventory.setItem(41, newItem(ChatColor.AQUA + "Bee", Material.DANDELION, stats.bee));
        inventory.setItem(42, newItem(ChatColor.AQUA + "Piglin", Material.GOLDEN_SWORD, stats.piglin));
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
