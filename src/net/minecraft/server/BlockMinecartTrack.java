package net.minecraft.server;

import java.util.Random;

public class BlockMinecartTrack extends Block {

    public static final boolean g(World world, int i, int j, int k) {
        int l = world.getTypeId(i, j, k);

        return l == Block.RAILS.id;
    }

    public static final boolean c(int i) {
        return i == Block.RAILS.id;
    }

    protected BlockMinecartTrack(int i, int j) {
        super(i, j, Material.ORIENTABLE);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
        this.a(world, i, j, k);
        return super.a(world, i, j, k, vec3d, vec3d1);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getData(i, j, k);

        if (l >= 2 && l <= 5) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        }
    }

    public int a(int i, int j) {
        if (j >= 6) {
            return this.textureId - 16;
        }

        return this.textureId;
    }

    public boolean b() {
        return false;
    }

    public int a(Random random) {
        return 1;
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return world.p(i, j - 1, k);
    }

    public void c(World world, int i, int j, int k) {
        if (!world.isStatic) {
            this.a(world, i, j, k, true);
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (!world.isStatic) {
            int i1 = world.getData(i, j, k);
            int j1 = i1;

            boolean flag = false;

            if (!world.p(i, j - 1, k)) {
                flag = true;
            }

            if (j1 == 2 && !world.p(i + 1, j, k)) {
                flag = true;
            }

            if (j1 == 3 && !world.p(i - 1, j, k)) {
                flag = true;
            }

            if (j1 == 4 && !world.p(i, j, k - 1)) {
                flag = true;
            }

            if (j1 == 5 && !world.p(i, j, k + 1)) {
                flag = true;
            }

            if (flag) {
                this.g(world, i, j, k, world.getData(i, j, k));
                world.setTypeId(i, j, k, 0);
            }
        }
    }

    private void a(World world, int i, int j, int k, boolean flag) {
        if (!world.isStatic) {
            (new MinecartTrackLogic(this, world, i, j, k)).a(flag);
        }
    }

    public int e() {
        return 0;
    }
}
