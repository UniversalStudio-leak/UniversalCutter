package ru.universalstudio.universalcutter.utils;

import java.text.*;
import java.util.*;
import org.bukkit.*;
import java.util.stream.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import java.util.concurrent.*;
import org.bukkit.configuration.file.*;
import ru.universalstudio.universalcutter.utils.actionbar.*;

/**
 * @Author source code: NaulbiMIX
 * @Author plugin code: UniversalStudio
 * @Author default source code: WinLocker02 (Thank pasting wAxes -> UniversalAxe)
 */

public class Utils {

    private static FileConfiguration config;

    public static FileConfiguration getConfig() {
        return config != null ? config : (config = Config.getFile("config.yml"));
    }

    public static void reloadConfig() {
        config = Config.getFile("config.yml");
    }

    public static boolean isIn(int x1, int y1, int z1, int x2, int y2, int z2, int x, int y, int z) {
        return Math.min(x1, x2) <= x && Math.min(y1, y2) <= y && Math.min(z1, z2) <= z && Math.max(x1, x2) >= x && Math.max(y1, y2) >= y && Math.max(z1, z2) >= z;
    }

    public static boolean isWood(Material material) {
        return material.getId() == 17 || material.getId() == 162;
    }

    public static boolean isLeaves(Material material) {
        return material.getId() == 18 || material.getId() == 161;
    }

    public static boolean isTree(Material material) {
        return isLeaves(material) || isWood(material);
    }

    public static double random(double d, double d2) {
        return ThreadLocalRandom.current().nextDouble(d, d2);
    }

    public static String getMessage(String path) {
        return getConfig().getString("messages." + path);
    }

    public static String getString(String path) {
        return getConfig().getString(path);
    }

    public static List<String> getStringList(String path) {
        return getConfig().getStringList(path);
    }

    public static int getInt(String path) {
        return getConfig().getInt(path);
    }

    public static double getDouble(String path) {
        return getConfig().getDouble(path);
    }

    public static boolean getBoolean(String path) {
        return getConfig().getBoolean(path);
    }

    public static String format(double var0) {
        return (new DecimalFormat("#0.00")).format(var0);
    }


    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> color(List<String> text) {
        return text.stream().map(x -> color(x)).collect(Collectors.toList());
    }

    public static boolean has(CommandSender player, String permission) { // прост винлокер берёт одни и те же утилитки и ему насрать используется тот или иной код. на оптимизацию похуй, а мне не похуй. сказали без костылей - доплатите и получите. сказали на высшем сорте и без костылей - распишите и получите, высший сорт.
        if(!player.hasPermission(permission)) {
            sendMessage(player, getConfig().getString("messages.no-permission"));
            return false;
        }
        return true;
    }

    public static void sendMessage(CommandSender player, String text) {

        if(text.isEmpty()) return;

        for(String line : text.split(";")) {
            line = line.replace("%player%", player.getName());

            if(line.startsWith("title:")) {
                if(player instanceof Player)
                    Title.sendTitle((Player) player, line.split("title:")[1]);
            }
            else if(line.startsWith("actionbar:")) {
                if(player instanceof Player)
                    Actionbar.sendActionbar((Player) player, line.split("actionbar:")[1]);
            }
            else {
                player.sendMessage(color(getMessage("prefix") + line));
            }
        }
    }

}