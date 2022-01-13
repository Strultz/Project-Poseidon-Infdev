package net.minecraft.server;

import java.util.Random;

public final class BlockSource extends Block {
    private int liquidInt;
    
    protected BlockSource(final int n, final int liquidInt) {
        super(n, Block.byId[liquidInt].textureId, Material.WATER);
        this.liquidInt = liquidInt;
        this.a(true);
    }
    
    public final void c(final World world, final int n, final int n2, final int n3) {
        super.e(world, n, n2, n3);
        if (world.getTypeId(n - 1, n2, n3) == 0) {
            world.setTypeId(n - 1, n2, n3, this.liquidInt);
        }
        if (world.getTypeId(n + 1, n2, n3) == 0) {
            world.setTypeId(n + 1, n2, n3, this.liquidInt);
        }
        if (world.getTypeId(n, n2, n3 - 1) == 0) {
            world.setTypeId(n, n2, n3 - 1, this.liquidInt);
        }
        if (world.getTypeId(n, n2, n3 + 1) == 0) {
            world.setTypeId(n, n2, n3 + 1, this.liquidInt);
        }
    }
    
    public final void a(final World world, final int n, final int n2, final int n3, final Random random) {
        super.a(world, n, n2, n3, random);
        if (world.getTypeId(n - 1, n2, n3) == 0) {
            world.setTypeId(n - 1, n2, n3, this.liquidInt);
        }
        if (world.getTypeId(n + 1, n2, n3) == 0) {
            world.setTypeId(n + 1, n2, n3, this.liquidInt);
        }
        if (world.getTypeId(n, n2, n3 - 1) == 0) {
            world.setTypeId(n, n2, n3 - 1, this.liquidInt);
        }
        if (world.getTypeId(n, n2, n3 + 1) == 0) {
            world.setTypeId(n, n2, n3 + 1, this.liquidInt);
        }
    }
}
