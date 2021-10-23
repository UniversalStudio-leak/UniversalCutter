package ru.universalstudio.universalcutter;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import ru.universalstudio.universalcutter.tree.Tree;
import ru.universalstudio.universalcutter.tree.TreeChecker;
import ru.universalstudio.universalcutter.tree.TreeUtil;
import ru.universalstudio.universalcutter.utils.Animation;
import ru.universalstudio.universalcutter.utils.RegionMgr;
import ru.universalstudio.universalcutter.utils.Utils;

public class EventListener implements Listener {
    private Main plugin;

    public EventListener(Main var1) {
        this.plugin = var1;
    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent var1) {
        Player var3 = var1.getPlayer();
        if (RegionMgr.isInRegion(var1.getBlock().getLocation())) {
            var1.setCancelled(true);
            if (!Utils.isWood(var1.getBlock().getType())) {
                if (var3.isOp()) {
                    var1.setCancelled(false);
                } else {
                    Utils.sendMessage(var3, Utils.getMessage("onlyWood"));
                }

            } else {
                TreeChecker var4 = new TreeChecker();
                if (!var4.checkTree(var1.getBlock().getLocation())) {
                    if (var3.isOp()) {
                        var1.setCancelled(false);
                    } else {
                        Utils.sendMessage(var3, Utils.getMessage("onlyWood"));
                    }

                } else {
                    Block var5 = var1.getBlock();
                    TreeUtil var6 = new TreeUtil();
                    Tree var7 = null;
                    long var8 = (long)(var5.getLocation().getBlockX() * 100000 + var5.getLocation().getBlockZ());
                    int var2;
                    if (!Main.strength.containsKey(var8)) {
                        var7 = var6.getTree(var5.getLocation());
                        var2 = var7.getStrenght() - 1;
                        Main.strength.put(var8, var2);
                    } else {
                        var2 = (Integer)Main.strength.get(var8) - 1;
                        Main.strength.put(var8, var2);
                    }

                    Utils.sendMessage(var3, Utils.getMessage("cut").replace("%number", Integer.toString(var2)));
                }
            }
        }
    }

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent var1) {
        Player var2 = var1.getPlayer();
        if (RegionMgr.isInRegion(var1.getBlock().getLocation())) {
            if (!var2.isOp()) {
                var1.setCancelled(true);
            }

        }
    }

    @EventHandler
    public void onFallingBlockLand(EntityChangeBlockEvent var1) {
        if (var1.getEntity() != null) {
            if (var1.getEntity().getCustomName() != null) {
                if (var1.getEntity().getCustomName().equals("#tree2")) {
                    var1.getEntity().remove();
                    if (var1.getBlock() != null) {
                        var1.getBlock().setType(Material.AIR);
                        Animation.animation2(var1.getBlock().getLocation());
                    }

                    var1.setCancelled(true);
                }

                if (var1.getEntity().getCustomName().equals("#tree")) {
                    var1.getEntity().remove();
                    if (var1.getBlock() != null) {
                        var1.getBlock().setType(Material.AIR);
                        Animation.animation2(var1.getBlock().getLocation());
                    }

                    var1.setCancelled(true);
                }

            }
        }
    }
}
