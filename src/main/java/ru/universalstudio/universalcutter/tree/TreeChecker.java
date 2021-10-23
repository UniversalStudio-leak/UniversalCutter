package ru.universalstudio.universalcutter.tree;

import java.util.*;
import org.bukkit.*;
import org.bukkit.block.*;
import ru.universalstudio.universalcutter.utils.*;

public class TreeChecker {

    private List<Block> blocks = new ArrayList<>();
    private boolean isTree = false;

    public boolean checkTree(Location location) {
        this.rec(location.getBlock());
        return this.isTree;
    }

    public void rec(Block block) {
        if (!this.isTree) {
            if (Utils.isTree(block.getType())) {
                if (!this.getBlocks().contains(block)) {
                    this.blocks.add(block);
                    if (Utils.isLeaves(block.getType())) {
                        this.isTree = true;
                    }

                    Location location = block.getLocation();
                    this.rec((new Location(location.getWorld(), (location.getBlockX() + 1), location.getBlockY(), location.getBlockZ())).getBlock());
                    this.rec((new Location(location.getWorld(), (location.getBlockX() - 1), location.getBlockY(), location.getBlockZ())).getBlock());
                    this.rec((new Location(location.getWorld(), location.getBlockX(), (location.getBlockY() + 1), location.getBlockZ())).getBlock());
                    this.rec((new Location(location.getWorld(), location.getBlockX(), (location.getBlockY() - 1), location.getBlockZ())).getBlock());
                    this.rec((new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), (location.getBlockZ() + 1))).getBlock());
                    this.rec((new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), (location.getBlockZ() - 1))).getBlock());
                }
            }
        }
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}
