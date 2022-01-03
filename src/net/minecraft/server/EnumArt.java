package net.minecraft.server;

public enum EnumArt {

    KEBAB("Kebab", 0, "Kebab", 16, 16, 0, 0), AZTEC("Aztec", 1, "Aztec", 16, 16, 16, 0), ALBAN("Alban", 2, "Alban", 16, 16, 32, 0), AZTEC2("Aztec2", 3, "Aztec2", 16, 16, 48, 0), BOMB("Bomb", 4, "Bomb", 16, 16, 64, 0), PLANT("Plant", 5, "Plant", 16, 16, 80, 0), WASTELAND("Wasteland", 6, "Wasteland", 16, 16, 96, 0), POOL("Pool", 7, "Pool", 32, 16, 0, 32), COURBET("Courbet", 8, "Courbet", 32, 16, 32, 32), SEA("Sea", 9, "Sea", 32, 16, 64, 32), SUNSET("Sunset", 10, "Sunset", 32, 16, 96, 32), WANDERER("Wanderer", 11, "Wanderer", 16, 32, 0, 64), MATCH("Match", 12, "Match", 32, 32, 0, 128), BUST("Bust", 13, "Bust", 32, 32, 32, 128), STAGE("Stage", 14, "Stage", 32, 32, 64, 128), VOID("Void", 15, "Void", 32, 32, 96, 128), SKULL_AND_ROSES("SkullAndRoses", 16, "SkullAndRoses", 32, 32, 128, 128), FIGHTERS("Fighters", 17, "Fighters", 64, 32, 0, 96), POINTER("Pointer", 18, "Pointer", 64, 64, 0, 192);
    public static final int z = "SkullAndRoses".length();
    public final String A;
    public final int B;
    public final int C;
    public final int D;
    public final int E;

    private static final EnumArt[] F = new EnumArt[] { KEBAB, AZTEC, ALBAN, AZTEC2, BOMB, PLANT, WASTELAND, POOL, COURBET, SEA, SUNSET, WANDERER, MATCH, BUST, STAGE, VOID, SKULL_AND_ROSES, FIGHTERS, POINTER};

    private EnumArt(String s, int i, String s1, int j, int k, int l, int i1) {
        this.A = s1;
        this.B = j;
        this.C = k;
        this.D = l;
        this.E = i1;
    }
}
