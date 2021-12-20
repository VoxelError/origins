package me.asterverse.origins.Traits;

import me.asterverse.origins.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;

public class Test implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (Arrays.asList(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK).contains(event.getAction())) {
            if (event.getItem().getType().equals(Material.SLIME_BALL)) player.performCommand("origins gui");
            if (event.getItem().getType().equals(Material.PHANTOM_MEMBRANE))
                player.setAllowFlight(!player.getAllowFlight());
            if (event.getItem().getType().equals(Material.GUNPOWDER)) player.performCommand("kill @e[type=item]");
            if (event.getItem().getType().equals(Material.STICK)) player.performCommand("reload");
            if (event.getItem().getType().equals(Material.GOLDEN_SWORD)) {
//                player.performCommand("kill @e[type=armor_stand]");
//                TextComponent text = new TextComponent(String.valueOf(Tag.FLOWERS.getValues()));
//                player.spigot().sendMessage(ChatMessageType.CHAT, text);
//                player.sendMessage(String.valueOf(Main.getCool(player)));
                player.performCommand("data get entity @p BukkitValues");
//                player.performCommand("time query daytime");
//                if (Math.round(Math.random() * 100 + 1) < 15) player.sendMessage("woot!");

            }
        }
    }
}
