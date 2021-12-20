package me.asterverse.origins.Traits;

import me.asterverse.origins.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;


public class Diet implements Listener {

    private List<Material> meatDiet = Arrays.asList(
            Material.BEEF,
            Material.PORKCHOP,
            Material.CHICKEN,
            Material.COD,
            Material.MUTTON,
            Material.RABBIT,
            Material.SALMON,
            Material.COOKED_BEEF,
            Material.COOKED_PORKCHOP,
            Material.COOKED_CHICKEN,
            Material.COOKED_COD,
            Material.COOKED_MUTTON,
            Material.COOKED_RABBIT,
            Material.COOKED_SALMON,
            Material.PUFFERFISH,
            Material.TROPICAL_FISH,
            Material.RABBIT_STEW,
            Material.ROTTEN_FLESH,
            Material.SPIDER_EYE
    );

    private void dietReject(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 2));
    }

    @EventHandler
    public void onDietConsume(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();
        Material food = event.getItem().getType();
        String origin = player.getPersistentDataContainer().get(new NamespacedKey(Main.getPlugin(), "Origin"), PersistentDataType.STRING);

        if (Arrays.asList("Arachnid", "Wolf", "Bee").contains(origin) && !meatDiet.contains(food)) dietReject(player);
        if (origin.equals("Avian") && meatDiet.contains(food)) dietReject(player);
    }
}
