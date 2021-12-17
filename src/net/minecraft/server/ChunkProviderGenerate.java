package net.minecraft.server;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public final class ChunkProviderGenerate implements IChunkProvider {
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen4;
    private NoiseGeneratorOctaves noiseGen5;
    private NoiseGeneratorOctaves noiseGen6;
    private World worldObj;
    private double[] noise1;
    private double[] noise2;
    private double[] noise3;
    private double[] noise4;
    
    public ChunkProviderGenerate(final World worldObj, final long n) {
        this.worldObj = worldObj;
        this.rand = new Random(n);
        new Random(n);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 4);
        new NoiseGeneratorOctaves(this.rand, 5);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 5);
    }
    
    public final Chunk getChunkAt(final int n, final int n2) {
    	return this.getOrCreateChunk(n, n2);
    }
    
    public final Chunk getOrCreateChunk(final int n, final int n2) {
        this.rand.setSeed(n * 341873128712L + n2 * 132897987541L);
        final byte[] array = new byte[32768];
        final Chunk chunk = new Chunk(this.worldObj, array, n, n2);
        final double[] noise1 = this.noise1;
        final int n3 = n << 2;
        int i = n2 << 2;
        int j = n3;
        double[] noise2 = noise1;
        if (noise2 == null) {
            noise2 = new double[425];
        }
        this.noise2 = this.noiseGen3.generateNoiseOctaves(this.noise2, j, 0, i, 5, 17, 5, 8.555150000000001, 4.277575000000001, 8.555150000000001);
        this.noise3 = this.noiseGen1.generateNoiseOctaves(this.noise3, j, 0, i, 5, 17, 5, 684.412, 684.412, 684.412);
        this.noise4 = this.noiseGen2.generateNoiseOctaves(this.noise4, j, 0, i, 5, 17, 5, 684.412, 684.412, 684.412);
        j = 0;
        for (i = 0; i < 5; ++i) {
            for (int k = 0; k < 5; ++k) {
                for (int l = 0; l < 17; ++l) {
                    double n4;
                    if ((n4 = (l - 8.5) * 12.0) < 0.0) {
                        n4 *= 2.0;
                    }
                    final double n5 = this.noise3[j] / 512.0;
                    final double n6 = this.noise4[j] / 512.0;
                    final double n7;
                    double n8;
                    if ((n7 = (this.noise2[j] / 10.0 + 1.0) / 2.0) < 0.0) {
                        n8 = n5;
                    }
                    else if (n7 > 1.0) {
                        n8 = n6;
                    }
                    else {
                        n8 = n5 + (n6 - n5) * n7;
                    }
                    n8 -= n4;
                    noise2[j] = n8;
                    ++j;
                }
            }
        }
        this.noise1 = noise2;
        for (int n9 = 0; n9 < 4; ++n9) {
            for (int n10 = 0; n10 < 4; ++n10) {
                for (j = 0; j < 16; ++j) {
                    final double n11 = this.noise1[(n9 * 5 + n10) * 17 + j];
                    final double n12 = this.noise1[(n9 * 5 + (n10 + 1)) * 17 + j];
                    final double n13 = this.noise1[((n9 + 1) * 5 + n10) * 17 + j];
                    final double n14 = this.noise1[((n9 + 1) * 5 + (n10 + 1)) * 17 + j];
                    final double n15 = this.noise1[(n9 * 5 + n10) * 17 + (j + 1)];
                    final double n16 = this.noise1[(n9 * 5 + (n10 + 1)) * 17 + (j + 1)];
                    final double n17 = this.noise1[((n9 + 1) * 5 + n10) * 17 + (j + 1)];
                    final double n18 = this.noise1[((n9 + 1) * 5 + (n10 + 1)) * 17 + (j + 1)];
                    for (int n19 = 0; n19 < 8; ++n19) {
                        final double n20 = n19 / 8.0;
                        final double n21 = n11 + (n15 - n11) * n20;
                        final double n22 = n12 + (n16 - n12) * n20;
                        final double n23 = n13 + (n17 - n13) * n20;
                        final double n24 = n14 + (n18 - n14) * n20;
                        for (int n25 = 0; n25 < 4; ++n25) {
                            final double n26 = n25 / 4.0;
                            final double n27 = n21 + (n23 - n21) * n26;
                            final double n28 = n22 + (n24 - n22) * n26;
                            int n29 = n25 + (n9 << 2) << 11 | 0 + (n10 << 2) << 7 | (j << 3) + n19;
                            for (int n30 = 0; n30 < 4; ++n30) {
                                final double n31 = n27 + (n28 - n27) * (n30 / 4.0);
                                int n32 = 0;
                                if ((j << 3) + n19 < 64) {
                                    n32 = Block.STATIONARY_WATER.id;
                                }
                                if (n31 > 0.0) {
                                    n32 = Block.STONE.id;
                                }
                                array[n29] = (byte)n32;
                                n29 += 128;
                            }
                        }
                    }
                }
            }
        }
        for (int n9 = 0; n9 < 16; ++n9) {
            for (int n10 = 0; n10 < 16; ++n10) {
                final double n33 = (n << 4) + n9;
                final double n34 = (n2 << 4) + n10;
                final boolean b = this.noiseGen4.func_a(n33 * 0.03125, n34 * 0.03125, 0.0) + this.rand.nextDouble() * 0.2 > 0.0;
                final boolean b2 = this.noiseGen4.func_a(n34 * 0.03125, 109.0134, n33 * 0.03125) + this.rand.nextDouble() * 0.2 > 3.0;
                final int n35 = (int)(this.noiseGen5.noiseGenerator(n33 * 0.03125 * 2.0, n34 * 0.03125 * 2.0) / 3.0 + 3.0 + this.rand.nextDouble() * 0.25);
                int n36 = n9 << 11 | n10 << 7 | 0x7F;
                int n37 = -1;
                int n38 = Block.GRASS.id;
                int n39 = Block.DIRT.id;
                for (int n40 = 127; n40 >= 0; --n40) {
                    if (array[n36] == 0) {
                        n37 = -1;
                    }
                    else if (array[n36] == Block.STONE.id) {
                        if (n37 == -1) {
                            if (n35 <= 0) {
                                n38 = 0;
                                n39 = (byte)Block.STONE.id;
                            }
                            else if (n40 >= 60 && n40 <= 65) {
                                n38 = Block.GRASS.id;
                                n39 = Block.DIRT.id;
                                if (b2) {
                                    n38 = 0;
                                }
                                if (b2) {
                                    n39 = Block.GRAVEL.id;
                                }
                                if (b) {
                                    n38 = Block.SAND.id;
                                }
                                if (b) {
                                    n39 = Block.SAND.id;
                                }
                            }
                            if (n40 < 64 && n38 == 0) {
                                n38 = Block.STATIONARY_WATER.id;
                            }
                            n37 = n35;
                            if (n40 >= 63) {
                                array[n36] = (byte)n38;
                            }
                            else {
                                array[n36] = (byte)n39;
                            }
                        }
                        else if (n37 > 0) {
                            --n37;
                            array[n36] = (byte)n39;
                        }
                    }
                    --n36;
                }
            }
        }
        chunk.initLighting();
        return chunk;
    }
    
    public final boolean isChunkLoaded(final int n, final int n2) {
        return true;
    }
    
    public final void getChunkAt(final IChunkProvider chunkProvider, int n, int i) {
        this.rand.setSeed(n * 318279123L + i * 919871212L);
        final int n2 = n << 4;
        n = i << 4;
        int n3;
        int j;
        int n4;
        for (i = 0; i < 20; ++i) {
            n3 = n2 + this.rand.nextInt(16);
            j = this.rand.nextInt(128);
            n4 = n + this.rand.nextInt(16);
            new WorldGenMinable(Block.COAL_ORE.id).a(this.worldObj, this.rand, n3, j, n4);
        }
        for (i = 0; i < 10; ++i) {
            n3 = n2 + this.rand.nextInt(16);
            j = this.rand.nextInt(64);
            n4 = n + this.rand.nextInt(16);
            new WorldGenMinable(Block.IRON_ORE.id).a(this.worldObj, this.rand, n3, j, n4);
        }
        if (this.rand.nextInt(2) == 0) {
            i = n2 + this.rand.nextInt(16);
            n3 = this.rand.nextInt(32);
            j = n + this.rand.nextInt(16);
            new WorldGenMinable(Block.GOLD_ORE.id).a(this.worldObj, this.rand, i, n3, j);
        }
        if (this.rand.nextInt(8) == 0) {
            i = n2 + this.rand.nextInt(16);
            n3 = this.rand.nextInt(16);
            j = n + this.rand.nextInt(16);
            new WorldGenMinable(Block.DIAMOND_ORE.id).a(this.worldObj, this.rand, i, n3, j);
        }
        if ((i = (int)(this.noiseGen6.noiseGenerator(n2 * 0.05, n * 0.05) - this.rand.nextDouble())) < 0) {
            i = 0;
        }
        final WorldGenBigTree worldGenBigTree = new WorldGenBigTree();
        if (this.rand.nextInt(100) == 0) {
            ++i;
        }
        for (j = 0; j < i; ++j) {
            n4 = n2 + this.rand.nextInt(16) + 8;
            final int n5 = n + this.rand.nextInt(16) + 8;
            worldGenBigTree.a(1.0, 1.0, 1.0);
            worldGenBigTree.a(this.worldObj, this.rand, n4, this.worldObj.getHighestBlockYAt(n4, n5), n5);
        }
    }
    
    public final boolean saveChunks(final boolean b, IProgressUpdate iprogressupdate) {
    	return true;
    }
    
    public final boolean unloadChunks() {
        return false;
    }
    
    public boolean canSave()
    {
        return true;
    }
}
