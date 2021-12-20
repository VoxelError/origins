package me.asterverse.origins.Traits;

import me.asterverse.origins.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Test2 {

    public Recipe getRecipe() {
        ItemStack item = new ItemStack(Material.SLIME_BALL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + (ChatColor.WHITE + "Orb of Power"));
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(Main.getPlugin(), "origins_orb");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(
                "NEN",
                "ESE",
                "NEN"
        );
        recipe.setIngredient('N', Material.NETHERITE_BLOCK);
        recipe.setIngredient('E', Material.ENCHANTED_GOLDEN_APPLE);
        recipe.setIngredient('S', Material.NETHER_STAR);
        return recipe;
    }
}
