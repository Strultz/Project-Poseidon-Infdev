package net.minecraft.server;

import java.util.Random;

public class ChunkProviderGenerate implements IChunkProvider {
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen4;
    private NoiseGeneratorOctaves noiseGen5;
    private NoiseGeneratorOctaves noiseGen6;
    private NoiseGeneratorOctaves noiseGen7;
    private NoiseGeneratorOctaves noiseGen8;
    private World worldObj;
    private double[] noise1;
    private double[] noise2;
    private double[] noise3;
    private double[] noise4;
    private double[] noise5;
    private double[] noise6;

    public ChunkProviderGenerate(World var1, long var2) {
        this.worldObj = var1;
        this.rand = new Random(var2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen7 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen8 = new NoiseGeneratorOctaves(this.rand, 8);
    }
    
    public final Chunk getChunkAt(int var1, int var2) {
        return this.getOrCreateChunk(var1, var2);
    }

    public final Chunk getOrCreateChunk(int var1, int var2) {
        this.rand.setSeed((long)var1 * 341873128712L + (long)var2 * 132897987541L);
        byte[] var3 = new byte['\u8000'];
        Chunk var4 = new Chunk(this.worldObj, var3, var1, var2);
        int var10003 = var1 << 2;
        int var10005 = var2 << 2;
        boolean var56 = true;
        boolean var55 = true;
        boolean var54 = true;
        int var9 = var10005;
        boolean var8 = false;
        int var7 = var10003;
        double[] var6 = this.noise1;
        ChunkProviderGenerate var5 = this;
        if(var6 == null) {
            var6 = new double[425];
        }

        this.noise5 = this.noiseGen6.generateNoiseOctaves(this.noise5, var7, 0, var9, 5, 1, 5, 1.0D, 0.0D, 1.0D);
        this.noise6 = this.noiseGen7.generateNoiseOctaves(this.noise6, var7, 0, var9, 5, 1, 5, 100.0D, 0.0D, 100.0D);
        this.noise2 = this.noiseGen3.generateNoiseOctaves(this.noise2, var7, 0, var9, 5, 17, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
        this.noise3 = this.noiseGen1.generateNoiseOctaves(this.noise3, var7, 0, var9, 5, 17, 5, 684.412D, 684.412D, 684.412D);
        this.noise4 = this.noiseGen2.generateNoiseOctaves(this.noise4, var7, 0, var9, 5, 17, 5, 684.412D, 684.412D, 684.412D);
        var9 = 0;
        int var62 = 0;

        for(int var63 = 0; var63 < 5; ++var63) {
            for(int var64 = 0; var64 < 5; ++var64) {
                double var65;
                if((var65 = (var5.noise5[var62] + 256.0D) / 512.0D) > 1.0D) {
                    var65 = 1.0D;
                }

                double var67;
                if((var67 = var5.noise6[var62] / 8000.0D) < 0.0D) {
                    var67 = -var67;
                }

                if((var67 = var67 * 3.0D - 3.0D) < 0.0D) {
                    if((var67 /= 2.0D) < -1.0D) {
                        var67 = -1.0D;
                    }

                    var67 = (var67 /= 1.4D) / 2.0D;
                    var65 = 0.0D;
                } else {
                    if(var67 > 1.0D) {
                        var67 = 1.0D;
                    }

                    var67 /= 6.0D;
                }

                var65 += 0.5D;
                var67 = var67 * 17.0D / 16.0D;
                double var69 = 8.5D + var67 * 4.0D;
                ++var62;

                for(int var71 = 0; var71 < 17; ++var71) {
                    double var74;
                    if((var74 = ((double)var71 - var69) * 12.0D / var65) < 0.0D) {
                        var74 *= 4.0D;
                    }

                    double var76 = var5.noise3[var9] / 512.0D;
                    double var78 = var5.noise4[var9] / 512.0D;
                    double var72;
                    double var80;
                    if((var80 = (var5.noise2[var9] / 10.0D + 1.0D) / 2.0D) < 0.0D) {
                        var72 = var76;
                    } else if(var80 > 1.0D) {
                        var72 = var78;
                    } else {
                        var72 = var76 + (var78 - var76) * var80;
                    }

                    var72 -= var74;
                    var6[var9] = var72;
                    ++var9;
                }
            }
        }

        this.noise1 = var6;

        int var84;
        int var85;
        for(var84 = 0; var84 < 4; ++var84) {
            for(var85 = 0; var85 < 4; ++var85) {
                for(var7 = 0; var7 < 16; ++var7) {
                    double var87 = this.noise1[(var84 * 5 + var85) * 17 + var7];
                    double var10 = this.noise1[(var84 * 5 + var85 + 1) * 17 + var7];
                    double var12 = this.noise1[((var84 + 1) * 5 + var85) * 17 + var7];
                    double var14 = this.noise1[((var84 + 1) * 5 + var85 + 1) * 17 + var7];
                    double var16 = this.noise1[(var84 * 5 + var85) * 17 + var7 + 1];
                    double var18 = this.noise1[(var84 * 5 + var85 + 1) * 17 + var7 + 1];
                    double var20 = this.noise1[((var84 + 1) * 5 + var85) * 17 + var7 + 1];
                    double var22 = this.noise1[((var84 + 1) * 5 + var85 + 1) * 17 + var7 + 1];

                    for(int var24 = 0; var24 < 8; ++var24) {
                        double var25 = (double)var24 / 8.0D;
                        double var27 = var87 + (var16 - var87) * var25;
                        double var29 = var10 + (var18 - var10) * var25;
                        double var31 = var12 + (var20 - var12) * var25;
                        double var33 = var14 + (var22 - var14) * var25;

                        for(int var94 = 0; var94 < 4; ++var94) {
                            double var36 = (double)var94 / 4.0D;
                            double var38 = var27 + (var31 - var27) * var36;
                            double var40 = var29 + (var33 - var29) * var36;
                            int var26 = var94 + (var84 << 2) << 11 | 0 + (var85 << 2) << 7 | (var7 << 3) + var24;

                            for(int var35 = 0; var35 < 4; ++var35) {
                                double var44 = (double)var35 / 4.0D;
                                double var46 = var38 + (var40 - var38) * var44;
                                int var95 = 0;
                                if((var7 << 3) + var24 < 64) {
                                    var95 = Block.STATIONARY_WATER.id;
                                }

                                if(var46 > 0.0D) {
                                    var95 = Block.STONE.id;
                                }

                                var3[var26] = (byte)var95;
                                var26 += 128;
                            }
                        }
                    }
                }
            }
        }

        for(var84 = 0; var84 < 16; ++var84) {
            for(var85 = 0; var85 < 16; ++var85) {
                double var86 = (double)((var1 << 4) + var84);
                double var89 = (double)((var2 << 4) + var85);
                boolean var13 = this.noiseGen4.func_a(var86 * 0.03125D, var89 * 0.03125D, 0.0D) + this.rand.nextDouble() * 0.2D > 0.0D;
                boolean var90 = this.noiseGen4.func_a(var89 * 0.03125D, 109.0134D, var86 * 0.03125D) + this.rand.nextDouble() * 0.2D > 3.0D;
                int var15 = (int)(this.noiseGen5.generateNoise(var86 * 0.03125D * 2.0D, var89 * 0.03125D * 2.0D) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var91 = var84 << 11 | var85 << 7 | 127;
                int var17 = -1;
                int var92 = Block.GRASS.id;
                int var19 = Block.DIRT.id;

                for(int var93 = 127; var93 >= 0; --var93) {
                    if(var93 <= this.rand.nextInt(6) - 1) {
                        var3[var91] = (byte)Block.BEDROCK.id;
                    } else if(var3[var91] == 0) {
                        var17 = -1;
                    } else if(var3[var91] == Block.STONE.id) {
                        if(var17 == -1) {
                            if(var15 <= 0) {
                                var92 = 0;
                                var19 = (byte)Block.STONE.id;
                            } else if(var93 >= 60 && var93 <= 65) {
                                var92 = Block.GRASS.id;
                                var19 = Block.DIRT.id;
                                if(var90) {
                                    var92 = 0;
                                }

                                if(var90) {
                                    var19 = Block.GRAVEL.id;
                                }

                                if(var13) {
                                    var92 = Block.SAND.id;
                                }

                                if(var13) {
                                    var19 = Block.SAND.id;
                                }
                            }

                            if(var93 < 64 && var92 == 0) {
                                var92 = Block.STATIONARY_WATER.id;
                            }

                            var17 = var15;
                            if(var93 >= 63) {
                                var3[var91] = (byte)var92;
                            } else {
                                var3[var91] = (byte)var19;
                            }
                        } else if(var17 > 0) {
                            --var17;
                            var3[var91] = (byte)var19;
                        }
                    }

                    --var91;
                }
            }
        }

        byte[] var88 = var3;
        var7 = var2;
        var85 = var1;
        var5 = this;
        this.rand.setSeed(this.worldObj.getSeed());
        long var96 = (this.rand.nextLong() / 2L << 1) + 1L;
        long var97 = (this.rand.nextLong() / 2L << 1) + 1L;

        for(var1 -= 8; var1 <= var85 + 8; ++var1) {
            for(var2 = var7 - 8; var2 <= var7 + 8; ++var2) {
                var5.rand.setSeed((long)var1 * var96 + (long)var2 * var97 ^ var5.worldObj.getSeed());
                int var83 = var5.rand.nextInt(var5.rand.nextInt(var5.rand.nextInt(40) + 1) + 1);
                if(var5.rand.nextInt(10) != 0) {
                    var83 = 0;
                }

                for(var9 = 0; var9 < var83; ++var9) {
                    double var98 = (double)((var1 << 4) + var5.rand.nextInt(16));
                    double var99 = (double)var5.rand.nextInt(var5.rand.nextInt(120) + 8);
                    double var66 = (double)((var2 << 4) + var5.rand.nextInt(16));
                    int var68 = 1;
                    if(var5.rand.nextInt(4) == 0) {
                        var5.func_a(var85, var7, var88, var98, var99, var66, 1.0F + var5.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
                        var68 = 1 + var5.rand.nextInt(4);
                    }

                    for(int var100 = 0; var100 < var68; ++var100) {
                        float var70 = var5.rand.nextFloat() * 3.1415927F * 2.0F;
                        float var101 = (var5.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                        float var102 = var5.rand.nextFloat() * 2.0F + var5.rand.nextFloat();
                        var5.func_a(var85, var7, var88, var98, var99, var66, var102, var70, var101, 0, 0, 1.0D);
                    }
                }
            }
        }

        var4.initLighting();
        return var4;
    }

    private void func_a(int var1, int var2, byte[] var3, double var4, double var6, double var8, float var10, float var11, float var12, int var13, int var14, double var15) {
        label204:
        while(true) {
            double var17 = (double)((var1 << 4) + 8);
            double var19 = (double)((var2 << 4) + 8);
            float var21 = 0.0F;
            float var22 = 0.0F;
            Random var23 = new Random(this.rand.nextLong());
            if(var14 <= 0) {
                var14 = 112 - var23.nextInt(28);
            }

            boolean var24 = false;
            if(var13 == -1) {
                var13 = var14 / 2;
                var24 = true;
            }

            int var25 = var23.nextInt(var14 / 2) + var14 / 4;

            for(boolean var26 = var23.nextInt(6) == 0; var13 < var14; ++var13) {
                double var27;
                double var29 = (var27 = 1.5D + (double)(MathHelper.sin((float)var13 * 3.1415927F / (float)var14) * var10)) * var15;
                float var31 = MathHelper.cos(var12);
                float var32 = MathHelper.sin(var12);
                var4 += (double)(MathHelper.cos(var11) * var31);
                var6 += (double)var32;
                var8 += (double)(MathHelper.sin(var11) * var31);
                if(var26) {
                    var12 *= 0.92F;
                } else {
                    var12 *= 0.7F;
                }

                var12 += var22 * 0.1F;
                var11 += var21 * 0.1F;
                var22 *= 0.9F;
                var21 *= 0.75F;
                var22 += (var23.nextFloat() - var23.nextFloat()) * var23.nextFloat() * 2.0F;
                var21 += (var23.nextFloat() - var23.nextFloat()) * var23.nextFloat() * 4.0F;
                if(!var24 && var13 == var25 && var10 > 1.0F) {
                    this.func_a(var1, var2, var3, var4, var6, var8, var23.nextFloat() * 0.5F + 0.5F, var11 - 1.5707964F, var12 / 3.0F, var13, var14, 1.0D);
                    float var10007 = var23.nextFloat() * 0.5F + 0.5F;
                    float var10008 = var11 + 1.5707964F;
                    float var10009 = var12 / 3.0F;
                    var15 = 1.0D;
                    var12 = var10009;
                    var11 = var10008;
                    var10 = var10007;
                    continue label204;
                }

                if(var24 || var23.nextInt(4) != 0) {
                    double var33 = var4 - var17;
                    double var35 = var8 - var19;
                    double var37 = (double)(var14 - var13);
                    double var39 = (double)(var10 + 2.0F + 16.0F);
                    if(var33 * var33 + var35 * var35 - var37 * var37 > var39 * var39) {
                        return;
                    }

                    if(var4 >= var17 - 16.0D - var27 * 2.0D && var8 >= var19 - 16.0D - var27 * 2.0D && var4 <= var17 + 16.0D + var27 * 2.0D && var8 <= var19 + 16.0D + var27 * 2.0D) {
                        int var53 = MathHelper.floor(var4 - var27) - (var1 << 4) - 1;
                        int var34 = MathHelper.floor(var4 + var27) - (var1 << 4) + 1;
                        int var55 = MathHelper.floor(var6 - var29) - 1;
                        int var36 = MathHelper.floor(var6 + var29) + 1;
                        int var56 = MathHelper.floor(var8 - var27) - (var2 << 4) - 1;
                        int var38 = MathHelper.floor(var8 + var27) - (var2 << 4) + 1;
                        if(var53 < 0) {
                            var53 = 0;
                        }

                        if(var34 > 16) {
                            var34 = 16;
                        }

                        if(var55 <= 0) {
                            var55 = 1;
                        }

                        if(var36 > 120) {
                            var36 = 120;
                        }

                        if(var56 < 0) {
                            var56 = 0;
                        }

                        if(var38 > 16) {
                            var38 = 16;
                        }

                        boolean var57 = false;

                        int var40;
                        int var51;
                        for(var40 = var53; !var57 && var40 < var34; ++var40) {
                            for(int var41 = var56; !var57 && var41 < var38; ++var41) {
                                for(int var42 = var36 + 1; !var57 && var42 >= var55 - 1; --var42) {
                                    var51 = ((var40 << 4) + var41 << 7) + var42;
                                    if(var42 >= 0 && var42 < 128) {
                                        if(var3[var51] == Block.WATER.id || var3[var51] == Block.STATIONARY_WATER.id) {
                                            var57 = true;
                                        }

                                        if(var42 != var55 - 1 && var40 != var53 && var40 != var34 - 1 && var41 != var56 && var41 != var38 - 1) {
                                            var42 = var55;
                                        }
                                    }
                                }
                            }
                        }

                        if(!var57) {
                            for(var40 = var53; var40 < var34; ++var40) {
                                double var59 = ((double)(var40 + (var1 << 4)) + 0.5D - var4) / var27;

                                for(var51 = var56; var51 < var38; ++var51) {
                                    double var44 = ((double)(var51 + (var2 << 4)) + 0.5D - var8) / var27;
                                    int var52 = ((var40 << 4) + var51 << 7) + var36;
                                    boolean var54 = false;

                                    for(int var58 = var36 - 1; var58 >= var55; --var58) {
                                        double var49;
                                        if((var49 = ((double)var58 + 0.5D - var6) / var29) > -0.7D && var59 * var59 + var49 * var49 + var44 * var44 < 1.0D) {
                                            byte var43;
                                            if((var43 = var3[var52]) == Block.GRASS.id) {
                                                var54 = true;
                                            }

                                            if(var43 == Block.STONE.id || var43 == Block.DIRT.id || var43 == Block.GRASS.id) {
                                                if(var58 < 10) {
                                                    var3[var52] = (byte)Block.LAVA.id;
                                                } else {
                                                    var3[var52] = 0;
                                                    if(var54 && var3[var52 - 1] == Block.DIRT.id) {
                                                        var3[var52 - 1] = (byte)Block.GRASS.id;
                                                    }
                                                }
                                            }
                                        }

                                        --var52;
                                    }
                                }
                            }

                            if(var24) {
                                break;
                            }
                        }
                    }
                }
            }

            return;
        }
    }

    public final boolean isChunkLoaded(int var1, int var2) {
        return true;
    }

    public final void getChunkAt(IChunkProvider var1, int var2, int var3) {
        this.rand.setSeed((long)var2 * 318279123L + (long)var3 * 919871212L);
        int var8 = var2 << 4;
        var2 = var3 << 4;

        int var4;
        int var5;
        int var6;
        for(var3 = 0; var3 < 20; ++var3) {
            var4 = var8 + this.rand.nextInt(16);
            var5 = this.rand.nextInt(128);
            var6 = var2 + this.rand.nextInt(16);
            (new WorldGenMinable(Block.DIRT.id, 32)).a(this.worldObj, this.rand, var4, var5, var6);
        }

        for(var3 = 0; var3 < 10; ++var3) {
            var4 = var8 + this.rand.nextInt(16);
            var5 = this.rand.nextInt(128);
            var6 = var2 + this.rand.nextInt(16);
            (new WorldGenMinable(Block.GRAVEL.id, 32)).a(this.worldObj, this.rand, var4, var5, var6);
        }

        for(var3 = 0; var3 < 20; ++var3) {
            var4 = var8 + this.rand.nextInt(16);
            var5 = this.rand.nextInt(128);
            var6 = var2 + this.rand.nextInt(16);
            (new WorldGenMinable(Block.COAL_ORE.id, 16)).a(this.worldObj, this.rand, var4, var5, var6);
        }

        for(var3 = 0; var3 < 20; ++var3) {
            var4 = var8 + this.rand.nextInt(16);
            var5 = this.rand.nextInt(64);
            var6 = var2 + this.rand.nextInt(16);
            (new WorldGenMinable(Block.IRON_ORE.id, 8)).a(this.worldObj, this.rand, var4, var5, var6);
        }

        if(this.rand.nextInt(1) == 0) {
            var3 = var8 + this.rand.nextInt(16);
            var4 = this.rand.nextInt(32);
            var5 = var2 + this.rand.nextInt(16);
            (new WorldGenMinable(Block.GOLD_ORE.id, 8)).a(this.worldObj, this.rand, var3, var4, var5);
        }

        if(this.rand.nextInt(4) == 0) {
            var3 = var8 + this.rand.nextInt(16);
            var4 = this.rand.nextInt(16);
            var5 = var2 + this.rand.nextInt(16);
            (new WorldGenMinable(Block.DIAMOND_ORE.id, 8)).a(this.worldObj, this.rand, var3, var4, var5);
        }

        if((var3 = (int)(this.noiseGen8.generateNoise((double)var8 * 0.5D, (double)var2 * 0.5D) / 8.0D + this.rand.nextDouble() * 4.0D + 4.0D)) < 0) {
            var3 = 0;
        }

        WorldGenTree var9 = new WorldGenTree();
        if(this.rand.nextInt(10) == 0) {
            ++var3;
        }

        int var7;
        for(var5 = 0; var5 < var3; ++var5) {
            var6 = var8 + this.rand.nextInt(16) + 8;
            var7 = var2 + this.rand.nextInt(16) + 8;
            var9.a(this.worldObj, this.rand, var6, this.worldObj.getHighestBlockYAt(var6, var7), var7);
        }

        for(var5 = 0; var5 < 2; ++var5) {
            var6 = var8 + this.rand.nextInt(16) + 8;
            var7 = this.rand.nextInt(128);
            var3 = var2 + this.rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.YELLOW_FLOWER.id)).a(this.worldObj, this.rand, var6, var7, var3);
        }

        if(this.rand.nextInt(2) == 0) {
            var5 = var8 + this.rand.nextInt(16) + 8;
            var6 = this.rand.nextInt(128);
            var7 = var2 + this.rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.RED_ROSE.id)).a(this.worldObj, this.rand, var5, var6, var7);
        }

        if(this.rand.nextInt(4) == 0) {
            var5 = var8 + this.rand.nextInt(16) + 8;
            var6 = this.rand.nextInt(128);
            var7 = var2 + this.rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.BROWN_MUSHROOM.id)).a(this.worldObj, this.rand, var5, var6, var7);
        }

