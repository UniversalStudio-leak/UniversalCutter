package ru.universalstudio.universalcutter.utils;

import java.util.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import ru.universalstudio.universalcutter.*;
import ru.universalstudio.universalcutter.tree.*;

public class Animation {

    public static void fall(Tree tree) {
        TreeBackup treeBackup = new TreeBackup(tree);
        for (Block block : tree.getBlocks()) {
            if (treeBackup.getRegion() == null && RegionMgr.isInRegion(block.getLocation())) {
                treeBackup.setRegion(RegionMgr.getRegion(block.getLocation()));
            }
            Vector vector = Animation.getAnimation1();
            Material material = block.getType();
            byte by = block.getData();
            block.setType(Material.AIR);
            Location location = block.getLocation();
            FallingBlock fallingBlock = block.getWorld().spawnFallingBlock(new Location(location.getWorld(), location.getX() + 0.5, location.getY(), location.getZ() + 0.5), material, by);
            fallingBlock.setDropItem(false);
            fallingBlock.setHurtEntities(false);
            fallingBlock.setInvulnerable(true);
            fallingBlock.setVelocity(vector);
            fallingBlock.setCustomNameVisible(false);
            fallingBlock.setCustomName("#tree");
        }
        Main.trees.put(treeBackup, (System.currentTimeMillis() / 1000L));
    }

    public static void back(TreeBackup tree) {
        List<FallingBlock> blocks = new ArrayList<>();
        for (TreeBlock treeBlock : tree.getBlocks()) {
            Location location = treeBlock.getLocation();
            FallingBlock fallingBlock = location.getWorld().spawnFallingBlock(
                    new Location(
                            location.getWorld(),
                            location.getX() + 0.5,
                            location.getY() + 3.1,
                            location.getZ() + 0.5
                    ),
                    treeBlock.getMaterial(),
                    treeBlock.getData()
            );
            fallingBlock.setDropItem(false);
            fallingBlock.setHurtEntities(false);
            fallingBlock.setInvulnerable(true);
            fallingBlock.setCustomNameVisible(false);
            fallingBlock.setCustomName("#tree2");
            blocks.add(fallingBlock);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (FallingBlock fb : blocks) {
                    if (fb != null)
                        fb.remove();
                }
                for (TreeBlock block : tree.getBlocks()) {
                    Block bl = block.getLocation().getBlock();
                    bl.setType(block.getMaterial());
                    bl.setData(block.getData());
                }
            }
        },  13L);
    }

    public static void hardBack(TreeBackup treeBackup) {
        for (TreeBlock treeBlock : treeBackup.getBlocks()) {
            Block block = treeBlock.getLocation().getBlock();
            block.setType(treeBlock.getMaterial());
            block.setData(treeBlock.getData());
        }
    }

    public static Vector getAnimation1() {
        Vector var0 = new Vector(0, 0, 0);
        var0.setX(Utils.random(-0.1D, 0.1D));
        var0.setZ(Utils.random(-0.1D, 0.1D));
        var0.setY(Utils.random(0.35D, 0.75D));
        return var0;
    }

    public static void animation2(Location var0) {
        if (Utils.random(0.0D, 100.0D) < 15.0D) {
            var0.getWorld().playEffect(var0, Effect.EXPLOSION, 0);
        }
    }

}
