package net.minecraft.server;

// CraftBukkit start
import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;
// CraftBukkit end

public class ItemSign extends Item {

    public ItemSign(int i) {
        super(i);
        this.maxStackSize = 1;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (l != 1) {
            return false;
        } else if (!world.getMaterial(i, j, k).isBuildable()) {
            return false;
        } else {
            int clickedX = i, clickedY = j, clickedZ = k; // CraftBukkit

            ++j;

            if (!Block.SIGN_POST.canPlace(world, i, j, k)) {
                return false;
            } else if (!world.isEmpty(i, j, k)) {
                return false;
            } else {
                CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j, k); // CraftBukkit

                world.setTypeIdAndData(i, j, k, Block.SIGN_POST.id, MathHelper.floor((double) ((entityhuman.yaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15);
                

                // CraftBukkit start - sign
                BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, clickedX, clickedY, clickedZ, Block.SIGN_POST);

                if (event.isCancelled() || !event.canBuild()) {
                    event.getBlockPlaced().setTypeIdAndData(blockState.getTypeId(), blockState.getRawData(), false);
                    return false;
                }
                // CraftBukkit end

                --itemstack.count;
                TileEntitySign tileentitysign = (TileEntitySign) world.getTileEntity(i, j, k);

                if (tileentitysign != null) {
                    entityhuman.a(tileentitysign);
                }

                return true;
            }
        }
    }
}
