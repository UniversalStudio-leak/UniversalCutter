package ru.universalstudio.universalcutter.utils;

import org.bukkit.*;
import com.sk89q.worldguard.protection.*;
import ru.universalstudio.universalcutter.*;
import com.sk89q.worldguard.protection.regions.*;

public class RegionMgr {

    public static String getRegion(Location location) {
        ApplicableRegionSet applicableRegionSet = Main.getWorldGuard().getRegionManager(location.getWorld()).getApplicableRegions(location);
        for (ProtectedRegion protectedRegion : applicableRegionSet.getRegions()) {
            if (RegionMgr.isRegion(protectedRegion.getId())) {
                return protectedRegion.getId();
            }
        }
        return null;
    }

    public static boolean isInRegion(Location location) {
        return getRegion(location) != null;
    }

    public static double getEarnings(String region) {
        return Utils.getConfig().getInt("regions." + region + ".earn");
    }

    public static int getCooldown(String region) {
        return Utils.getConfig().getInt("regions." + region + ".cooldown");
    }

    public static boolean isRegion(String region) {
        for (String s : Utils.getConfig().getConfigurationSection("regions").getKeys(false)) {
            if(s.equals(region)) {
                return true;
            }
        }
        return false;
    }
}
