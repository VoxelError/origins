package me.asterverse.origins.Stuff;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class Command implements CommandExecutor {

    private List<String> origins = Arrays.asList(
            "Human",
            "Enderian",
            "Elytrian",
            "Arachnid",
            "Merling",
            "Blazeborn",
            "Shulk",
            "Phantom",
            "Avian",
            "Feline",
            "Wolf",
            "Slime",
            "Guardian",
            "Evoker",
            "Strider",
            "Snow Golem",
            "Witch",
            "Fox",
            "Bee",
            "Piglin"
    );

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        if (!(command.getName().equals("origins"))) return true;
        Player player = (Player) sender;
        if (args.length == 1 && args[0].equals("orb")) player.getInventory().addItem(new ItemStack(Material.SLIME_BALL));
        if (args.length == 1 && args[0].equals("ability")) player.getInventory().addItem(new ItemStack(Material.FIREWORK_STAR));
        if (args.length == 1 && args[0].equals("gui")) player.openInventory(new Gui().getInventory());
        if (args.length == 2 && args[0].equals("set") && origins.contains(args[1])) new Set().setOrigin(player, args[1]);
        return true;
    }
}