package org.bukkit;

import org.bukkit.material.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An enum of all material ids accepted by the official server + client
 */
public enum Material {
    AIR(0),
    STONE(1),
    GRASS(2),
    DIRT(3),
    COBBLESTONE(4),
    WOOD(5),
    SAPLING(6),
    BEDROCK(7),
    WATER(8, MaterialData.class),
    STATIONARY_WATER(9, MaterialData.class),
    LAVA(10, MaterialData.class),
    STATIONARY_LAVA(11, MaterialData.class),
    SAND(12),
    GRAVEL(13),
    GOLD_ORE(14),
    IRON_ORE(15),
    COAL_ORE(16),
    LOG(17),
    LEAVES(18),
    SPONGE(19),
    GLASS(20),
    RED_WOOL(21),
    ORANGE_WOOL(22),
    YELLOW_WOOL(23),
    LIME_WOOL(24),
    GREEN_WOOL(25),
    AQUA_GREEN_WOOL(26),
    CYAN_WOOL(27),
    BLUE_WOOL(28),
	INDIGO_WOOL(29),
    PURPLE_WOOL(30),
    VIOLET_WOOL(31),
    MAGENTA_WOOL(32),
    PINK_WOOL(33),
    DARK_GRAY_WOOL(34),
    WOOL(35),
    WHITE_WOOL(36),
    YELLOW_FLOWER(37),
    RED_ROSE(38),
    BROWN_MUSHROOM(39),
    RED_MUSHROOM(40),
    GOLD_BLOCK(41),
    IRON_BLOCK(42),
    DOUBLE_STEP(43),
    STEP(44),
    BRICK(45),
    TNT(46),
    BOOKSHELF(47),
    MOSSY_COBBLESTONE(48),
    OBSIDIAN(49),
    TORCH(50, Torch.class),
    FIRE(51),
    WATER_SOURCE(52),
    LAVA_SOURCE(53),
    CHEST(54),
    GEAR(55),
    DIAMOND_ORE(56),
    DIAMOND_BLOCK(57),
    WORKBENCH(58),
    CROPS(59, Crops.class),
    SOIL(60, MaterialData.class),
    FURNACE(61, Furnace.class),
    BURNING_FURNACE(62, Furnace.class),
    // ----- Item Separator -----
    IRON_SPADE(256, 1, 128),
    IRON_PICKAXE(257, 1, 128),
    IRON_AXE(258, 1, 128),
    FLINT_AND_STEEL(259, 1, 64),
    APPLE(260, 1),
    BOW(261, 1),
    ARROW(262),
    COAL(263),
    DIAMOND(264),
    IRON_INGOT(265),
    GOLD_INGOT(266),
    IRON_SWORD(267, 1, 128),
    WOOD_SWORD(268, 1, 32),
    WOOD_SPADE(269, 1, 32),
    WOOD_PICKAXE(270, 1, 32),
    WOOD_AXE(271, 1, 32),
    STONE_SWORD(272, 1, 64),
    STONE_SPADE(273, 1, 64),
    STONE_PICKAXE(274, 1, 64),
    STONE_AXE(275, 1, 64),
    DIAMOND_SWORD(276, 1, 512),
    DIAMOND_SPADE(277, 1, 512),
    DIAMOND_PICKAXE(278, 1, 512),
    DIAMOND_AXE(279, 1, 512),
    STICK(280),
    BOWL(281),
    MUSHROOM_SOUP(282, 1),
    GOLD_SWORD(283, 1, 32),
    GOLD_SPADE(284, 1, 32),
    GOLD_PICKAXE(285, 1, 32),
    GOLD_AXE(286, 1, 32),
    STRING(287),
    FEATHER(288),
    SULPHUR(289),
    WOOD_HOE(290, 1, 32),
    STONE_HOE(291, 1, 64),
    IRON_HOE(292, 1, 128),
    DIAMOND_HOE(293, 1, 512),
    GOLD_HOE(294, 1, 32),
    SEEDS(295),
    WHEAT(296),
    BREAD(297, 1),
    LEATHER_HELMET(298, 1, 33),
    LEATHER_CHESTPLATE(299, 1, 48),
    LEATHER_LEGGINGS(300, 1, 45),
    LEATHER_BOOTS(301, 1, 39),
    CHAINMAIL_HELMET(302, 1, 66),
    CHAINMAIL_CHESTPLATE(303, 1, 96),
    CHAINMAIL_LEGGINGS(304, 1, 90),
    CHAINMAIL_BOOTS(305, 1, 78),
    IRON_HELMET(306, 1, 132),
    IRON_CHESTPLATE(307, 1, 192),
    IRON_LEGGINGS(308, 1, 180),
    IRON_BOOTS(309, 1, 156),
    DIAMOND_HELMET(310, 1, 264),
    DIAMOND_CHESTPLATE(311, 1, 384),
    DIAMOND_LEGGINGS(312, 1, 360),
    DIAMOND_BOOTS(313, 1, 312),
    GOLD_HELMET(314, 1, 66),
    GOLD_CHESTPLATE(315, 1, 96),
    GOLD_LEGGINGS(316, 1, 90),
    GOLD_BOOTS(317, 1, 78),
    FLINT(318),
    PORK(319, 1),
    GRILLED_PORK(320, 1),
    PAINTING(321),
    GOLDEN_APPLE(322, 1);

