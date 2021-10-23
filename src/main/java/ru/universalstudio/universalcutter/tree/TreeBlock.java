package ru.universalstudio.universalcutter.tree;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class TreeBlock {

    private byte data;
    private Material material;
    private Location location;

    public TreeBlock(Block block) {
        this.setData(block.getData());
        this.setMaterial(block.getType());
        this.setLocation(block.getLocation());
    }

    public byte getData() {
        return this.data;
    }

    public void setData(byte data) {
        this.data = data;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
