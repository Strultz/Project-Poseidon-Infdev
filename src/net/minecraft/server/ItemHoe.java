package net.minecraft.server;

// CraftBukkit start
import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;
// CraftBukkit end

public class ItemHoe extends Item {

    public ItemHoe(int i, EnumToolMaterial enumtoolmaterial) {
        super(i);
        this.maxStackSize = 1;
        this.d(enumtoolmaterial.a());
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        int i1 = world.getTypeId(i, j, k);
        int j1 = world.getTypeId(i, j + 1, k);

        if ((l == 0 || j1 != 0 || i1 != Block.GRASS.id) && i1 != Block.DIRT.id) {
            return false;
        } else {
            Block block = Block.SOIL;

            world.makeSound((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), block.stepSound.getName(), (block.stepSound.getVolume1() + 1.0F) / 2.0F, block.stepSound.getVolume2() * 0.8F);
            if (world.isStatic) {
                return true;
            } else {
                CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j, k); // CraftBukkit

                world.setTypeId(i, j, k, block.id);

                // CraftBukkit start - Hoes - blockface -1 for 'SELF'
                BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, i, j, k, block);

                if (event.isCancelled() || !event.canBuild()) {
                    event.getBlockPlaced().setTypeId(blockState.getTypeId());
                    return false;
                }
                // CraftBukkit end
                
                if(world.random.nextInt(8) == 0 && i1 == Block.GRASS.id) {
                    for(int fart9 = 0; fart9 <= 0; ++fart9) {
                        float var11 = world.random.nextFloat() * 0.7F + 0.15F;
                        float var14 = world.random.nextFloat() * 0.7F + 0.15F;
                        EntityItem var12;
                        (var12 = new EntityItem(world, (double)((float)i + var11), (double)((float)j + 1.2F), (double)((float)k + var14), new ItemStack(Item.SEEDS))).pickupDelay = 10;
                        world.addEntity(var12);
                    }
                }

                itemstack.damage(1, entityhuman);
                return true;
            }
        }
    }
}
