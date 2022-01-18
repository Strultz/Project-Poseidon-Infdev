package net.minecraft.server;

import java.util.Random;

public abstract class BlockFluids extends Block {

    protected BlockFluids(int i, Material material) {
        super(i, (material == Material.LAVA ? 14 : 12) * 16 + 13, material);
        float f = 0.0F;
        float f1 = 0.0F;

        this.a(0.0F + f1, 0.0F + f, 0.0F + f1, 1.0F + f1, 1.0F + f, 1.0F + f1);
        this.a(true);
    }

    public static float c(int i) {
        if (i >= 8) {
            i = 0;
        }

        float f = (float) (i + 1) / 9.0F;

        return f;
    }

    public int a(int i) {
        return i != 0 && i != 1 ? this.textureId + 1 : this.textureId;
    }

    protected int g(World world, int i, int j, int k) {
        return world.getMaterial(i, j, k) != this.material ? -1 : world.getData(i, j, k);
    }

    protected int b(IBlockAccess iblockaccess, int i, int j, int k) {
        if (iblockaccess.getMaterial(i, j, k) != this.material) {
            return -1;
        } else {
            int l = iblockaccess.getData(i, j, k);

            if (l >= 8) {
                l = 0;
            }

            return l;
        }
    }

    public boolean b() {
        return false;
    }

    public boolean a() {
        return false;
    }

    public boolean a(int i, boolean flag) {
        return flag && i == 0;
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        Material material = iblockaccess.getMaterial(i, j, k);

        return material == this.material ? false : (l == 1 ? true : super.b(iblockaccess, i, j, k, l));
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        return null;
    }

    public int a(int i, Random random) {
        return 0;
    }

    public int a(Random random) {
        return 0;
    }

    private Vec3D c(IBlockAccess var1, int var2, int var3, int var4) {
        Vec3D var5 = Vec3D.create(0.0D, 0.0D, 0.0D);
        int var6 = this.b(var1, var2, var3, var4);

        for(int var7 = 0; var7 < 4; ++var7) {
            int var8 = var2;
            int var9 = var4;
            if(var7 == 0) {
                var8 = var2 - 1;
            }

            if(var7 == 1) {
                var9 = var4 - 1;
            }

            if(var7 == 2) {
                ++var8;
            }

            if(var7 == 3) {
                ++var9;
            }

            int var10;
            if((var10 = this.b(var1, var8, var3, var9)) < 0) {
                if((var10 = this.b(var1, var8, var3 - 1, var9)) >= 0) {
                    var10 -= var6 - 8;
                    var5 = var5.add((double)((var8 - var2) * var10), (double)(var10 * 0), (double)((var9 - var4) * var10));
                }
            } else if(var10 >= 0) {
                var10 -= var6;
                var5 = var5.add((double)((var8 - var2) * var10), (double)(var10 * 0), (double)((var9 - var4) * var10));
            }
        }

        if(var1.getData(var2, var3, var4) >= 8) {
            boolean var11 = false;
            if(this.b(var1, var2, var3, var4 - 1, 2)) {
                var11 = true;
            }

            if(var11 || this.b(var1, var2, var3, var4 + 1, 3)) {
                var11 = true;
            }

            if(var11 || this.b(var1, var2 - 1, var3, var4, 4)) {
                var11 = true;
            }

            if(var11 || this.b(var1, var2 + 1, var3, var4, 5)) {
                var11 = true;
            }

            if(var11) {
                var5 = var5.b().add(0.0D, -6.0D, 0.0D);
            }
        }

        return var5.b();
    }

    public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {
        Vec3D vec3d1 = this.c((IBlockAccess) world, i, j, k);

        vec3d.a += vec3d1.a;
        vec3d.b += vec3d1.b;
        vec3d.c += vec3d1.c;
    }

    public int c() {
        return this.material == Material.WATER ? 5 : (this.material == Material.LAVA ? 30 : 0);
    }

    public void a(World world, int i, int j, int k, Random random) {
        super.a(world, i, j, k, random);
    }

    public void c(World world, int i, int j, int k) {
        this.i(world, i, j, k);
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        this.i(world, i, j, k);
    }

    private void i(World world, int i, int j, int k) {
        if (world.getTypeId(i, j, k) == this.id) {
            if (this.material == Material.LAVA) {
                boolean flag = false;

                if (flag || world.getMaterial(i, j, k - 1) == Material.WATER) {
                    flag = true;
                }

                if (flag || world.getMaterial(i, j, k + 1) == Material.WATER) {
                    flag = true;
                }

                if (flag || world.getMaterial(i - 1, j, k) == Material.WATER) {
                    flag = true;
                }

                if (flag || world.getMaterial(i + 1, j, k) == Material.WATER) {
                    flag = true;
                }

                if (flag || world.getMaterial(i, j + 1, k) == Material.WATER) {
                    flag = true;
                }

                if (flag && !world.isStatic) {
                    int l = world.getData(i, j, k);

                    if (l == 0) {
                        world.setTypeId(i, j, k, Block.OBSIDIAN.id);
                    } else {
                        world.setTypeId(i, j, k, Block.COBBLESTONE.id);
                    }

                    this.h(world, i, j, k);
                }
            }
        }
    }

    protected void h(World world, int i, int j, int k) {
        world.makeSound((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

        for (int l = 0; l < 8; ++l) {
            world.a("largesmoke", (double) i + Math.random(), (double) j + 1.2D, (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
}
