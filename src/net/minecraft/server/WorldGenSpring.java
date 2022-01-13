package net.minecraft.server;

import java.util.Random;

public final class WorldGenSpring extends WorldGenerator {
    private int id;

    public WorldGenSpring(int var1) {
        this.id = var1;
    }

    public final boolean a(World var1, Random var2, int var3, int var4, int var5) {
        if(var1.getTypeId(var3, var4 + 1, var5) != Block.STONE.id) {
            return false;
        } else if(var1.getTypeId(var3, var4 - 1, var5) != Block.STONE.id) {
            return false;
        } else if(var1.getTypeId(var3, var4, var5) != 0 && var1.getTypeId(var3, var4, var5) != Block.STONE.id) {
            return false;
        } else {
            int var7 = 0;
            if(var1.getTypeId(var3 - 1, var4, var5) == Block.STONE.id) {
                ++var7;
            }

            if(var1.getTypeId(var3 + 1, var4, var5) == Block.STONE.id) {
                ++var7;
            }

            if(var1.getTypeId(var3, var4, var5 - 1) == Block.STONE.id) {
                ++var7;
            }

            if(var1.getTypeId(var3, var4, var5 + 1) == Block.STONE.id) {
                ++var7;
            }

            int var6 = 0;
            if(var1.getTypeId(var3 - 1, var4, var5) == 0) {
                ++var6;
            }

            if(var1.getTypeId(var3 + 1, var4, var5) == 0) {
                ++var6;
            }

            if(var1.getTypeId(var3, var4, var5 - 1) == 0) {
                ++var6;
            }

            if(var1.getTypeId(var3, var4, var5 + 1) == 0) {
                ++var6;
            }

            if(var7 == 3 && var6 == 1) {
                var1.setTypeId(var3, var4, var5, this.id);
            }

            return true;
        }
    }
}
