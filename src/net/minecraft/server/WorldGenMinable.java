package net.minecraft.server;

import java.util.Random;

public class WorldGenMinable extends WorldGenerator {

	public WorldGenMinable(int i)
    {
        minableBlockId = i;
    }

    @Override
    public final boolean a(final World world, final Random random, int i, int j, int k) {
        final float n = random.nextFloat() * 3.1415927f;
        final double n2 = i + 8 + MathHelper.sin(n) * 2.0f;
        final double n3 = i + 8 - MathHelper.sin(n) * 2.0f;
        final double n4 = k + 8 + MathHelper.cos(n) * 2.0f;
        final double n5 = k + 8 - MathHelper.cos(n) * 2.0f;
        final double n6 = j + random.nextInt(3) + 2;
        final double n7 = j + random.nextInt(3) + 2;
        double n8;
        double n9;
        double n10;
        double nextDouble;
        double n11;
        double n12;
        int l;
        double n13;
        double n14;
        double n15;
        for (i = 0; i <= 16; ++i) {
            n8 = n2 + (n3 - n2) * i / 16.0;
            n9 = n6 + (n7 - n6) * i / 16.0;
            n10 = n4 + (n5 - n4) * i / 16.0;
            nextDouble = random.nextDouble();
            n11 = (MathHelper.sin(i / 16.0f * 3.1415927f) + 1.0f) * nextDouble + 1.0;
            n12 = (MathHelper.sin(i / 16.0f * 3.1415927f) + 1.0f) * nextDouble + 1.0;
            for (j = (int)(n8 - n11 / 2.0); j <= (int)(n8 + n11 / 2.0); ++j) {
                for (k = (int)(n9 - n12 / 2.0); k <= (int)(n9 + n12 / 2.0); ++k) {
                    for (l = (int)(n10 - n11 / 2.0); l <= (int)(n10 + n11 / 2.0); ++l) {
                        n13 = (j + 0.5 - n8) / (n11 / 2.0);
                        n14 = (k + 0.5 - n9) / (n12 / 2.0);
                        n15 = (l + 0.5 - n10) / (n11 / 2.0);
                        if (n13 * n13 + n14 * n14 + n15 * n15 < 1.0 && world.getTypeId(j, k, l) == Block.STONE.id) {
                            world.setTypeId(j, k, l, this.minableBlockId);
                        }
                    }
                }
            }
        }
        return true;
    }

    private int minableBlockId;
}
