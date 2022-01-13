package net.minecraft.server;

import org.bukkit.event.block.LeavesDecayEvent;

import java.util.Random;

public class BlockLeaves extends BlockLeavesBase {

    private int c;
    int[] a;

    protected BlockLeaves(int i, int j) {
        super(i, j, Material.LEAVES, false);
        this.c = j;
        this.a(true);
    }

    public void a(World var1, int var2, int var3, int var4, Random var5) {
        if(!var1.isStatic) {
            if(!var1.getMaterial(var2, var3 - 1, var4).isSolid()) {
                for(int var8 = var2 - 3; var8 <= var2 + 3; ++var8) {
                    for(int var6 = var3 - 1; var6 <= var3; ++var6) {
                        for(int var7 = var4 - 3; var7 <= var4 + 3; ++var7) {
                            if(var1.getTypeId(var8, var6, var7) == Block.LOG.id) {
                                return;
                            }
                        }
                    }
                }
    
                this.g(var1, var2, var3, var4);
            }
        }
    }

    private void g(World world, int i, int j, int k) {
        // CraftBukkit start
        LeavesDecayEvent event = new LeavesDecayEvent(world.getWorld().getBlockAt(i, j, k));
        world.getServer().getPluginManager().callEvent(event);

        if (event.isCancelled()) return;
        // CraftBukkit end

        this.g(world, i, j, k, world.getData(i, j, k));
        world.setTypeId(i, j, k, 0);
    }

    public int a(Random random) {
        return random.nextInt(10) == 0 ? 1 : 0;
    }

    public int a(int i, Random random) {
        return Block.SAPLING.id;
    }

    public boolean a() {
        return !this.b;
    }

    public int a(int i, int j) {
        return this.textureId;
    }

    public void b(World world, int i, int j, int k, Entity entity) {
        super.b(world, i, j, k, entity);
    }
}
