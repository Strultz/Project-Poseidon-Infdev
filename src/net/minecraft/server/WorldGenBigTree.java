package net.minecraft.server;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

import org.bukkit.BlockChangeDelegate;

public final class WorldGenBigTree extends WorldGenerator {
    private static byte[] otherCoordPairs;
    private Random rand;
    BlockChangeDelegate worldObj; //Craftbukkit
    private int[] basePos;
    private int heightLimit;
    private int height;
    private double heightAttenuation;
    private double branchSlope;
    private double scaleWidth;
    private double leafDensity;
    private int trunkSize;
    private int heightLimitLimit;
    private int leafDistanceLimit;
    private int[][] leafNodes;
    
    public WorldGenBigTree() {
        this.rand = new Random();
        this.basePos = new int[] { 0, 0, 0 };
        this.heightLimit = 0;
        this.heightAttenuation = 0.618;
        this.branchSlope = 0.381;
        this.scaleWidth = 1.0;
        this.leafDensity = 1.0;
        this.trunkSize = 1;
        this.heightLimitLimit = 12;
        this.leafDistanceLimit = 4;
    }
    
    private void placeBlockLine(final int[] array, final int[] array2, int n) {
        final int[] array3 = { 0, 0, 0 };
        int i = 0;
        int n2 = 0;
        while (i < 3) {
            array3[i] = array2[i] - array[i];
            if (Math.abs(array3[i]) > Math.abs(array3[n2])) {
                n2 = i;
            }
            i = (byte)(i + 1);
        }
        if (array3[n2] == 0) {
            return;
        }
        final byte b = WorldGenBigTree.otherCoordPairs[n2];
        final byte b2 = WorldGenBigTree.otherCoordPairs[n2 + 3];
        int n3;
        if (array3[n2] > 0) {
            n3 = 1;
        }
        else {
            n3 = -1;
        }
        final double n4 = array3[b] / (double)array3[n2];
        final double n5 = array3[b2] / (double)array3[n2];
        final int[] array4 = { 0, 0, 0 };
        int j;
        for (j = 0, n = array3[n2] + n3; j != n; j += n3) {
            array4[n2] = MathHelper.floor(array[n2] + j + 0.5);
            array4[b] = MathHelper.floor(array[b] + j * n4 + 0.5);
            array4[b2] = MathHelper.floor(array[b2] + j * n5 + 0.5);
            this.worldObj.setRawTypeId(array4[0], array4[1], array4[2], 17);
        }
    }
    
    private int checkBlockLine(final int[] array, final int[] array2) {
        final int[] array3 = { 0, 0, 0 };
        int i = 0;
        int n = 0;
        while (i < 3) {
            array3[i] = array2[i] - array[i];
            if (Math.abs(array3[i]) > Math.abs(array3[n])) {
                n = i;
            }
            i = (byte)(i + 1);
        }
        if (array3[n] == 0) {
            return -1;
        }
        final byte b = WorldGenBigTree.otherCoordPairs[n];
        final byte b2 = WorldGenBigTree.otherCoordPairs[n + 3];
        int n2;
        if (array3[n] > 0) {
            n2 = 1;
        }
        else {
            n2 = -1;
        }
        final double n3 = array3[b] / (double)array3[n];
        final double n4 = array3[b2] / (double)array3[n];
        final int[] array4 = { 0, 0, 0 };
        int j;
        int n5;
        for (j = 0, n5 = array3[n] + n2; j != n5; j += n2) {
            array4[n] = array[n] + j;
            array4[b] = (int)(array[b] + j * n3);
            array4[b2] = (int)(array[b2] + j * n4);
            final int blockId;
            if ((blockId = this.worldObj.getTypeId(array4[0], array4[1], array4[2])) != 0 && blockId != 18) {
                break;
            }
        }
        if (j == n5) {
            return -1;
        }
        return Math.abs(j);
    }
    