        if(this.rand.nextInt(8) == 0) {
            var5 = var8 + this.rand.nextInt(16) + 8;
            var6 = this.rand.nextInt(128);
            var7 = var2 + this.rand.nextInt(16) + 8;
            (new WorldGenFlowers(Block.RED_MUSHROOM.id)).a(this.worldObj, this.rand, var5, var6, var7);
        }

        for(var5 = 0; var5 < 50; ++var5) {
            var6 = var8 + this.rand.nextInt(16) + 8;
            var7 = this.rand.nextInt(this.rand.nextInt(120) + 8);
            var3 = var2 + this.rand.nextInt(16) + 8;
            (new WorldGenSpring(Block.WATER.id)).a(this.worldObj, this.rand, var6, var7, var3);
        }

        for(var5 = 0; var5 < 20; ++var5) {
            var6 = var8 + this.rand.nextInt(16) + 8;
            var7 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(112) + 8) + 8);
            var3 = var2 + this.rand.nextInt(16) + 8;
            (new WorldGenSpring(Block.LAVA.id)).a(this.worldObj, this.rand, var6, var7, var3);
        }

    }

    public final boolean saveChunks(boolean var1, IProgressUpdate var2) {
        return true;
    }

    public final boolean unloadChunks() {
        return false;
    }

    public final boolean canSave() {
        return true;
    }
}
