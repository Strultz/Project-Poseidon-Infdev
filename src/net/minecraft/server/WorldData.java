package net.minecraft.server;

import java.util.List;

public class WorldData {

    private long a;
    private int b;
    private int c;
    private int d;
    private long e;
    private long f;
    private long g;
    private NBTTagCompound h;
    public String name; // CraftBukkit - private -> public
    private int k;

    public WorldData(NBTTagCompound nbttagcompound) {
        this.a = nbttagcompound.getLong("RandomSeed");
        this.b = nbttagcompound.e("SpawnX");
        this.c = nbttagcompound.e("SpawnY");
        this.d = nbttagcompound.e("SpawnZ");
        this.e = nbttagcompound.getLong("Time");
        this.f = nbttagcompound.getLong("LastPlayed");
        this.g = nbttagcompound.getLong("SizeOnDisk");
        this.name = nbttagcompound.getString("LevelName");
        this.k = nbttagcompound.e("version");
        if (nbttagcompound.hasKey("Player")) {
            this.h = nbttagcompound.k("Player");
        }
    }

    public WorldData(long i, String s) {
        this.a = i;
        this.name = s;
    }

    public WorldData(WorldData worlddata) {
        this.a = worlddata.a;
        this.b = worlddata.b;
        this.c = worlddata.c;
        this.d = worlddata.d;
        this.e = worlddata.e;
        this.f = worlddata.f;
        this.g = worlddata.g;
        this.h = worlddata.h;
        this.name = worlddata.name;
        this.k = worlddata.k;
    }

    public NBTTagCompound a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        this.a(nbttagcompound, this.h);
        return nbttagcompound;
    }

    public NBTTagCompound a(List list) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        EntityHuman entityhuman = null;
        NBTTagCompound nbttagcompound1 = null;

        if (list.size() > 0) {
            entityhuman = (EntityHuman) list.get(0);
        }

        if (entityhuman != null) {
            nbttagcompound1 = new NBTTagCompound();
            entityhuman.d(nbttagcompound1);
        }

        this.a(nbttagcompound, nbttagcompound1);
        return nbttagcompound;
    }

    private void a(NBTTagCompound nbttagcompound, NBTTagCompound nbttagcompound1) {
        nbttagcompound.setLong("RandomSeed", this.a);
        nbttagcompound.a("SpawnX", this.b);
        nbttagcompound.a("SpawnY", this.c);
        nbttagcompound.a("SpawnZ", this.d);
        nbttagcompound.setLong("Time", this.e);
        nbttagcompound.setLong("SizeOnDisk", this.g);
        nbttagcompound.setLong("LastPlayed", System.currentTimeMillis());
        nbttagcompound.setString("LevelName", this.name);
        nbttagcompound.a("version", this.k);
        if (nbttagcompound1 != null) {
            nbttagcompound.a("Player", nbttagcompound1);
        }
    }

    public long getSeed() {
        return this.a;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }

    public long f() {
        return this.e;
    }

    public long g() {
        return this.g;
    }

    public void a(long i) {
        this.e = i;
    }

    public void b(long i) {
        this.g = i;
    }

    public void setSpawn(int i, int j, int k) {
        this.b = i;
        this.c = j;
        this.d = k;
    }

    public void a(String s) {
        this.name = s;
    }

    public int i() {
        return this.k;
    }

    public void a(int i) {
        this.k = i;
    }
}
