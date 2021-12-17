package net.minecraft.server;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public class NoiseGeneratorOctaves extends NoiseGenerator
{

	private NoiseGeneratorPerlin[] a;
    private int b;
    
    public NoiseGeneratorOctaves(final Random random, final int octaves) {
        this.b = octaves;
        this.a = new NoiseGeneratorPerlin[octaves];
        for (int i = 0; i < octaves; ++i) {
            this.a[i] = new NoiseGeneratorPerlin(random);
        }
    }
    
    public final double noiseGenerator(final double n, final double n2) {
        double n3 = 0.0;
        double n4 = 1.0;
        for (int i = 0; i < this.b; ++i) {
            n3 += this.a[i].func_a(n * n4, n2 * n4) / n4;
            n4 /= 2.0;
        }
        return n3;
    }
    
    public final double func_a(final double n, final double n2, final double n3) {
        double n4 = 0.0;
        double n5 = 1.0;
        for (int i = 0; i < this.b; ++i) {
            n4 += this.a[i].func_a(n * n5, n2 * n5, n3 * n5) / n5;
            n5 /= 2.0;
        }
        return n4;
    }
    
    public final double[] generateNoiseOctaves(double[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final double n7, final double n8, final double n9) {
        if (array == null) {
            array = new double[n4 * n5 * n6];
        }
        else {
            for (int i = 0; i < array.length; ++i) {
                array[i] = 0.0;
            }
        }
        double n10 = 1.0;
        for (int j = 0; j < this.b; ++j) {
            this.a[j].populateNoiseArray(array, n, n2, n3, n4, n5, n6, n7 * n10, n8 * n10, n9 * n10, n10);
            n10 /= 2.0;
        }
        return array;
    }
}
