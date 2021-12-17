package net.minecraft.server;

public class Material {

    public static final Material AIR = new MaterialTransparent();
    public static final Material GRASS = new Material();
    public static final Material EARTH = new Material();
    public static final Material WOOD = (new Material()).o();
    public static final Material STONE = (new Material()).n();
    public static final Material ORE = (new Material()).n();
    public static final Material WATER = (new MaterialLiquid()).k();
    public static final Material LAVA = (new MaterialLiquid()).k();
    public static final Material LEAVES = (new Material()).o().k();
    public static final Material PLANT = (new MaterialLogic()).k();
    public static final Material SPONGE = new Material();
    public static final Material CLOTH = (new Material()).o();
    public static final Material FIRE = (new MaterialTransparent()).k();
    public static final Material SAND = new Material();
    public static final Material ORIENTABLE = (new MaterialLogic()).k();
    public static final Material SHATTERABLE = (new Material());
    public static final Material TNT = (new Material()).o();
    public static final Material CORAL = (new Material()).k();
    private boolean canBurn;
    private boolean E;
    private boolean G = true;
    private int H;

    public Material() {
    }

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

    public final boolean liquidSolidCheck() {
        return !this.isLiquid() && !this.isSolid();
    }

    private Material n() {
        this.G = false;
        return this;
    }

    private Material o() {
        this.canBurn = true;
        return this;
    }

    public boolean isBurnable() {
        return this.canBurn;
    }

    public Material f() {
        this.E = true;
        return this;
    }

    public boolean isReplacable() {
        return this.E;
    }

    public boolean i() {
        return this.G;
    }

    public int j() {
        return this.H;
    }

    protected Material k() {
        this.H = 1;
        return this;
    }

    protected Material l() {
        this.H = 2;
        return this;
    }
}