    private final int id;
    private final Class<? extends MaterialData> data;
    private static final Map<Integer, Material> lookupId = new HashMap<Integer, Material>();
    private static final Map<String, Material> lookupName = new HashMap<String, Material>();
    private final int maxStack;
    private final short durability;

    private Material(final int id) {
        this(id, 64);
    }

    private Material(final int id, final int stack) {
        this(id, stack, null);
    }

    private Material(final int id, final int stack, final int durability) {
        this(id, stack, durability, null);
    }

    private Material(final int id, final Class<? extends MaterialData> data) {
        this(id, 64, data);
    }

    private Material(final int id, final int stack, final Class<? extends MaterialData> data) {
        this(id, stack, -1, data);
    }

    private Material(final int id, final int stack, final int durability, final Class<? extends MaterialData> data) {
        this.id = id;
        this.durability = (short) durability;
        this.maxStack = stack;
        this.data = data;
    }

    /**
     * Gets the item ID or block ID of this Material
     *
     * @return ID of this material
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the maximum amount of this material that can be held in a stack
     *
     * @return Maximum stack size for this material
     */
    public int getMaxStackSize() {
        return maxStack;
    }

    /**
     * Gets the maximum durability of this material
     *
     * @return Maximum durability for this material
     */
    public short getMaxDurability() {
        return durability;
    }

    /**
     * Gets the MaterialData class associated with this Material
     *
     * @return MaterialData associated with this Material
     */
    public Class<? extends MaterialData> getData() {
        return data;
    }

    /**
     * Constructs a new MaterialData relevant for this Material, with the given
     * initial data
     *
     * @param raw Initial data to construct the MaterialData with
     * @return New MaterialData with the given data
     */
    public MaterialData getNewData(final byte raw) {
        if (data == null) {
            return null;
        }

        try {
            Constructor<? extends MaterialData> ctor = data.getConstructor(int.class, byte.class);

            return ctor.newInstance(id, raw);
        } catch (InstantiationException ex) {
            Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Checks if this Material is a placable block
     *
     * @return true if this material is a block
     */
    public boolean isBlock() {
        return id < 256;
    }

    /**
     * Attempts to get the Material with the given ID
     *
     * @param id ID of the material to get
     * @return Material if found, or null
     */
    public static Material getMaterial(final int id) {
        return lookupId.get(id);
    }

    /**
     * Attempts to get the Material with the given name.
     * This is a normal lookup, names must be the precise name they are given
     * in the enum.
     *
     * @param name Name of the material to get
     * @return Material if found, or null
     */
    public static Material getMaterial(final String name) {
        return lookupName.get(name);
    }

    /**
     * Attempts to match the Material with the given name.
     * This is a match lookup; names will be converted to uppercase, then stripped
     * of special characters in an attempt to format it like the enum
     *
     * @param name Name of the material to get
     * @return Material if found, or null
     */
    public static Material matchMaterial(final String name) {
        Material result = null;

        try {
            result = getMaterial(Integer.parseInt(name));
        } catch (NumberFormatException ex) {}

        if (result == null) {
            String filtered = name.toUpperCase();

            filtered = filtered.replaceAll("\\s+", "_").replaceAll("\\W", "");
            result = lookupName.get(filtered);
        }

        return result;
    }

    static {
        for (Material material : values()) {
            lookupId.put(material.getId(), material);
            lookupName.put(material.name(), material);
        }
    }
}
