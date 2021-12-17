package net.minecraft.server;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public abstract class BlockFluid extends Block
{

	protected int stillId;
    protected int movingId;
    
    protected BlockFluid(final int movingId, final Material material) {
        super(movingId, material);
        this.textureId = 14;
        if (material == Material.LAVA) {
            this.textureId = 30;
        }
        Block.isTileEntity[movingId] = false;
        this.movingId = movingId;
        this.stillId = movingId + 1;
        this.a(0.01f, -0.09f, 0.01f, 1.01f, 0.90999997f, 1.01f);
        this.a(true);
        this.b(2.0f);
    }
    
    @Override
    public final int a(final int n) {
        if (this.material == Material.LAVA) {
            return this.textureId;
        }
        if (n == 1) {
            return this.textureId;
        }
        if (n == 0) {
            return this.textureId;
        }
        return this.textureId + 32;
    }
    
    @Override
    public void c(final World world, final int n, final int n2, final int n3) {
        world.c(n, n2, n3, this.movingId, Block.byId[this.movingId].c());
    }
    
    @Override
    public void a(final World world, final int n, final int n2, final int n3, final Random random) {
        this.func_e(world, n, n2, n3, 0);
    }
    
    public boolean func_e(final World world, final int n, int n2, final int n3, int n4) {
        n4 = 0;
        func_h(world, n, n2, n3);
        while (this.canFlow(world, n, --n2, n3)) {
            func_h(world, n, n2, n3);
            final boolean setBlockWithNotify;
            if (setBlockWithNotify = world.setTypeId(n, n2, n3, this.movingId)) {
                n4 = 1;
            }
            if (setBlockWithNotify && this.material != Material.LAVA) {
                continue;
            }
            break;
        }
        ++n2;
        if (this.material == Material.WATER || n4 == 0) {
            n4 = ((n4 = ((n4 = ((n4 |= (this.flow(world, n - 1, n2, n3) ? 1 : 0)) | (this.flow(world, n + 1, n2, n3) ? 1 : 0))) | (this.flow(world, n, n2, n3 - 1) ? 1 : 0))) | (this.flow(world, n, n2, n3 + 1) ? 1 : 0));
        }
        if (this.material == Material.LAVA) {
            n4 = ((n4 = ((n4 = ((n4 |= (extinguishFireLava(world, n - 1, n2, n3) ? 1 : 0)) | (extinguishFireLava(world, n + 1, n2, n3) ? 1 : 0))) | (extinguishFireLava(world, n, n2, n3 - 1) ? 1 : 0))) | (extinguishFireLava(world, n, n2, n3 + 1) ? 1 : 0));
        }
        if (n4 == 0) {
            world.setRawTypeId(n, n2, n3, this.stillId);
        }
        else {
            world.c(n, n2, n3, this.movingId, Block.byId[this.movingId].c());
        }
        return n4 != 0;
    }
    
    private static boolean func_h(final World world, final int n, final int n2, final int n3) {
        return world.getMaterial(n, n2 - 1, n3).liquidSolidCheck();
    }
    
    protected final boolean canFlow(final World world, final int n, final int n2, final int n3) {
        if (!world.getMaterial(n, n2, n3).liquidSolidCheck()) {
            return false;
        }
        if (this.material == Material.WATER) {
            for (int i = n - 2; i <= n + 2; ++i) {
                for (int j = n2 - 2; j <= n2 + 2; ++j) {
                    for (int k = n3 - 2; k <= n3 + 2; ++k) {
                        if (world.getTypeId(i, j, k) == Block.SPONGE.id) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    @Override
    public boolean a(final IBlockAccess world, final int n, final int n2, final int n3, final int n4) {
        final int blockId;
        return (blockId = world.getTypeId(n, n2, n3)) != this.movingId && blockId != this.stillId && ((n4 == 1 && (world.getTypeId(n - 1, n2, n3) == 0 || world.getTypeId(n + 1, n2, n3) == 0 || world.getTypeId(n, n2, n3 - 1) == 0 || world.getTypeId(n, n2, n3 + 1) == 0)) || super.a(world, n, n2, n3, n4));
    }
    
    private static boolean extinguishFireLava(final World world, final int n, final int n2, final int n3) {
        if (Block.FIRE.b(world.getTypeId(n, n2, n3))) {
            Block.FIRE.fireSpread(world, n, n2, n3);
            return true;
        }
        return false;
    }
    
    private boolean flow(final World world, final int n, final int n2, final int n3) {
        if (!this.canFlow(world, n, n2, n3)) {
            return false;
        }
        if (world.setTypeId(n, n2, n3, this.movingId)) {
            world.c(n, n2, n3, this.movingId, Block.byId[this.movingId].c());
        }
        return false;
    }
    
    @Override
    public boolean k_() {
        return false;
    }
    
    @Override
    public boolean a() {
        return false;
    }
    
    @Override
    public void doPhysics(final World world, final int n, final int n2, final int n3, final int n4) {
        if (n4 != 0) {
            final Material blockMaterial = Block.byId[n4].material;
            if ((this.material == Material.WATER && blockMaterial == Material.LAVA) || (blockMaterial == Material.WATER && this.material == Material.LAVA)) {
                world.setTypeId(n, n2, n3, Block.STONE.id);
            }
        }
        world.c(n, n2, n3, this.id, this.c());
    }
    
    @Override
    public int c() {
        if (this.material == Material.LAVA) {
            return 25;
        }
        return 5;
    }
    
    @Override
    public int a(final Random random) {
        return 0;
    }
    
    @Override
    public AxisAlignedBB e(World world, final int n, final int n2, final int n3) {
        return null;
    }
}
