package me.asterverse.origins.Stuff;

import me.asterverse.origins.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Select implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (!(event.getClickedInventory().getHolder() instanceof Gui)) return;
        if (event.getCurrentItem() == null) return;
        event.setCancelled(true);
        if (event.getCurrentItem().getType() == Material.WHITE_STAINED_GLASS_PANE) return;

        Player player = (Player) event.getWhoClicked();
        new Set().setOrigin(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
        player.closeInventory();
    }
}
