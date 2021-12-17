package net.minecraft.server;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public final class BlockFluidFlowing extends BlockFluid {
    private int stillId1;
    private int movingId1;
    private Random rand;
    private int[] liquidIntArray;
    
    protected BlockFluidFlowing(final int movingId1, final Material material) {
        super(movingId1, material);
        rand = new Random();
        liquidIntArray = new int[]{ 0, 1, 2, 3 };
        this.textureId = 14;
        if (material == Material.LAVA) {
            this.textureId = 30;
        }
        Block.isTileEntity[movingId1] = false;
        this.movingId1 = movingId1;
        this.stillId1 = movingId1 + 1;
        this.a(0.01f, -0.09f, 0.01f, 1.01f, 0.90999997f, 1.01f);
        this.a(true);
    }
    
    @Override
    public final void a(World world, final int n, final int n2, final int n3, final Random random) {
    }
    
    @Override
    public final boolean func_e(final World world, final int n, final int n2, final int n3, final int n4) {
        return false;
    }
    
    @Override
    public final void c(final World world, final int n, final int n2, final int n3) {
        world.c(n, n2, n3, this.movingId1, Block.byId[this.movingId1].c());
    }
    
    @Override
    public final boolean a(final IBlockAccess world, final int n, final int n2, final int n3, final int n4) {
        final int blockId;
        return (blockId = world.getTypeId(n, n2, n3)) != this.movingId1 && blockId != this.stillId1 && ((n4 == 1 && (world.getTypeId(n - 1, n2, n3) == 0 || world.getTypeId(n + 1, n2, n3) == 0 || world.getTypeId(n, n2, n3 - 1) == 0 || world.getTypeId(n, n2, n3 + 1) == 0)) || super.a(world, n, n2, n3, n4));
    }
    
    @Override
    public final boolean k_() {
        return false;
    }
    
    @Override
    public final boolean a() {
        return false;
    }
    
    @Override
    public final void doPhysics(final World world, final int n, final int n2, final int n3, final int n4) {
    }
    
    @Override
    public final int c() {
        if (this.material == Material.LAVA) {
            return 25;
        }
        return 5;
    }
    
    @Override
    public final int a(final Random random) {
        return 0;
    }
    
    @Override
    public AxisAlignedBB e(World world, final int n, final int n2, final int n3) {
        return null;
    }
}
