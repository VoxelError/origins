package me.asterverse.origins;

import me.asterverse.origins.Stuff.*;
import me.asterverse.origins.Traits.*;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Main extends JavaPlugin {

    private static Main plugin;

    public static Main getPlugin() {
        return plugin;
    }

    private void newListen(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    private void newRun(Runnable runnable) {
        Bukkit.getScheduler().runTaskTimer(plugin, runnable, 0, 1);
    }

    public static String getOrigin(Player player) {
        return player.getPersistentDataContainer().get(new NamespacedKey(plugin, "Origin"), PersistentDataType.STRING);
    }

    public static void setOrigin(Player player, String string) {
        player.getPersistentDataContainer().set(new NamespacedKey(plugin, "Origin"), PersistentDataType.STRING, string);
    }

    public static Integer getCool(Player player) {
        return player.getPersistentDataContainer().get(new NamespacedKey(plugin, "Cooldown"), PersistentDataType.INTEGER);
    }

    public static void setCool(Player player, int cooldown) {
        player.getPersistentDataContainer().set(new NamespacedKey(plugin, "Cooldown"), PersistentDataType.INTEGER, cooldown);
    }

    public void onEnable() {
        plugin = this;

        getServer().addRecipe(new Test2().getRecipe());

        newListen(new Init());
        newListen(new Select());

        newListen(new Diet());
        newRun(new Effect());
        newListen(new Effect2());
        newListen(new Immune());
        newListen(new Ability());

        newListen(new Test());

        newRun(new Damage());

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : plugin.getServer().getOnlinePlayers()) {
                    if (getCool(player) > 0) setCool(player, getCool(player) - 1);
                }
            }
        }.runTaskTimer(plugin, 0, 1);

        getCommand("origins").setExecutor(new Command());
        getCommand("origins").setTabCompleter(new Autocomplete());
    }
}
