package net.minecraft.server;

import java.util.Random;

public class Item {

    protected static Random b = new Random();
    public static Item[] byId = new Item[32000];
    public static Item IRON_SPADE = (new ItemSpade(0, EnumToolMaterial.IRON)).a(2, 5).a("shovelIron");
    public static Item IRON_PICKAXE = (new ItemPickaxe(1, EnumToolMaterial.IRON)).a(2, 6).a("pickaxeIron");
    public static Item IRON_AXE = (new ItemAxe(2, EnumToolMaterial.IRON)).a(2, 7).a("hatchetIron");
    public static Item FLINT_AND_STEEL = (new ItemFlintAndSteel(3)).a(5, 0).a("flintAndSteel");
    public static Item APPLE = (new ItemFood(4, 4, false)).a(10, 0).a("apple");
    public static Item BOW = (new ItemBow(5)).a(5, 1).a("bow");
    public static Item ARROW = (new Item(6)).a(5, 2).a("arrow");
    public static Item COAL = (new ItemCoal(7)).a(7, 0).a("coal");
    public static Item DIAMOND = (new Item(8)).a(7, 3).a("emerald");
    public static Item IRON_INGOT = (new Item(9)).a(7, 1).a("ingotIron");
    public static Item GOLD_INGOT = (new Item(10)).a(7, 2).a("ingotGold");
    public static Item IRON_SWORD = (new ItemSword(11, EnumToolMaterial.IRON)).a(2, 4).a("swordIron");
    public static Item WOOD_SWORD = (new ItemSword(12, EnumToolMaterial.WOOD)).a(0, 4).a("swordWood");
    public static Item WOOD_SPADE = (new ItemSpade(13, EnumToolMaterial.WOOD)).a(0, 5).a("shovelWood");
    public static Item WOOD_PICKAXE = (new ItemPickaxe(14, EnumToolMaterial.WOOD)).a(0, 6).a("pickaxeWood");
    public static Item WOOD_AXE = (new ItemAxe(15, EnumToolMaterial.WOOD)).a(0, 7).a("hatchetWood");
    public static Item STONE_SWORD = (new ItemSword(16, EnumToolMaterial.STONE)).a(1, 4).a("swordStone");
    public static Item STONE_SPADE = (new ItemSpade(17, EnumToolMaterial.STONE)).a(1, 5).a("shovelStone");
    public static Item STONE_PICKAXE = (new ItemPickaxe(18, EnumToolMaterial.STONE)).a(1, 6).a("pickaxeStone");
    public static Item STONE_AXE = (new ItemAxe(19, EnumToolMaterial.STONE)).a(1, 7).a("hatchetStone");
    public static Item DIAMOND_SWORD = (new ItemSword(20, EnumToolMaterial.DIAMOND)).a(3, 4).a("swordDiamond");
    public static Item DIAMOND_SPADE = (new ItemSpade(21, EnumToolMaterial.DIAMOND)).a(3, 5).a("shovelDiamond");
    public static Item DIAMOND_PICKAXE = (new ItemPickaxe(22, EnumToolMaterial.DIAMOND)).a(3, 6).a("pickaxeDiamond");
    public static Item DIAMOND_AXE = (new ItemAxe(23, EnumToolMaterial.DIAMOND)).a(3, 7).a("hatchetDiamond");
    public static Item STICK = (new Item(24)).a(5, 3).g().a("stick");
    public static Item BOWL = (new Item(25)).a(7, 4).a("bowl");
    public static Item MUSHROOM_SOUP = (new ItemSoup(26, 10)).a(8, 4).a("mushroomStew");
    public static Item GOLD_SWORD = (new ItemSword(27, EnumToolMaterial.GOLD)).a(4, 4).a("swordGold");
    public static Item GOLD_SPADE = (new ItemSpade(28, EnumToolMaterial.GOLD)).a(4, 5).a("shovelGold");
    public static Item GOLD_PICKAXE = (new ItemPickaxe(29, EnumToolMaterial.GOLD)).a(4, 6).a("pickaxeGold");
    public static Item GOLD_AXE = (new ItemAxe(30, EnumToolMaterial.GOLD)).a(4, 7).a("hatchetGold");
    public static Item STRING = (new Item(31)).a(8, 0).a("string");
    public static Item FEATHER = (new Item(32)).a(8, 1).a("feather");
    public static Item SULPHUR = (new Item(33)).a(8, 2).a("sulphur");
    public static Item WOOD_HOE = (new ItemHoe(34, EnumToolMaterial.WOOD)).a(0, 8).a("hoeWood");
    public static Item STONE_HOE = (new ItemHoe(35, EnumToolMaterial.STONE)).a(1, 8).a("hoeStone");
    public static Item IRON_HOE = (new ItemHoe(36, EnumToolMaterial.IRON)).a(2, 8).a("hoeIron");
    public static Item DIAMOND_HOE = (new ItemHoe(37, EnumToolMaterial.DIAMOND)).a(3, 8).a("hoeDiamond");
    public static Item GOLD_HOE = (new ItemHoe(38, EnumToolMaterial.GOLD)).a(4, 8).a("hoeGold");
    public static Item SEEDS = (new ItemSeeds(39, Block.CROPS.id)).a(9, 0).a("seeds");
    public static Item WHEAT = (new Item(40)).a(9, 1).a("wheat");
    public static Item BREAD = (new ItemFood(41, 5, false)).a(9, 2).a("bread");
    public static Item LEATHER_HELMET = (new ItemArmor(42, 0, 0, 0)).a(0, 0).a("helmetCloth");
    public static Item LEATHER_CHESTPLATE = (new ItemArmor(43, 0, 0, 1)).a(0, 1).a("chestplateCloth");
    public static Item LEATHER_LEGGINGS = (new ItemArmor(44, 0, 0, 2)).a(0, 2).a("leggingsCloth");
    public static Item LEATHER_BOOTS = (new ItemArmor(45, 0, 0, 3)).a(0, 3).a("bootsCloth");
    public static Item CHAINMAIL_HELMET = (new ItemArmor(46, 1, 1, 0)).a(1, 0).a("helmetChain");
    public static Item CHAINMAIL_CHESTPLATE = (new ItemArmor(47, 1, 1, 1)).a(1, 1).a("chestplateChain");
    public static Item CHAINMAIL_LEGGINGS = (new ItemArmor(48, 1, 1, 2)).a(1, 2).a("leggingsChain");
    public static Item CHAINMAIL_BOOTS = (new ItemArmor(49, 1, 1, 3)).a(1, 3).a("bootsChain");
    public static Item IRON_HELMET = (new ItemArmor(50, 2, 2, 0)).a(2, 0).a("helmetIron");
    public static Item IRON_CHESTPLATE = (new ItemArmor(51, 2, 2, 1)).a(2, 1).a("chestplateIron");
    public static Item IRON_LEGGINGS = (new ItemArmor(52, 2, 2, 2)).a(2, 2).a("leggingsIron");
    public static Item IRON_BOOTS = (new ItemArmor(53, 2, 2, 3)).a(2, 3).a("bootsIron");
    public static Item DIAMOND_HELMET = (new ItemArmor(54, 3, 3, 0)).a(3, 0).a("helmetDiamond");
    public static Item DIAMOND_CHESTPLATE = (new ItemArmor(55, 3, 3, 1)).a(3, 1).a("chestplateDiamond");
    public static Item DIAMOND_LEGGINGS = (new ItemArmor(56, 3, 3, 2)).a(3, 2).a("leggingsDiamond");
    public static Item DIAMOND_BOOTS = (new ItemArmor(57, 3, 3, 3)).a(3, 3).a("bootsDiamond");
    public static Item GOLD_HELMET = (new ItemArmor(58, 1, 4, 0)).a(4, 0).a("helmetGold");
    public static Item GOLD_CHESTPLATE = (new ItemArmor(59, 1, 4, 1)).a(4, 1).a("chestplateGold");
    public static Item GOLD_LEGGINGS = (new ItemArmor(60, 1, 4, 2)).a(4, 2).a("leggingsGold");
    public static Item GOLD_BOOTS = (new ItemArmor(61, 1, 4, 3)).a(4, 3).a("bootsGold");
    public static Item FLINT = (new Item(62)).a(6, 0).a("flint");
    public static Item PORK = (new ItemFood(63, 3, true)).a(7, 5).a("porkchopRaw");
    public static Item GRILLED_PORK = (new ItemFood(64, 8, true)).a(8, 5).a("porkchopCooked");
    public static Item PAINTING = (new ItemPainting(65)).a(10, 1).a("painting");
    public static Item GOLDEN_APPLE = (new ItemFood(66, 42, false)).a(11, 0).a("appleGold");
    public final int id;
    protected int maxStackSize = 64;
    private int durability = 32;
    protected int textureId;
    protected boolean bi = false;
    protected boolean bj = false;
    private Item craftingResult = null;
    private String name;

