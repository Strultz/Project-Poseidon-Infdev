package net.minecraft.server;

import java.util.Random;

public class BlockSign extends BlockContainer {

    private Class a;

    protected BlockSign(int i, Class oclass) {
        super(i, Material.WOOD);
        this.textureId = 4;
        this.a = oclass;
        this.a(0.25F, 0.0F, 0.25F, 0.75F, 1.625F, 0.75F);
    }

    public boolean b() {
        return false;
    }

    public boolean a() {
        return false;
    }

    protected TileEntity a_() {
        try {
            return (TileEntity) this.a.newInstance();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public int a(int i, Random random) {
        return Item.SIGN.id;
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        boolean flag = false;

        if (!world.getMaterial(i, j - 1, k).isBuildable()) {
            flag = true;
        }

        if (flag) {
            this.g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
        }

        super.doPhysics(world, i, j, k, l);
    }
}
