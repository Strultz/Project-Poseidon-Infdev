package net.minecraft.server;

import java.util.Random;

public class BlockDoor extends Block {

    protected BlockDoor(int i) {
        super(i, Material.WOOD);
        this.textureId = 97;

        float f = 0.5F;
        float f1 = 1.0F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    }

    public int a(int i, int j) {
        if (i != 0 && i != 1) {
            int k = this.d(j);

            if ((k == 0 || k == 2) ^ i <= 3) {
                return this.textureId;
            } else {
                int l = k / 2 + (i & 1 ^ k);

                l += (j & 4) / 4;
                int i1 = this.textureId - (j & 8) * 2;

                if ((l & 1) != 0) {
                    i1 = -i1;
                }

                return i1;
            }
        } else {
            return this.textureId;
        }
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        this.a(world, i, j, k);
        return super.e(world, i, j, k);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k) {
        this.c(this.d(iblockaccess.getData(i, j, k)));
    }

    public void c(int i) {
        float f = 0.1875F;

        this.a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
        if (i == 0) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        }

        if (i == 1) {
            this.a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (i == 2) {
            this.a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        }

        if (i == 3) {
            this.a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman) {
        this.interact(world, i, j, k, entityhuman);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
        int l = world.getData(i, j, k);

        if ((l & 8) != 0) {
            if (world.getTypeId(i, j - 1, k) == this.id) {
                this.interact(world, i, j - 1, k, entityhuman);
            }

            return true;
        } else {
            if (world.getTypeId(i, j + 1, k) == this.id) {
                world.setData(i, j + 1, k, (l ^ 4) + 8);
            }

            world.setData(i, j, k, l ^ 4);
            world.b(i, j - 1, k, i, j, k);
            world.a(entityhuman, 1003, i, j, k, 0);
            return true;
        }
    }

    public void setDoor(World world, int i, int j, int k, boolean flag) {
        int l = world.getData(i, j, k);

        if ((l & 8) != 0) {
            if (world.getTypeId(i, j - 1, k) == this.id) {
                this.setDoor(world, i, j - 1, k, flag);
            }
        } else {
            boolean flag1 = (world.getData(i, j, k) & 4) > 0;

            if (flag1 != flag) {
                if (world.getTypeId(i, j + 1, k) == this.id) {
                    world.setData(i, j + 1, k, (l ^ 4) + 8);
                }

                world.setData(i, j, k, l ^ 4);
                world.b(i, j - 1, k, i, j, k);
                world.a((EntityHuman) null, 1003, i, j, k, 0);
            }
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        int i1 = world.getData(i, j, k);

        if ((i1 & 8) != 0) {
            if (world.getTypeId(i, j - 1, k) != this.id) {
                world.setTypeId(i, j, k, 0);
            }
        } else {
            boolean flag = false;

            if (world.getTypeId(i, j + 1, k) != this.id) {
                world.setTypeId(i, j, k, 0);
                flag = true;
            }

            if (!world.p(i, j - 1, k)) {
                world.setTypeId(i, j, k, 0);
                flag = true;
                if (world.getTypeId(i, j + 1, k) == this.id) {
                    world.setTypeId(i, j + 1, k, 0);
                }
            }

            if (flag) {
                if (!world.isStatic) {
                    this.g(world, i, j, k, i1);
                }
            }
        }
    }

    public int a(int i, Random random) {
        return (i & 8) != 0 ? 0 : Item.WOOD_DOOR.id;
    }

    public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
        this.a(world, i, j, k);
        return super.a(world, i, j, k, vec3d, vec3d1);
    }

    public int d(int i) {
        return (i & 4) == 0 ? i - 1 & 3 : i & 3;
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return j >= 127 ? false : world.p(i, j - 1, k) && super.canPlace(world, i, j, k) && super.canPlace(world, i, j + 1, k);
    }

    public static boolean e(int i) {
        return (i & 4) != 0;
    }

    public int e() {
        return 1;
    }
}
