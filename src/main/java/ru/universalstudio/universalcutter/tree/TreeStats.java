package ru.universalstudio.universalcutter.tree;

import java.util.*;
import org.bukkit.entity.*;

public class TreeStats {

    private static Map<Player, Integer> breaks = new HashMap<>();

    public static int getBreaks(Player player) {
        if (breaks.get(player) == null) {
            breaks.put(player, 0);
        }
        return breaks.get(player);
    }

    public static void setBreaks(Player player, int countBreaks) {
        breaks.put(player, countBreaks);
    }

    public static void addBreak(Player player, int countBreaks) {
        breaks.put(player, getBreaks(player) + countBreaks);
    }

}
