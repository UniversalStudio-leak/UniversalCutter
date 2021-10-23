package ru.universalstudio.universalcutter.tree;

import java.util.*;
import org.bukkit.*;
import org.bukkit.block.*;
import ru.universalstudio.universalcutter.utils.*;

public class TreeUtil {

    private List<Block> blocks = new ArrayList<>();
    private int count = 0;

    public Tree getTree(Location location) {
        this.rec(location.getBlock());
        return new Tree(this.getBlocks(), this.count);
    }

    public void rec(Block block) {
        if (!this.getBlocks().contains(block)) {
            if (Utils.isTree(block.getType())) {
                this.blocks.add(block);
                if (Utils.isWood(block.getType())) {
                    ++this.count;
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

    public List<Block> getBlocks() {
        return this.blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
    
}
