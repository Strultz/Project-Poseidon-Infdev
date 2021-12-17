package net.minecraft.server;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public final class BlockFluidStill extends BlockFluid {
    protected BlockFluidStill(final int stillId, final Material material) {
        super(stillId, material);
        this.movingId = stillId - 1;
        this.stillId = stillId;
        this.a(false);
    }
    
    @Override
    public final void a(final World world, final int n, final int n2, final int n3, final Random random) {
    }
    
    @Override
    public final void doPhysics(final World world, final int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        if (this.canFlow(world, n, n2 - 1, n3)) {
            n5 = 1;
        }
        if (n5 == 0 && this.canFlow(world, n - 1, n2, n3)) {
            n5 = 1;
        }
        if (n5 == 0 && this.canFlow(world, n + 1, n2, n3)) {
            n5 = 1;
        }
        if (n5 == 0 && this.canFlow(world, n, n2, n3 - 1)) {
            n5 = 1;
        }
        if (n5 == 0 && this.canFlow(world, n, n2, n3 + 1)) {
            n5 = 1;
        }
        if (n4 != 0) {
            final Material blockMaterial = Block.byId[n4].material;
            if ((this.material == Material.WATER && blockMaterial == Material.LAVA) || (blockMaterial == Material.WATER && this.material == Material.LAVA)) {
                world.setTypeId(n, n2, n3, Block.STONE.id);
                return;
            }
        }
        if (Block.FIRE.b(n4)) {
            n5 = 1;
        }
        if (n5 != 0) {
            world.setTypeId(n, n2, n3, this.movingId);
            world.c(n, n2, n3, this.movingId, Block.byId[this.movingId].c());
        }
    }
}