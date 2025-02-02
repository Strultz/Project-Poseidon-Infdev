package net.minecraft.server;

// CraftBukkit start
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
// CraftBukkit end

public class ItemMinecart extends Item {
    public ItemMinecart(int i) {
        super(i);
        this.maxStackSize = 1;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        int i1 = world.getTypeId(i, j, k);

        if (BlockMinecartTrack.c(i1)) {
            if (!world.isStatic) {
                // CraftBukkit start - Minecarts
                PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(entityhuman, Action.RIGHT_CLICK_BLOCK, i, j, k, l, itemstack);

                if (event.isCancelled()) {
                    return false;
                }
                // CraftBukkit end

                world.addEntity(new EntityMinecart(world, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F)));
            }

            --itemstack.count;
            return true;
        } else {
            return false;
        }
    }
}
