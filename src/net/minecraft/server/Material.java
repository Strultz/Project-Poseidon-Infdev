package net.minecraft.server;

public class Material {

    public static final Material AIR = new MaterialTransparent();
    public static final Material GRASS = new Material();
    public static final Material EARTH = new Material();
    public static final Material WOOD = (new Material());
    public static final Material STONE = (new Material());
    public static final Material ORE = (new Material());
    public static final Material WATER = (new MaterialLiquid());
    public static final Material LAVA = (new MaterialLiquid());
    public static final Material LEAVES = (new Material());
    public static final Material PLANT = (new MaterialLogic());
    public static final Material SPONGE = new Material();
    public static final Material CLOTH = (new Material());
    public static final Material FIRE = (new MaterialTransparent());
    public static final Material SAND = new Material();
    public static final Material ORIENTABLE = (new MaterialLogic());
    public static final Material SHATTERABLE = (new Material());
    public static final Material TNT = (new Material());

    public boolean isLiquid() {
        return false;
    }

    public boolean isBuildable() {
        return true;
    }

    public boolean blocksLight() {
        return true;
    }

    public boolean isSolid() {
        return true;
    }
}
