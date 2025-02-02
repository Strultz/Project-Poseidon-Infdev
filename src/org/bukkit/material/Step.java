package org.bukkit.material;

import org.bukkit.Material;

import java.util.HashSet;

/**
 * Represents the different types of steps.
 */
public class Step extends MaterialData {
    private static HashSet<Material> stepTypes = new HashSet<Material>();
    static {
        stepTypes.add(Material.STONE);
    }

    public Step() {
        super(Material.STEP);
    }

    public Step(final int type) {
        super(type);
    }

    public Step(final Material type) {
        super((stepTypes.contains(type)) ? Material.STEP : type);
        if (stepTypes.contains(type)) {
            setMaterial(type);
        }
    }

    public Step(final int type, final byte data) {
        super(type, data);
    }

    public Step(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the current Material this step is made of
     *
     * @return Material of this step
     */
    public Material getMaterial() {
        return Material.STONE;
    }

    /**
     * Sets the material this step is made of
     *
     * @param material New material of this step
     */
    public void setMaterial(Material material) {
        setData((byte) 0x0);
    }

    @Override
    public String toString() {
        return getMaterial() + " " + super.toString();
    }
}
