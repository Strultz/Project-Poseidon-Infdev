package net.minecraft.server;

public enum EnumCreatureType {

    MONSTER("monster", 0, IMonster.class, 70, false), CREATURE("creature", 1, EntityAnimal.class, 15, true);
    private final Class d;
    private final int e;
    private final boolean g;

    private static final EnumCreatureType[] h = new EnumCreatureType[] { MONSTER, CREATURE };

    private EnumCreatureType(String s, int i, Class oclass, int j, boolean flag) {
        this.d = oclass;
        this.e = j;
        this.g = flag;
    }

    public Class a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public boolean d() {
        return this.g;
    }
}
