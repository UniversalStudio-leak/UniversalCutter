package ru.universalstudio.universalcutter.utils;

import org.bukkit.entity.*;
import ru.universalstudio.universalcutter.*;

public class Econom {

    public static void withdraw(Player player, double money) {
        Main.economy.withdrawPlayer(player, money);
    }

    public static void deposit(Player player, double money) {
        Main.economy.depositPlayer(player, money);
    }

    public static double getBalance(Player player) {
        return Main.economy.getBalance(player);
    }
}
