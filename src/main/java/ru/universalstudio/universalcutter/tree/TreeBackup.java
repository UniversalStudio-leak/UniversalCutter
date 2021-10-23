package ru.universalstudio.universalcutter.tree;

import java.util.*;
import org.bukkit.block.*;

public class TreeBackup {

    private List<TreeBlock> blocks = new ArrayList<>();
    private String region = null;

    public TreeBackup(Tree tree) {
        for(Block block : tree.getBlocks()) {
            this.addBlock(new TreeBlock(block));
        }
    }

    public void addBlock(TreeBlock treeBlock) {
        this.blocks.add(treeBlock);
    }

    public List<TreeBlock> getBlocks() {
        return this.blocks;
    }

    public void setBlocks(List<TreeBlock> blocks) {
        this.blocks = blocks;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
