package me.asterverse.origins.Stuff;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class Autocomplete implements TabCompleter {

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
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> sub = Arrays.asList("orb", "ability", "gui", "set");

        if (args.length == 1) return sub;
        if (args.length == 2) {
            if (args[0].equals("orb")) return Arrays.asList();
            if (args[0].equals("ability")) return Arrays.asList();
            if (args[0].equals("gui")) return Arrays.asList();
            if (args[0].equals("set")) return origins;
        }
        if (args.length > 2) return Arrays.asList();
        return null;
    }
}