    @Override
    public final void a(final double n, final double n2, final double n3) {
        this.heightLimitLimit = 12;
        this.leafDistanceLimit = 5;
        this.scaleWidth = 1.0;
        this.leafDensity = 1.0;
    }
    
    @Override
    public boolean a(World world, Random random, int i, int j, int k) {
        // CraftBukkit start
        // sk: The idea is to have (our) WorldServer implement
        // BlockChangeDelegate and then we can implicitly cast World to
        // WorldServer (a safe cast, AFAIK) and no code will be broken. This
        // then allows plugins to catch manually-invoked generation events
        return generate((BlockChangeDelegate)world, random, i, j, k);
    }

    public boolean generate(BlockChangeDelegate worldObj, Random random, int n, int blockId, int i) {
        // CraftBukkit end
        this.worldObj = worldObj;
        this.rand.setSeed(random.nextLong());
        this.basePos[0] = n;
        this.basePos[1] = blockId;
        this.basePos[2] = i;
        if (this.heightLimit == 0) {
            this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
        }
        final int[] array = { this.basePos[0], this.basePos[1], this.basePos[2] };
        final int[] array2 = { this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2] };
        boolean b;
        if ((blockId = this.worldObj.getTypeId(this.basePos[0], this.basePos[1] - 1, this.basePos[2])) != 2 && blockId != 3) {
            b = false;
        }
        else if ((i = this.checkBlockLine(array, array2)) == -1) {
            b = true;
        }
        else if (i < 6) {
            b = false;
        }
        else {
            this.heightLimit = i;
            b = true;
        }
        if (!b) {
            return false;
        }
        this.height = (int)(this.heightLimit * this.heightAttenuation);
        if (this.height >= this.heightLimit) {
            this.height = this.heightLimit - 1;
        }
        int j;
        if ((j = (int)(1.382 + Math.pow(this.leafDensity * this.heightLimit / 13.0, 2.0))) <= 0) {
            j = 1;
        }
        final int[][] array3 = new int[j * this.heightLimit][4];
        blockId = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
        i = 1;
        int n2 = this.basePos[1] + this.height;
        int k = blockId - this.basePos[1];
        array3[0][0] = this.basePos[0];
        array3[0][1] = blockId;
        array3[0][2] = this.basePos[2];
        array3[0][3] = n2;
        --blockId;
        while (k >= 0) {
            int l = 0;
            int n3 = k;
            float n5;
            float n4;
            if (n3 < (float)this.heightLimit * 0.3) {
                n4 = (n5 = -1.618f);
            }
            else {
                final float n6 = this.heightLimit / 2.0f;
                final float n7;
                float n8;
                if ((n7 = this.heightLimit / 2.0f - n3) == 0.0f) {
                    n8 = n6;
                }
                else if (Math.abs(n7) >= n6) {
                    n8 = 0.0f;
                }
                else {
                    n8 = (float)Math.sqrt(Math.pow((double)Math.abs(n6), 2.0) - Math.pow((double)Math.abs(n7), 2.0));
                }
                n5 = (n8 = (n4 = n8 * 0.5f));
            }
            final float n9 = n5;
            if (n4 < 0.0f) {
                --blockId;
                --k;
            }
            else {
                while (l < j) {
                    final double n10 = this.scaleWidth * (n9 * (this.rand.nextFloat() + 0.328));
                    final double n11 = this.rand.nextFloat() * 2.0 * 3.14159;
                    final int n12 = (int)(n10 * Math.sin(n11) + this.basePos[0] + 0.5);
                    n3 = (int)(n10 * Math.cos(n11) + this.basePos[2] + 0.5);
                    final int[] array4 = { n12, blockId, n3 };
                    if (this.checkBlockLine(array4, new int[] { n12, blockId + this.leafDistanceLimit, n3 }) == -1) {
                        final int[] array5 = { this.basePos[0], this.basePos[1], this.basePos[2] };
                        final double n13 = Math.sqrt(Math.pow((double)Math.abs(this.basePos[0] - array4[0]), 2.0) + Math.pow((double)Math.abs(this.basePos[2] - array4[2]), 2.0)) * this.branchSlope;
                        if (array4[1] - n13 > n2) {
                            array5[1] = n2;
                        }
                        else {
                            array5[1] = (int)(array4[1] - n13);
                        }
                        if (this.checkBlockLine(array5, array4) == -1) {
                            array3[i][0] = n12;
                            array3[i][1] = blockId;
                            array3[i][2] = n3;
                            array3[i][3] = array5[1];
                            ++i;
                        }
                    }
                    ++l;
                }
                --blockId;
                --k;
            }
        }
        System.arraycopy(array3, 0, (this.leafNodes = new int[i][4]), 0, i);
        int l;
        int n3;
        int n12;
        int n14;
        int n15;
        int n16;
        int n17;
        float n18;
        int n19;
        int n20;
        int n21;
        float n22;
        int n23;
        byte b2;
        byte b3;
        int[] array6;
        int[] array7;
        int n24;
        int blockId2;
        for (j = 0, n = this.leafNodes.length; j < n; ++j) {
            blockId = this.leafNodes[j][0];
            i = this.leafNodes[j][1];
            n2 = this.leafNodes[j][2];
            n14 = blockId;
            n15 = i;
            blockId = n2;
            n16 = n15;
            l = n14;
            for (i = n16; i < n16 + this.leafDistanceLimit; ++i) {
                n17 = i - n16;
                n18 = ((n17 < 0 || n17 >= this.leafDistanceLimit) ? -1.0f : ((n17 == 0 || n17 == this.leafDistanceLimit - 1) ? 2.0f : 3.0f));
                n19 = l;
                n20 = i;
                n21 = blockId;
                n22 = n18;
                n3 = n21;
                n12 = n20;
                n17 = n19;
                n23 = (int)(n22 + 0.618);
                b2 = WorldGenBigTree.otherCoordPairs[1];
                b3 = WorldGenBigTree.otherCoordPairs[4];
                array6 = new int[] { n17, n12, n3 };
                array7 = new int[] { 0, 0, 0 };
                n3 = -n23;
                array7[1] = array6[1];
                while (n3 <= n23) {
                    array7[b2] = array6[b2] + n3;
                    for (n24 = -n23; n24 <= n23; ++n24) {
                        if (Math.sqrt(Math.pow(Math.abs(n3) + 0.5, 2.0) + Math.pow(Math.abs(n24) + 0.5, 2.0)) <= n22) {
                            array7[b3] = array6[b3] + n24;
                            if ((blockId2 = this.worldObj.getTypeId(array7[0], array7[1], array7[2])) == 0 || blockId2 == 18) {
                                this.worldObj.setRawTypeId(array7[0], array7[1], array7[2], 18);
                            }
                        }
                    }
                    ++n3;
                }
            }
        }
        j = this.basePos[0];
        n = this.basePos[1];
        blockId = this.basePos[1] + this.height;
        i = this.basePos[2];
        final int[] array8 = { j, n, i };
        final int[] array9 = { j, blockId, i };
        this.placeBlockLine(array8, array9, 17);
        if (this.trunkSize == 2) {
            final int[] array10 = array8;
            final int n25 = 0;
            ++array10[n25];
            final int[] array11 = array9;
            final int n26 = 0;
            ++array11[n26];
            this.placeBlockLine(array8, array9, 17);
            final int[] array12 = array8;
            final int n27 = 2;
            ++array12[n27];
            final int[] array13 = array9;
            final int n28 = 2;
            ++array13[n28];
            this.placeBlockLine(array8, array9, 17);
            final int[] array14 = array8;
            final int n29 = 0;
            --array14[n29];
            final int[] array15 = array9;
            final int n30 = 0;
            --array15[n30];
            this.placeBlockLine(array8, array9, 17);
        }
        j = 0;
        n = this.leafNodes.length;
        final int[] array16 = { this.basePos[0], this.basePos[1], this.basePos[2] };
        while (j < n) {
            final int[] array17 = this.leafNodes[j];
            final int[] array18 = { array17[0], array17[1], array17[2] };
            array16[1] = array17[3];
            k = (n16 = array16[1] - this.basePos[1]);
            if (n16 >= this.heightLimit * 0.2) {
                this.placeBlockLine(array16, array18, 17);
            }
            ++j;
        }
        return true;
    }
    
    static {
        WorldGenBigTree.otherCoordPairs = new byte[] { 2, 0, 0, 1, 2, 1 };
    }
}
/*package net.minecraft.server;

import java.util.Random;

// CraftBukkit start
import org.bukkit.BlockChangeDelegate;
// CraftBukkit end

public class WorldGenBigTree extends WorldGenerator {

    static final byte[] a = new byte[] { (byte) 2, (byte) 0, (byte) 0, (byte) 1, (byte) 2, (byte) 1};
    Random b = new Random();
    // CraftBukkit start
    BlockChangeDelegate c;
    // CraftBukkit end
    int[] d = new int[] { 0, 0, 0};
    int e = 0;
    int f;
    double g = 0.618D;
    double h = 1.0D;
    double i = 0.381D;
    double j = 1.0D;
    double k = 1.0D;
    int l = 1;
    int m = 12;
    int n = 4;
    int[][] o;

    public WorldGenBigTree() {}

    void a() {
        this.f = (int) ((double) this.e * this.g);
        if (this.f >= this.e) {
            this.f = this.e - 1;
        }

        int i = (int) (1.382D + Math.pow(this.k * (double) this.e / 13.0D, 2.0D));

        if (i < 1) {
            i = 1;
        }

        int[][] aint = new int[i * this.e][4];
        int j = this.d[1] + this.e - this.n;
        int k = 1;
        int l = this.d[1] + this.f;
        int i1 = j - this.d[1];

        aint[0][0] = this.d[0];
        aint[0][1] = j;
        aint[0][2] = this.d[2];
        aint[0][3] = l;
        --j;

        while (i1 >= 0) {
            int j1 = 0;
            float f = this.a(i1);

            if (f < 0.0F) {
                --j;
                --i1;
            } else {
                for (double d0 = 0.5D; j1 < i; ++j1) {
                    double d1 = this.j * (double) f * ((double) this.b.nextFloat() + 0.328D);
                    double d2 = (double) this.b.nextFloat() * 2.0D * 3.14159D;
                    int k1 = (int) (d1 * Math.sin(d2) + (double) this.d[0] + d0);
                    int l1 = (int) (d1 * Math.cos(d2) + (double) this.d[2] + d0);
                    int[] aint1 = new int[] { k1, j, l1};
                    int[] aint2 = new int[] { k1, j + this.n, l1};

                    if (this.a(aint1, aint2) == -1) {
                        int[] aint3 = new int[] { this.d[0], this.d[1], this.d[2]};
                        double d3 = Math.sqrt(Math.pow((double) Math.abs(this.d[0] - aint1[0]), 2.0D) + Math.pow((double) Math.abs(this.d[2] - aint1[2]), 2.0D));
                        double d4 = d3 * this.i;

                        if ((double) aint1[1] - d4 > (double) l) {
                            aint3[1] = l;
                        } else {
                            aint3[1] = (int) ((double) aint1[1] - d4);
                        }

                        if (this.a(aint3, aint1) == -1) {
                            aint[k][0] = k1;
                            aint[k][1] = j;
                            aint[k][2] = l1;
                            aint[k][3] = aint3[1];
                            ++k;
                        }
                    }
                }

                --j;
                --i1;
            }
        }

        this.o = new int[k][4];
        System.arraycopy(aint, 0, this.o, 0, k);
    }

    void a(int i, int j, int k, float f, byte b0, int l) {
        int i1 = (int) ((double) f + 0.618D);
        byte b1 = a[b0];
        byte b2 = a[b0 + 3];
        int[] aint = new int[] { i, j, k};
        int[] aint1 = new int[] { 0, 0, 0};
        int j1 = -i1;
        int k1 = -i1;

        for (aint1[b0] = aint[b0]; j1 <= i1; ++j1) {
            aint1[b1] = aint[b1] + j1;
            k1 = -i1;

            while (k1 <= i1) {
                double d0 = Math.sqrt(Math.pow((double) Math.abs(j1) + 0.5D, 2.0D) + Math.pow((double) Math.abs(k1) + 0.5D, 2.0D));

                if (d0 > (double) f) {
                    ++k1;
                } else {
                    aint1[b2] = aint[b2] + k1;
                    int l1 = this.c.getTypeId(aint1[0], aint1[1], aint1[2]);

                    if (l1 != 0 && l1 != 18) {
                        ++k1;
                    } else {
                        this.c.setTypeId(aint1[0], aint1[1], aint1[2], l);
                        ++k1;
                    }
                }
            }
        }
    }

    float a(int i) {
        if ((double) i < (double) ((float) this.e) * 0.3D) {
            return -1.618F;
        } else {
            float f = (float) this.e / 2.0F;
            float f1 = (float) this.e / 2.0F - (float) i;
            float f2;

            if (f1 == 0.0F) {
                f2 = f;
            } else if (Math.abs(f1) >= f) {
                f2 = 0.0F;
            } else {
                f2 = (float) Math.sqrt(Math.pow((double) Math.abs(f), 2.0D) - Math.pow((double) Math.abs(f1), 2.0D));
            }

            f2 *= 0.5F;
            return f2;
        }
    }

    float b(int i) {
        return i >= 0 && i < this.n ? (i != 0 && i != this.n - 1 ? 3.0F : 2.0F) : -1.0F;
    }

    void a(int i, int j, int k) {
        int l = j;

        for (int i1 = j + this.n; l < i1; ++l) {
            float f = this.b(l - j);

            this.a(i, l, k, f, (byte) 1, 18);
        }
    }

    void a(int[] aint, int[] aint1, int i) {
        int[] aint2 = new int[] { 0, 0, 0};
        byte b0 = 0;

        byte b1;

        for (b1 = 0; b0 < 3; ++b0) {
            aint2[b0] = aint1[b0] - aint[b0];
            if (Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
                b1 = b0;
            }
        }

        if (aint2[b1] != 0) {
            byte b2 = a[b1];
            byte b3 = a[b1 + 3];
            byte b4;

            if (aint2[b1] > 0) {
                b4 = 1;
            } else {
                b4 = -1;
            }

            double d0 = (double) aint2[b2] / (double) aint2[b1];
            double d1 = (double) aint2[b3] / (double) aint2[b1];
            int[] aint3 = new int[] { 0, 0, 0};
            int j = 0;

            for (int k = aint2[b1] + b4; j != k; j += b4) {
                aint3[b1] = MathHelper.b((double) (aint[b1] + j) + 0.5D);
                aint3[b2] = MathHelper.b((double) aint[b2] + (double) j * d0 + 0.5D);
                aint3[b3] = MathHelper.b((double) aint[b3] + (double) j * d1 + 0.5D);
                this.c.setTypeId(aint3[0], aint3[1], aint3[2], i);
            }
        }
    }

    void b() {
        int i = 0;

        for (int j = this.o.length; i < j; ++i) {
            int k = this.o[i][0];
            int l = this.o[i][1];
            int i1 = this.o[i][2];

            this.a(k, l, i1);
        }
    }

    boolean c(int i) {
        return (double) i >= (double) this.e * 0.2D;
    }

    void c() {
        int i = this.d[0];
        int j = this.d[1];
        int k = this.d[1] + this.f;
        int l = this.d[2];
        int[] aint = new int[] { i, j, l};
        int[] aint1 = new int[] { i, k, l};

        this.a(aint, aint1, 17);
        if (this.l == 2) {
            ++aint[0];
            ++aint1[0];
            this.a(aint, aint1, 17);
            ++aint[2];
            ++aint1[2];
            this.a(aint, aint1, 17);
            aint[0] += -1;
            aint1[0] += -1;
            this.a(aint, aint1, 17);
        }
    }

    void d() {
        int i = 0;
        int j = this.o.length;

        for (int[] aint = new int[] { this.d[0], this.d[1], this.d[2]}; i < j; ++i) {
            int[] aint1 = this.o[i];
            int[] aint2 = new int[] { aint1[0], aint1[1], aint1[2]};

            aint[1] = aint1[3];
            int k = aint[1] - this.d[1];

            if (this.c(k)) {
                this.a(aint, aint2, 17);
            }
        }
    }

    int a(int[] aint, int[] aint1) {
        int[] aint2 = new int[] { 0, 0, 0};
        byte b0 = 0;

        byte b1;

        for (b1 = 0; b0 < 3; ++b0) {
            aint2[b0] = aint1[b0] - aint[b0];
            if (Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
                b1 = b0;
            }
        }

        if (aint2[b1] == 0) {
            return -1;
        } else {
            byte b2 = a[b1];
            byte b3 = a[b1 + 3];
            byte b4;

            if (aint2[b1] > 0) {
                b4 = 1;
            } else {
                b4 = -1;
            }

            double d0 = (double) aint2[b2] / (double) aint2[b1];
            double d1 = (double) aint2[b3] / (double) aint2[b1];
            int[] aint3 = new int[] { 0, 0, 0};
            int i = 0;

            int j;

            for (j = aint2[b1] + b4; i != j; i += b4) {
                aint3[b1] = aint[b1] + i;
                aint3[b2] = (int) ((double) aint[b2] + (double) i * d0);
                aint3[b3] = (int) ((double) aint[b3] + (double) i * d1);
                int k = this.c.getTypeId(aint3[0], aint3[1], aint3[2]);

                if (k != 0 && k != 18) {
                    break;
                }
            }

            return i == j ? -1 : Math.abs(i);
        }
    }

    boolean e() {
        int[] aint = new int[] { this.d[0], this.d[1], this.d[2]};
        int[] aint1 = new int[] { this.d[0], this.d[1] + this.e - 1, this.d[2]};
        int i = this.c.getTypeId(this.d[0], this.d[1] - 1, this.d[2]);

        if (i != 2 && i != 3) {
            return false;
        } else {
            int j = this.a(aint, aint1);

            if (j == -1) {
                return true;
            } else if (j < 6) {
                return false;
            } else {
                this.e = j;
                return true;
            }
        }
    }

    public void a(double d0, double d1, double d2) {
        this.m = (int) (d0 * 12.0D);
        if (d0 > 0.5D) {
            this.n = 5;
        }

        this.j = d1;
        this.k = d2;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        // CraftBukkit start
        // sk: The idea is to have (our) WorldServer implement
        // BlockChangeDelegate and then we can implicitly cast World to
        // WorldServer (a safe cast, AFAIK) and no code will be broken. This
        // then allows plugins to catch manually-invoked generation events
        return generate((BlockChangeDelegate)world, random, i, j, k);
    }

    public boolean generate(BlockChangeDelegate world, Random random, int i, int j, int k) {
        // CraftBukkit end
        this.c = world;
        long l = random.nextLong();

        this.b.setSeed(l);
        this.d[0] = i;
        this.d[1] = j;
        this.d[2] = k;
        if (this.e == 0) {
            this.e = 5 + this.b.nextInt(this.m);
        }

        if (!this.e()) {
            return false;
        } else {
            this.a();
            this.b();
            this.c();
            this.d();
            return true;
        }
    }
}*/
