package net.minecraft.server;

import java.util.Random;

import org.bukkit.BlockChangeDelegate;

public final class WorldGenTree extends WorldGenerator {
    public final boolean a(World world, Random random, int i, int j, int k) {
         // CraftBukkit start
        // sk: The idea is to have (our) WorldServer implement
        // BlockChangeDelegate and then we can implicitly cast World to
        // WorldServer (a safe cast, AFAIK) and no code will be broken. This
        // then allows plugins to catch manually-invoked generation events
        return this.generate((BlockChangeDelegate) world, random, i, j, k);
    }

    public boolean generate(BlockChangeDelegate var1, Random var2, int var3, int var4, int var5) {
        // CraftBukkit end
        int var6 = var2.nextInt(3) + 4;
        boolean var7 = true;
        if(var4 > 0 && var4 + var6 + 1 <= 128) {
            int var8;
            int var10;
            int var11;
            int var12;
            for(var8 = var4; var8 <= var4 + 1 + var6; ++var8) {
                byte var9 = 1;
                if(var8 == var4) {
                    var9 = 0;
                }

                if(var8 >= var4 + 1 + var6 - 2) {
                    var9 = 2;
                }

                for(var10 = var3 - var9; var10 <= var3 + var9 && var7; ++var10) {
                    for(var11 = var5 - var9; var11 <= var5 + var9 && var7; ++var11) {
                        if(var8 >= 0 && var8 < 128) {
                            if((var12 = var1.getTypeId(var10, var8, var11)) != 0 && var12 != Block.LEAVES.id) {
                                var7 = false;
                            }
                        } else {
                            var7 = false;
                        }
                    }
                }
            }

            if(!var7) {
                return false;
            } else if(((var8 = var1.getTypeId(var3, var4 - 1, var5)) == Block.GRASS.id || var8 == Block.DIRT.id) && var4 < 128 - var6 - 1) {
                var1.setRawTypeId(var3, var4 - 1, var5, Block.DIRT.id);

                int var15;
                for(var15 = var4 - 3 + var6; var15 <= var4 + var6; ++var15) {
                    var10 = var15 - (var4 + var6);
                    var11 = 1 - var10 / 2;

                    for(var12 = var3 - var11; var12 <= var3 + var11; ++var12) {
                        int var14 = var12 - var3;

                        for(var8 = var5 - var11; var8 <= var5 + var11; ++var8) {
                            int var13 = var8 - var5;
                            if((Math.abs(var14) != var11 || Math.abs(var13) != var11 || var2.nextInt(2) != 0 && var10 != 0) && !Block.o[var1.getTypeId(var12, var15, var8)]) {
                                var1.setRawTypeId(var12, var15, var8, Block.LEAVES.id);
                            }
                        }
                    }
                }

                for(var15 = 0; var15 < var6; ++var15) {
                    if((var10 = var1.getTypeId(var3, var4 + var15, var5)) == 0 || var10 == Block.LEAVES.id) {
                        var1.setRawTypeId(var3, var4 + var15, var5, Block.LOG.id);
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
