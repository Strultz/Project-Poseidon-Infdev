package net.minecraft.server;

import org.bukkit.event.block.BlockIgniteEvent;

import java.util.Random;

public class BlockStationary extends BlockFluids {

    protected BlockStationary(int i, Material material) {
        super(i, material);
        this.a(false);
        if (material == Material.LAVA) {
            this.a(true);
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        super.doPhysics(world, i, j, k, l);
        if (world.getTypeId(i, j, k) == this.id) {
            this.i(world, i, j, k);
        }
    }

    private void i(World world, int i, int j, int k) {
        int l = world.getData(i, j, k);

        world.suppressPhysics = true;
        world.setRawTypeIdAndData(i, j, k, this.id - 1, l);
        world.b(i, j, k, i, j, k);
        world.c(i, j, k, this.id - 1, this.c());
        world.suppressPhysics = false;
    }
}
