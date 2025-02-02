package net.minecraft.server;

import org.bukkit.BlockChangeDelegate;

import java.util.Random;

public class BlockSapling extends BlockFlower {

    protected BlockSapling(int i, int j) {
        super(i, j);
        float f = 0.4F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (!world.isStatic) {
            super.a(world, i, j, k, random);
            if (world.getLightLevel(i, j + 1, k) >= 9 && random.nextInt(5) == 0) {
                int l = world.getData(i, j, k);

                if (l < 15) {
                    world.setData(i, j, k, l + 1);
                } else {
                    this.b(world, i, j, k, random);
                }
            }
        }
    }

    public void b(World world, int i, int j, int k, Random random) {
        world.setRawTypeId(i, j, k, 0);

        // CraftBukkit start - fixes client updates on recently grown trees
        BlockChangeWithNotify delegate = new BlockChangeWithNotify(world);

        if (!new WorldGenTree().generate(delegate, random, i, j, k)) {
            world.setRawTypeId(i, j, k, this.id);
        }
        // CraftBukkit end
    }

    // CraftBukkit start
    private class BlockChangeWithNotify implements BlockChangeDelegate {
        World world;

        BlockChangeWithNotify(World world) { this.world = world; }

        public boolean setRawTypeId(int x, int y, int z, int type) {
            return this.world.setTypeId(x, y, z, type);
        }

        public boolean setRawTypeIdAndData(int x, int y, int z, int type, int data) {
            return this.world.setTypeIdAndData(x, y, z, type, data);
        }

        public int getTypeId(int x, int y, int z) {
            return this.world.getTypeId(x, y, z);
        }
    }
    // CraftBukkit end
}
