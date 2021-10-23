package ru.universalstudio.universalcutter;

import java.util.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.*;
import me.clip.placeholderapi.*;
import net.milkbowl.vault.economy.*;
import com.sk89q.worldguard.bukkit.*;
import ru.universalstudio.universalcutter.tree.*;
import ru.universalstudio.universalcutter.utils.*;

public class Main extends JavaPlugin {

    private int timerId;
    private static Main instance;
    public static HashMap<TreeBackup, Long> trees = new HashMap<>();
    public static HashMap<Long, Integer> strength = new HashMap<>();
    public static Economy economy = null;

    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        this.loadPlugin();
    }

    public void onDisable() {
        backAllTrees();
        Bukkit.getScheduler().cancelTask(timerId);
    }

    void loadPlugin() {
        instance = this;
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[UniversalCutter] Plugin recompiled and cracked by NaulbiMIX | Sponsored by FlatiCommunity (https://t.me/flaticommunity) | Specially publication for https://teletype.in/@naulbimix/rumine"); // да и кстати на деле если чё, то сурсы писал я сам, идею брал у универсалов. а по закону идею пиздить не запрещёно, поэтому у меня авторское право на это говно :)
        Utils.getConfig();
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
       /* if (Utils.getConfig().getString("KEY") == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[UniversalCutter] You are not licensed! You can buy it on our website: u-studio.su");
            Bukkit.getServer().shutdown();
        } else */ if ((new UGuard("лицензию спиздили", "и крякнули", this)).register()) {
            getCommand("ucutter").setExecutor(new Execute());
            // это говно не регает нынче, пидорасы переписали систему так что я заебался её патчить. на деле просто на старых обновах нету многих фиксов, поэтому хочется сидеть на новых с патчами :). Ну крч PlaceholderAPI 2.10.0 работает атлична
            // короче обнова 2.10.7 всё паламаля, так как разрабы решили переписать. поэтому или патчите новые версии или 2.10.6 к вашим услугам, хотя я щас понял что особых фиксов они не добавили, поэтому пока что 2.10.6 к вашим услугам.
            PlaceholderAPI.registerPlaceholderHook("ucutter", new Placeholder());
            startTimer();
            setupEconomy();
            getLogger().info("Плагин успешно включён.");
        }
    }

    public void backAllTrees() {
        for (TreeBackup treeBackup : trees.keySet()) {
            Animation.hardBack(treeBackup);
        }
    }

    public void startTimer() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                long l = System.currentTimeMillis() / 1000L;
                List<TreeBackup> linkedList = new ArrayList<>();
                for (TreeBackup treeBackup : Main.trees.keySet()) {
                    long l2 = Main.trees.get(treeBackup);
                    if ((l2 + (long)RegionMgr.getCooldown(treeBackup.getRegion())) <= l) {
                        Animation.back(treeBackup);
                        linkedList.add(treeBackup);
                    }
                }
                for (TreeBackup treeBackup : linkedList) {
                    Main.trees.remove(treeBackup);
                }
            }
        }, 20L, 20L);
    }

    public static boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> registeredServiceProvider = Bukkit.getServicesManager().getRegistration(Economy.class);
        return false;
    }

    public static WorldGuardPlugin getWorldGuard() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
        if (plugin instanceof WorldGuardPlugin) {
            return (WorldGuardPlugin)plugin;
        }
        return null;
    }
}
