package net.minecraft.server;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public class NoiseGeneratorPerlin extends NoiseGenerator
{

	private int[] d;
    private double a;
    private double b;
    private double c;
    
    public NoiseGeneratorPerlin() {
        this(new Random());
    }
    
    public NoiseGeneratorPerlin(final Random random) {
        this.d = new int[512];
        this.a = random.nextDouble() * 256.0;
        this.b = random.nextDouble() * 256.0;
        this.c = random.nextDouble() * 256.0;
        for (int i = 0; i < 256; ++i) {
            this.d[i] = i;
        }
        for (int i = 0; i < 256; ++i) {
            final int n = random.nextInt(256 - i) + i;
            final int n2 = this.d[i];
            this.d[i] = this.d[n];
            this.d[n] = n2;
            this.d[i + 256] = this.d[i];
        }
    }
    
    private double generateNoise(final double n, final double n2, final double n3) {
        double n4 = n + this.a;
        double n5 = n2 + this.b;
        double n6 = n3 + this.c;
        int n7 = (int)n4;
        int n8 = (int)n5;
        int n9 = (int)n6;
        if (n4 < n7) {
            --n7;
        }
        if (n5 < n8) {
            --n8;
        }
        if (n6 < n9) {
            --n9;
        }
        int n10 = n7 & 0xFF;
        final int n11 = n8 & 0xFF;
        final int n12 = n9 & 0xFF;
        n4 -= n7;
        n5 -= n8;
        n6 -= n9;
        final double n13 = n4 * n4 * n4 * (n4 * (n4 * 6.0 - 15.0) + 10.0);
        final double n14 = n5 * n5 * n5 * (n5 * (n5 * 6.0 - 15.0) + 10.0);
        final double n15 = n6 * n6 * n6 * (n6 * (n6 * 6.0 - 15.0) + 10.0);
        n7 = this.d[n10] + n11;
        n8 = this.d[n7] + n12;
        n7 = this.d[n7 + 1] + n12;
        n9 = this.d[n10 + 1] + n11;
        n10 = this.d[n9] + n12;
        n9 = this.d[n9 + 1] + n12;
        return lerp(n15, lerp(n14, lerp(n13, grad(this.d[n8], n4, n5, n6), grad(this.d[n10], n4 - 1.0, n5, n6)), lerp(n13, grad(this.d[n7], n4, n5 - 1.0, n6), grad(this.d[n9], n4 - 1.0, n5 - 1.0, n6))), lerp(n14, lerp(n13, grad(this.d[n8 + 1], n4, n5, n6 - 1.0), grad(this.d[n10 + 1], n4 - 1.0, n5, n6 - 1.0)), lerp(n13, grad(this.d[n7 + 1], n4, n5 - 1.0, n6 - 1.0), grad(this.d[n9 + 1], n4 - 1.0, n5 - 1.0, n6 - 1.0))));
    }
    
    private static double lerp(final double n, final double n2, final double n3) {
        return n2 + n * (n3 - n2);
    }
    
    private static double grad(int n, final double n2, final double n3, final double n4) {
        final double n5 = ((n &= 0xF) < 8) ? n2 : n3;
        final double n6 = (n < 4) ? n3 : ((n == 12 || n == 14) ? n2 : n4);
        return (((n & 0x1) == 0x0) ? n5 : (-n5)) + (((n & 0x2) == 0x0) ? n6 : (-n6));
    }
    
    public final double func_a(final double n, final double n2) {
        return this.generateNoise(n, n2, 0.0);
    }
    
    public final double func_a(final double n, final double n2, final double n3) {
        return this.generateNoise(n, n2, n3);
    }
    
    public final void populateNoiseArray(final double[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final double n7, final double n8, final double n9, final double n10) {
        int n11 = 0;
        final double n12 = 1.0 / n10;
        int n13 = -1;
        double lerp = 0.0;
        double lerp2 = 0.0;
        double lerp3 = 0.0;
        double lerp4 = 0.0;
        for (int i = 0; i < n4; ++i) {
            double n15;
            int n14 = (int)(n15 = (n + i) * n7 + this.a);
            if (n15 < n14) {
                --n14;
            }
            final int n16 = n14 & 0xFF;
            final double n17 = (n15 -= n14) * n15 * n15 * (n15 * (n15 * 6.0 - 15.0) + 10.0);
            for (int j = 0; j < n6; ++j) {
                double n18;
                n14 = (int)(n18 = (n3 + j) * n9 + this.c);
                if (n18 < n14) {
                    --n14;
                }
                final int n19 = n14 & 0xFF;
                final double n20 = (n18 -= n14) * n18 * n18 * (n18 * (n18 * 6.0 - 15.0) + 10.0);
                for (int k = 0; k < n5; ++k) {
                    double n21;
                    n14 = (int)(n21 = (n2 + k) * n8 + this.b);
                    if (n21 < n14) {
                        --n14;
                    }
                    int n22 = n14 & 0xFF;
                    final double n23 = (n21 -= n14) * n21 * n21 * (n21 * (n21 * 6.0 - 15.0) + 10.0);
                    if (k == 0 || n22 != n13) {
                        n13 = n22;
                        n14 = this.d[n16] + n22;
                        final int n24 = this.d[n14] + n19;
                        n14 = this.d[n14 + 1] + n19;
                        n22 += this.d[n16 + 1];
                        final int n25 = this.d[n22] + n19;
                        n22 = this.d[n22 + 1] + n19;
                        lerp = lerp(n17, grad(this.d[n24], n15, n21, n18), grad(this.d[n25], n15 - 1.0, n21, n18));
                        lerp2 = lerp(n17, grad(this.d[n14], n15, n21 - 1.0, n18), grad(this.d[n22], n15 - 1.0, n21 - 1.0, n18));
                        lerp3 = lerp(n17, grad(this.d[n24 + 1], n15, n21, n18 - 1.0), grad(this.d[n25 + 1], n15 - 1.0, n21, n18 - 1.0));
                        lerp4 = lerp(n17, grad(this.d[n14 + 1], n15, n21 - 1.0, n18 - 1.0), grad(this.d[n22 + 1], n15 - 1.0, n21 - 1.0, n18 - 1.0));
                    }
                    final double lerp5 = lerp(n20, lerp(n23, lerp, lerp2), lerp(n23, lerp3, lerp4));
                    final int n26 = n11++;
                    array[n26] += lerp5 * n12;
                }
            }
        }
    }
}
