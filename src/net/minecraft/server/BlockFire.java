package net.minecraft.server;

import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.material.MaterialData;

import java.util.Random;

// CraftBukkit start
// CraftBukkit end

public class BlockFire extends Block {

    private int[] a = new int[256];
    private int[] b = new int[256];

    protected BlockFire(int i, int j) {
        super(i, j, Material.FIRE);
        this.a(true);
    }

    public void h() {
        this.a(Block.WOOD.id, 5, 20);
        this.a(Block.LOG.id, 5, 5);
        this.a(Block.LEAVES.id, 30, 60);
        this.a(Block.BOOKSHELF.id, 30, 20);
        this.a(Block.TNT.id, 15, 100);
        for (int i1 = 0; i1 < 16; ++i1) {
            this.a(Block.RED_WOOL.id + i1, 30, 60);
        }
    }

    private void a(int i, int j, int k) {
        this.a[i] = j;
        this.b[i] = k;
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int a(Random random) {
        return 0;
    }

    public int c() {
        return 20;
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (!this.canPlace(world, i, j, k)) {
            world.setTypeId(i, j, k, 0);
        }
            int l = world.getData(i, j, k);

            if (l < 15) {
                world.setRawData(i, j, k, l + 1);
            }

            world.c(i, j, k, this.id, this.c());
            if (!this.g(world, i, j, k)) {
                if (!world.p(i, j - 1, k) || l > 3) {
                    world.setTypeId(i, j, k, 0);
                }
            } else if (!this.b(world.getTypeId(i, j - 1, k)) && l == 15 && random.nextInt(4) == 0) {
                world.setTypeId(i, j, k, 0);
            } else {
                this.a(world, i + 1, j, k, 300, random, l);
                this.a(world, i - 1, j, k, 300, random, l);
                this.a(world, i, j - 1, k, 100, random, l);
                this.a(world, i, j + 1, k, 200, random, l);
                this.a(world, i, j, k - 1, 300, random, l);
                this.a(world, i, j, k + 1, 300, random, l);

                // CraftBukkit start - Call to stop spread of fire.
                org.bukkit.Server server = world.getServer();
                org.bukkit.World bworld = world.getWorld();

                IgniteCause igniteCause = BlockIgniteEvent.IgniteCause.SPREAD;
                org.bukkit.block.Block fromBlock = bworld.getBlockAt(i, j, k);
                // CraftBukkit end

                for (int i1 = i - 1; i1 <= i + 1; ++i1) {
                    for (int j1 = k - 1; j1 <= k + 1; ++j1) {
                        for (int k1 = j - 1; k1 <= j + 4; ++k1) {
                            if (i1 != i || k1 != j || j1 != k) {
                                int l1 = 100;

                                if (k1 > j + 1) {
                                    l1 += (k1 - (j + 1)) * 100;
                                }

                                int i2 = this.h(world, i1, k1, j1);

                                if (i2 > 0) {
                                    int j2 = (i2 + 40) / (l + 30);

                                    if (j2 > 0 && random.nextInt(l1) <= j2 && !world.v()) {
                                        int k2 = l + random.nextInt(5) / 4;

                                        if (k2 > 15) {
                                            k2 = 15;
                                        }
                                        // CraftBukkit start - Call to stop spread of fire.
                                        org.bukkit.block.Block block = bworld.getBlockAt(i1, k1, j1);

                                        if (block.getTypeId() != Block.FIRE.id) {
                                            BlockIgniteEvent event = new BlockIgniteEvent(block, igniteCause, null);
                                            server.getPluginManager().callEvent(event);

                                            if (event.isCancelled()) {
                                                continue;
                                            }

                                            org.bukkit.block.BlockState blockState = bworld.getBlockAt(i1, k1, j1).getState();
                                            blockState.setTypeId(this.id);
                                            blockState.setData(new MaterialData(this.id, (byte) k2));

                                            BlockSpreadEvent spreadEvent = new BlockSpreadEvent(blockState.getBlock(), fromBlock, blockState);
                                            server.getPluginManager().callEvent(spreadEvent);

                                            if (!spreadEvent.isCancelled()) {
                                                blockState.update(true);
                                            }
                                        }
                                        // CraftBukkit end
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }


    private void a(World world, int i, int j, int k, int l, Random random, int i1) {
        int j1 = this.b[world.getTypeId(i, j, k)];

        if (random.nextInt(l) < j1) {
            boolean flag = world.getTypeId(i, j, k) == Block.TNT.id;
            // CraftBukkit start
            org.bukkit.block.Block theBlock = world.getWorld().getBlockAt(i, j, k);

            BlockBurnEvent event = new BlockBurnEvent(theBlock);
            world.getServer().getPluginManager().callEvent(event);

            if (event.isCancelled()) {
                return;
            }
            // CraftBukkit end

            if (random.nextInt(2) == 0) {
                world.setTypeId(i, j, k, this.id);
            } else {
                world.setTypeId(i, j, k, 0);
            }

            if (flag) {
                Block.TNT.postBreak(world, i, j, k, 1);
            }
        }
    }

    private boolean g(World world, int i, int j, int k) {
        return this.b(world.getTypeId(i + 1, j, k)) ? true : (this.b(world.getTypeId(i - 1, j, k)) ? true : (this.b(world.getTypeId(i, j - 1, k)) ? true : (this.b(world.getTypeId(i, j + 1, k)) ? true : (this.b(world.getTypeId(i, j, k - 1)) ? true : this.b(world.getTypeId(i, j, k + 1))))));
    }
    
    public final void fireSpread(final World world, final int n, final int n2, final int n3) {
        boolean b = false;
        if (!(b = fireCheck(world, n, n2 + 1, n3))) {
            b = fireCheck(world, n - 1, n2, n3);
        }
        if (!b) {
            b = fireCheck(world, n + 1, n2, n3);
        }
        if (!b) {
            b = fireCheck(world, n, n2, n3 - 1);
        }
        if (!b) {
            b = fireCheck(world, n, n2, n3 + 1);
        }
        if (!b) {
            b = fireCheck(world, n, n2 - 1, n3);
        }
        if (!b) {
            world.setTypeId(n, n2, n3, Block.FIRE.id);
        }
    }

    private int h(World world, int i, int j, int k) {
        byte b0 = 0;

        if (!world.isEmpty(i, j, k)) {
            return 0;
        } else {
            int l = this.f(world, i + 1, j, k, b0);

            l = this.f(world, i - 1, j, k, l);
            l = this.f(world, i, j - 1, k, l);
            l = this.f(world, i, j + 1, k, l);
            l = this.f(world, i, j, k - 1, l);
            l = this.f(world, i, j, k + 1, l);
            return l;
        }
    }

    public boolean k_() {
        return false;
    }

    public boolean b(int i) {
        return this.a[i] > 0;
    }

    public int f(World world, int i, int j, int k, int l) {
        int i1 = this.a[world.getTypeId(i, j, k)];

        return i1 > l ? i1 : l;
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return world.p(i, j - 1, k) || this.g(world, i, j, k);
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (!world.p(i, j - 1, k) && !this.g(world, i, j, k)) {
            world.setTypeId(i, j, k, 0);
        }
    }

    public void c(World world, int i, int j, int k) {
        
            if (!world.p(i, j - 1, k) && !this.g(world, i, j, k)) {
                world.setTypeId(i, j, k, 0);
            } else {
                world.c(i, j, k, this.id, this.c());
            }
    }
    
    private static boolean fireCheck(final World world, final int n, final int n2, final int n3) {
        final int blockId;
        if ((blockId = world.getTypeId(n, n2, n3)) == Block.FIRE.id) {
            return true;
        }
        if (blockId == 0) {
            world.setTypeId(n, n2, n3, Block.FIRE.id);
            return true;
        }
        return false;
    }
}
