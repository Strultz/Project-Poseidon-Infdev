package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * MaterialData for signs
 */
public class Sign extends MaterialData implements Attachable {
    public Sign() {
        super(Material.SIGN_POST);
    }

    public Sign(final int type) {
        super(type);
    }

    public Sign(final Material type) {
        super(type);
    }

    public Sign(final int type, final byte data) {
        super(type, data);
    }

    public Sign(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * Gets the face that this block is attached on
     *
     * @return BlockFace attached to
     */
    public BlockFace getAttachedFace() {
        return BlockFace.DOWN;
    }

    /**
     * Gets the direction that this sign is currently facing
     *
     * @return BlockFace indicating where this sign is facing
     */
    public BlockFace getFacing() {
        byte data = getData();

        switch (data) {
        case 0x0:
            return BlockFace.WEST;

        case 0x1:
            return BlockFace.WEST_NORTH_WEST;

        case 0x2:
            return BlockFace.NORTH_WEST;

        case 0x3:
            return BlockFace.NORTH_NORTH_WEST;

        case 0x4:
            return BlockFace.NORTH;

        case 0x5:
            return BlockFace.NORTH_NORTH_EAST;

        case 0x6:
            return BlockFace.NORTH_EAST;

        case 0x7:
            return BlockFace.EAST_NORTH_EAST;

        case 0x8:
            return BlockFace.EAST;

        case 0x9:
            return BlockFace.EAST_SOUTH_EAST;

        case 0xA:
            return BlockFace.SOUTH_EAST;

        case 0xB:
            return BlockFace.SOUTH_SOUTH_EAST;

        case 0xC:
            return BlockFace.SOUTH;

        case 0xD:
            return BlockFace.SOUTH_SOUTH_WEST;

        case 0xE:
            return BlockFace.SOUTH_WEST;

        case 0xF:
            return BlockFace.WEST_SOUTH_WEST;
        }

        return null;
    }

    public void setFacingDirection(BlockFace face) {
        byte data;

        switch (face) {
        case WEST:
            data = 0x0;
            break;

        case WEST_NORTH_WEST:
            data = 0x1;
            break;

        case NORTH_WEST:
            data = 0x2;
            break;

        case NORTH_NORTH_WEST:
            data = 0x3;
            break;

        case NORTH:
            data = 0x4;
            break;

        case NORTH_NORTH_EAST:
            data = 0x5;
            break;

        case NORTH_EAST:
            data = 0x6;
            break;

        case EAST_NORTH_EAST:
            data = 0x7;
            break;

        case EAST:
            data = 0x8;
            break;

        case EAST_SOUTH_EAST:
            data = 0x9;
            break;

        case SOUTH_EAST:
            data = 0xA;
            break;

        case SOUTH_SOUTH_EAST:
            data = 0xB;
            break;

        case SOUTH:
            data = 0xC;
            break;

        case SOUTH_SOUTH_WEST:
            data = 0xD;
            break;

        case WEST_SOUTH_WEST:
            data = 0xF;
            break;

        case SOUTH_WEST:
        default:
            data = 0xE;
        }

        setData(data);
    }

    @Override
    public String toString() {
        return super.toString() + " facing " + getFacing();
    }
}