    protected Item(int i) {
        this.id = 256 + i;
        if (byId[256 + i] != null) {
            System.out.println("CONFLICT @ " + i);
        }

        byId[256 + i] = this;
    }

    public Item b(int i) {
        this.textureId = i;
        return this;
    }

    public Item c(int i) {
        this.maxStackSize = i;
        return this;
    }

    public Item a(int i, int j) {
        this.textureId = i + j * 16;
        return this;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        return false;
    }

    public float a(ItemStack itemstack, Block block) {
        return 1.0F;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        return itemstack;
    }

    public int getMaxStackSize() {
        return this.maxStackSize;
    }

    public int filterData(int i) {
        return 0;
    }

    public boolean d() {
        return this.bj;
    }

    protected Item a(boolean flag) {
        this.bj = flag;
        return this;
    }

    public int e() {
        return this.durability;
    }

    protected Item d(int i) {
        this.durability = i;
        return this;
    }

    public boolean f() {
        return this.durability > 0 && !this.bj;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        return false;
    }

    public boolean a(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving) {
        return false;
    }

    public int a(Entity entity) {
        return 1;
    }

    public boolean a(Block block) {
        return false;
    }

    public void a(ItemStack itemstack, EntityLiving entityliving) {}

    public Item g() {
        this.bi = true;
        return this;
    }

    public Item a(String s) {
        this.name = "item." + s;
        return this;
    }

    public String a() {
        return this.name;
    }

    public Item a(Item item) {
        if (this.maxStackSize > 1) {
            throw new IllegalArgumentException("Max stack size must be 1 for items with crafting results");
        } else {
            this.craftingResult = item;
            return this;
        }
    }

    public Item h() {
        return this.craftingResult;
    }

    public boolean i() {
        return this.craftingResult != null;
    }

    public String j() {
        return StatisticCollector.a(this.a() + ".name");
    }

    public void a(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {}

    public void c(ItemStack itemstack, World world, EntityHuman entityhuman) {}

    public boolean b() {
        return false;
    }
}
