package ru.universalstudio.universalcutter.utils;

import java.util.*;
import org.bukkit.entity.*;

public class Salary {

    private static Map<Player, Double> salarys = new HashMap<>();

    public static double getSalary(Player player) {
        if (salarys.get(player) == null) {
            salarys.put(player, 0.0D);
        }
        return salarys.get(player);
    }

    public static void setSalary(Player player, double salary) {
        salarys.put(player, salary);
    }

    public static void addSalary(Player player, double salary) {
        setSalary(player, getSalary(player) + salary);
    }

}
