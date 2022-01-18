package net.minecraft.server;

import com.legacyminecraft.poseidon.PoseidonConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Block {

    public static final StepSound d = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound e = new StepSound("wood", 1.0F, 1.0F);
    public static final StepSound f = new StepSound("gravel", 1.0F, 1.0F);
    public static final StepSound g = new StepSound("grass", 1.0F, 1.0F);
    public static final StepSound h = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound i = new StepSound("stone", 1.0F, 1.5F);
    public static final StepSound j = new StepSoundStone("stone", 1.0F, 1.0F);
    public static final StepSound k = new StepSound("cloth", 1.0F, 1.0F);
    public static final StepSound l = new StepSoundSand("sand", 1.0F, 1.0F);
    public static final Block[] byId = new Block[256];
    public static final boolean[] n = new boolean[256];
    public static final boolean[] o = new boolean[256];
    public static final boolean[] isTileEntity = new boolean[256];
    public static final int[] q = new int[256];
    public static final boolean[] r = new boolean[256];
    public static final int[] s = new int[256];
    public static final boolean[] t = new boolean[256];
    public static final Block STONE = (new BlockStone(1, 1)).c(1.5F).b(10.0F).a(h).a("stone");
    public static final BlockGrass GRASS = (BlockGrass) (new BlockGrass(2)).c(0.6F).a(g).a("grass");
    public static final Block DIRT = (new BlockDirt(3, 2)).c(0.5F).a(f).a("dirt");
    public static final Block COBBLESTONE = (new Block(4, 16, Material.STONE)).c(2.0F).b(10.0F).a(h).a("stonebrick");
    public static final Block WOOD = (new Block(5, 4, Material.WOOD)).c(2.0F).b(5.0F).a(e).a("wood").g();
    public static final Block SAPLING = (new BlockSapling(6, 15)).c(0.0F).a(g).a("sapling").g();
    public static final Block BEDROCK = (new Block(7, 17, Material.STONE)).i().b(6000000.0F).a(h).a("bedrock").n();
    public static final Block WATER = (new BlockFlowing(8, Material.WATER)).c(100.0F).f(3).a("water").n().g();
    public static final Block STATIONARY_WATER = (new BlockStationary(9, Material.WATER)).c(100.0F).f(3).a("water").n().g();
    public static final Block LAVA = (new BlockFlowing(10, Material.LAVA)).c(0.0F).a(1.0F).f(255).a("lava").n().g();
    public static final Block STATIONARY_LAVA = (new BlockStationary(11, Material.LAVA)).c(100.0F).a(1.0F).f(255).a("lava").n().g();
    public static final Block SAND = (new BlockSand(12, 18)).c(0.5F).a(l).a("sand");
    public static final Block GRAVEL = (new BlockGravel(13, 19)).c(0.6F).a(f).a("gravel");
    public static final Block GOLD_ORE = (new BlockOre(14, 32)).c(3.0F).b(5.0F).a(h).a("oreGold");
    public static final Block IRON_ORE = (new BlockOre(15, 33)).c(3.0F).b(5.0F).a(h).a("oreIron");
    public static final Block COAL_ORE = (new BlockOre(16, 34)).c(3.0F).b(5.0F).a(h).a("oreCoal");
    public static final Block LOG = (new BlockLog(17)).c(2.0F).a(e).a("log").g();
    public static final BlockLeaves LEAVES = (BlockLeaves) (new BlockLeaves(18, 52)).c(0.2F).f(1).a(g).a("leaves").n().g();
    public static final Block SPONGE = (new BlockSponge(19)).c(0.6F).a(g).a("sponge");
    public static final Block GLASS = (new BlockGlass(20, 49, Material.SHATTERABLE, false)).c(0.3F).a(j).a("glass");
    public static final Block RED_WOOL = (new Block(21, 64, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block ORANGE_WOOL = (new Block(22, 65, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block YELLOW_WOOL = (new Block(23, 66, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block CHARTREUSE_WOOL = (new Block(24, 67, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block GREEN_WOOL = (new Block(25, 68, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block SPRING_GREEN_WOOL = (new Block(26, 69, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block CYAN_WOOL = (new Block(27, 70, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block CAPRI_WOOL = (new Block(28, 71, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block ULTRAMARINE_WOOL = (new Block(29, 72, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block VIOLET_WOOL = (new Block(30, 73, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block PURPLE_WOOL = (new Block(31, 74, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block MAGENTA_WOOL = (new Block(32, 75, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block ROSE_WOOL = (new Block(33, 76, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block DARK_GRAY_WOOL = (new Block(34, 77, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block WOOL = (new Block(35, 78, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final Block WHITE_WOOL = (new Block(36, 79, Material.CLOTH)).c(0.8F).a(k).a("cloth");
    public static final BlockFlower YELLOW_FLOWER = (BlockFlower) (new BlockFlower(37, 13)).c(0.0F).a(g).a("flower");
    public static final BlockFlower RED_ROSE = (BlockFlower) (new BlockFlower(38, 12)).c(0.0F).a(g).a("rose");
    public static final BlockFlower BROWN_MUSHROOM = (BlockFlower) (new BlockMushroom(39, 29)).c(0.0F).a(g).a(0.125F).a("mushroom");
    public static final BlockFlower RED_MUSHROOM = (BlockFlower) (new BlockMushroom(40, 28)).c(0.0F).a(g).a("mushroom");
    public static final Block GOLD_BLOCK = (new BlockOreBlock(41, 23)).c(3.0F).b(10.0F).a(i).a("blockGold");
    public static final Block IRON_BLOCK = (new BlockOreBlock(42, 22)).c(5.0F).b(10.0F).a(i).a("blockIron");
    public static final Block DOUBLE_STEP = (new BlockStep(43, true)).c(2.0F).b(10.0F).a(h).a("stoneSlab");
    public static final Block STEP = (new BlockStep(44, false)).c(2.0F).b(10.0F).a(h).a("stoneSlab");
    public static final Block BRICK = (new Block(45, 7, Material.STONE)).c(2.0F).b(10.0F).a(h).a("brick");
    public static final Block TNT = (new BlockTNT(46, 8)).c(0.0F).a(g).a("tnt");
    public static final Block BOOKSHELF = (new BlockBookshelf(47, 35)).c(1.5F).a(e).a("bookshelf");
    public static final Block MOSSY_COBBLESTONE = (new Block(48, 36, Material.STONE)).c(2.0F).b(10.0F).a(h).a("stoneMoss");
    public static final Block OBSIDIAN = (new BlockObsidian(49, 37)).c(10.0F).b(2000.0F).a(h).a("obsidian");
    public static final Block TORCH = (new BlockTorch(50, 80)).c(0.0F).a(0.9375F).a(e).a("torch").g();
    public static final BlockFire FIRE = (BlockFire) (new BlockFire(51, 31)).c(0.0F).a(1.0F).a(e).a("fire").n().g();
    public static final Block WATER_SOURCE = (new BlockSource(52, Block.WATER.id)).c(0.0F).a(e).a("watersource").g();
    public static final Block LAVA_SOURCE = (new BlockSource(53, Block.LAVA.id)).c(0.0F).a(e).a("lavasource").g();
    public static final Block CHEST = (new BlockChest(54)).c(2.5F).a(e).a("chest").g();
    public static final Block GEAR = (new BlockGears()).c(0.5F).a(i).a("gear").g();
    public static final Block DIAMOND_ORE = (new BlockOre(56, 50)).c(3.0F).b(5.0F).a(h).a("oreDiamond");
    public static final Block DIAMOND_BLOCK = (new BlockOreBlock(57, 24)).c(5.0F).b(10.0F).a(i).a("blockDiamond");
    public static final Block WORKBENCH = (new BlockWorkbench(58)).c(2.5F).a(e).a("workbench");
    public static final Block CROPS = (new BlockCrops(59, 88)).c(0.0F).a(g).a("crops").n().g();
    public static final Block SOIL = (new BlockSoil(60)).c(0.6F).a(f).a("farmland");
    public static final Block FURNACE = (new BlockFurnace(61, false)).c(3.5F).a(h).a("furnace").g();
    public static final Block BURNING_FURNACE = (new BlockFurnace(62, true)).c(3.5F).a(h).a(0.875F).a("furnace").g();
    public static final Block SIGN_POST = (new BlockSign(63, TileEntitySign.class)).c(1.0F).a(e).a("sign").n().g();
    public static final Block WOODEN_DOOR = (new BlockDoor(64, Material.WOOD)).c(3.0F).a(e).a("doorWood").n().g();
    public static final Block LADDER = (new BlockLadder(65, 83)).c(0.4F).a(e).a("ladder").g();
    public static final Block RAILS = (new BlockMinecartTrack(66, 128)).c(0.7F).a(i).a("rail").g();
    public static final List<Integer> leafDecayBlacklist = Arrays.asList(PoseidonConfig.getInstance().getTreeBlacklistIDs());
    public int textureId;
    public final int id;
    protected float strength;
    protected float durability;
    protected boolean bq;
    protected boolean br;
    public double minX;
    public double minY;
    public double minZ;
    public double maxX;
    public double maxY;
    public double maxZ;
    public StepSound stepSound;
    public float bz;
    public final Material material;
    public float frictionFactor;
    private String name;

    protected Block(int i, Material material) {
        this.bq = true;
        this.br = true;
        this.stepSound = d;
        this.bz = 1.0F;
        this.frictionFactor = 0.6F;
        if (byId[i] != null) {
            throw new IllegalArgumentException("Slot " + i + " is already occupied by " + byId[i] + " when adding " + this);
        } else {
            this.material = material;
            byId[i] = this;
            this.id = i;
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            o[i] = this.a();
            q[i] = this.a() ? 255 : 0;
            r[i] = !material.blocksLight();
            isTileEntity[i] = false;
        }
    }

    protected Block g() {
        t[this.id] = true;
        return this;
    }

    protected void h() {
    }

    protected Block(int i, int j, Material material) {
        this(i, material);
        this.textureId = j;
    }

    protected Block a(StepSound stepsound) {
        this.stepSound = stepsound;
        return this;
    }

    protected Block f(int i) {
        q[this.id] = i;
        return this;
    }

    protected Block a(float f) {
        s[this.id] = (int) (15.0F * f);
        return this;
    }

    protected Block b(float f) {
        this.durability = f * 3.0F;
        return this;
    }

    public boolean b() {
        return true;
    }

    protected Block c(float f) {
        this.strength = f;
        if (this.durability < f * 5.0F) {
            this.durability = f * 5.0F;
        }

        return this;
    }

    protected Block i() {
        this.c(-1.0F);
        return this;
    }

    public float j() {
        return this.strength;
    }

    protected Block a(boolean flag) {
        n[this.id] = flag;
        return this;
    }

    public void a(float f, float f1, float f2, float f3, float f4, float f5) {
        this.minX = (double) f;
        this.minY = (double) f1;
        this.minZ = (double) f2;
        this.maxX = (double) f3;
        this.maxY = (double) f4;
        this.maxZ = (double) f5;
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return iblockaccess.getMaterial(i, j, k).isBuildable();
    }

    public int a(int i, int j) {
        return this.a(i);
    }

    public int a(int i) {
        return this.textureId;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {
        AxisAlignedBB axisalignedbb1 = this.e(world, i, j, k);

        if (axisalignedbb1 != null && axisalignedbb.a(axisalignedbb1)) {
            arraylist.add(axisalignedbb1);
        }
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        return AxisAlignedBB.b((double) i + this.minX, (double) j + this.minY, (double) k + this.minZ, (double) i + this.maxX, (double) j + this.maxY, (double) k + this.maxZ);
    }

    public boolean a() {
        return true;
    }

    public boolean a(int i, boolean flag) {
        return this.k_();
    }

    public boolean k_() {
        return true;
    }

    public void a(World world, int i, int j, int k, Random random) {
    }

    public void postBreak(World world, int i, int j, int k, int l) {
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
    }

    public int c() {
        return 10;
    }

    public void c(World world, int i, int j, int k) {
    }

    public void remove(World world, int i, int j, int k) {
    }

    public int a(Random random) {
        return 1;
    }

    public int a(int i, Random random) {
        return this.id;
    }

    public float getDamage(EntityHuman entityhuman) {
        return this.strength < 0.0F ? 0.0F : (!entityhuman.b(this) ? 1.0F / this.strength / 100.0F : entityhuman.a(this) / this.strength / 30.0F);
    }

    public final void g(World world, int i, int j, int k, int l) {
        this.dropNaturally(world, i, j, k, l, 1.0F);
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f) {
        if (!world.isStatic) {
            int i1 = this.a(world.random);

            for (int j1 = 0; j1 < i1; ++j1) {
                // CraftBukkit - <= to < to allow for plugins to completely disable block drops from explosions
                if (world.random.nextFloat() < f) {
                    int k1 = this.a(l, world.random);

                    if (k1 > 0) {
                        this.a(world, i, j, k, new ItemStack(k1, 1, this.a_(l)));
                    }
                }
            }
        }
    }

    protected void a(World world, int i, int j, int k, ItemStack itemstack) {
        if (!world.isStatic) {
            float f = 0.7F;
            double d0 = (double) (world.random.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
            double d1 = (double) (world.random.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
            double d2 = (double) (world.random.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
            EntityItem entityitem = new EntityItem(world, (double) i + d0, (double) j + d1, (double) k + d2, itemstack);

            entityitem.pickupDelay = 10;
            world.addEntity(entityitem);
        }
    }

    protected int a_(int i) {
        return 0;
    }

    public float a(Entity entity) {
        return this.durability / 5.0F;
    }

    public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
        this.a(world, i, j, k);
        vec3d = vec3d.add((double) (-i), (double) (-j), (double) (-k));
        vec3d1 = vec3d1.add((double) (-i), (double) (-j), (double) (-k));
        Vec3D vec3d2 = vec3d.a(vec3d1, this.minX);
        Vec3D vec3d3 = vec3d.a(vec3d1, this.maxX);
        Vec3D vec3d4 = vec3d.b(vec3d1, this.minY);
        Vec3D vec3d5 = vec3d.b(vec3d1, this.maxY);
        Vec3D vec3d6 = vec3d.c(vec3d1, this.minZ);
        Vec3D vec3d7 = vec3d.c(vec3d1, this.maxZ);

        if (!this.a(vec3d2)) {
            vec3d2 = null;
        }

        if (!this.a(vec3d3)) {
            vec3d3 = null;
        }

        if (!this.b(vec3d4)) {
            vec3d4 = null;
        }

        if (!this.b(vec3d5)) {
            vec3d5 = null;
        }

        if (!this.c(vec3d6)) {
            vec3d6 = null;
        }

        if (!this.c(vec3d7)) {
            vec3d7 = null;
        }

        Vec3D vec3d8 = null;

        if (vec3d2 != null && (vec3d8 == null || vec3d.a(vec3d2) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d2;
        }

        if (vec3d3 != null && (vec3d8 == null || vec3d.a(vec3d3) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d3;
        }

        if (vec3d4 != null && (vec3d8 == null || vec3d.a(vec3d4) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d4;
        }

        if (vec3d5 != null && (vec3d8 == null || vec3d.a(vec3d5) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d5;
        }

        if (vec3d6 != null && (vec3d8 == null || vec3d.a(vec3d6) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d6;
        }

        if (vec3d7 != null && (vec3d8 == null || vec3d.a(vec3d7) < vec3d.a(vec3d8))) {
            vec3d8 = vec3d7;
        }

        if (vec3d8 == null) {
            return null;
        } else {
            byte b0 = -1;

            if (vec3d8 == vec3d2) {
                b0 = 4;
            }

            if (vec3d8 == vec3d3) {
                b0 = 5;
            }

            if (vec3d8 == vec3d4) {
                b0 = 0;
            }

            if (vec3d8 == vec3d5) {
                b0 = 1;
            }

            if (vec3d8 == vec3d6) {
                b0 = 2;
            }

            if (vec3d8 == vec3d7) {
                b0 = 3;
            }

            return new MovingObjectPosition(i, j, k, b0, vec3d8.add((double) i, (double) j, (double) k));
        }
    }

    private boolean a(Vec3D vec3d) {
        return vec3d == null ? false : vec3d.b >= this.minY && vec3d.b <= this.maxY && vec3d.c >= this.minZ && vec3d.c <= this.maxZ;
    }

    private boolean b(Vec3D vec3d) {
        return vec3d == null ? false : vec3d.a >= this.minX && vec3d.a <= this.maxX && vec3d.c >= this.minZ && vec3d.c <= this.maxZ;
    }

    private boolean c(Vec3D vec3d) {
        return vec3d == null ? false : vec3d.a >= this.minX && vec3d.a <= this.maxX && vec3d.b >= this.minY && vec3d.b <= this.maxY;
    }

    public void d(World world, int i, int j, int k) {
    }

    public boolean canPlace(World world, int i, int j, int k, int l) {
        return this.canPlace(world, i, j, k);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return true;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
        return false;
    }

    public void b(World world, int i, int j, int k, Entity entity) {
    }

    public void postPlace(World world, int i, int j, int k, int l) {
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman) {
    }

    public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k) {
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return false;
    }

    public void a(World world, int i, int j, int k, Entity entity) {
    }

    public boolean d(World world, int i, int j, int k, int l) {
        return false;
    }

    public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l) {
        this.g(world, i, j, k, l);
    }

    public boolean f(World world, int i, int j, int k) {
        return true;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
    }

    public Block a(String s) {
        this.name = "tile." + s;
        return this;
    }

    public String k() {
        return StatisticCollector.a(this.l() + ".name");
    }

    public String l() {
        return this.name;
    }

    public void a(World world, int i, int j, int k, int l, int i1) {
    }

    public boolean m() {
        return this.br;
    }

    protected Block n() {
        this.br = false;
        return this;
    }

    static {
        for (int i = 0; i < 256; ++i) {
            if (byId[i] != null && Item.byId[i] == null) {
                Item.byId[i] = new ItemBlock(i - 256);
                byId[i].h();
            }
        }

        r[0] = true;
    }
}
