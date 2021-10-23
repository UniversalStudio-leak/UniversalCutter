package ru.universalstudio.universalcutter.tree;

import java.util.*;
import org.bukkit.block.*;

public class Tree {

    private List<Block> blocks = new ArrayList<>();
    private int strenght;

    public Tree(List<Block> blocks, int strenght) {
        this.setBlocks(blocks);
        this.strenght = strenght;
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public int getStrenght() {
        return this.strenght;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }
}
