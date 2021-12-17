package net.minecraft.server;

public abstract class WorldProvider {

    public World a;
    public boolean c = false;
    public boolean d = false;
    public boolean e = false;
    public float[] f = new float[16];
    public int dimension = 0;
    private float[] h = new float[4];

    public WorldProvider() {}

    public final void a(World world) {
        this.a = world;
        this.c();
    }

    protected void c() {
        float f = 0.05F;

        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0F - (float) i / 15.0F;

            this.f[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    public IChunkProvider getChunkProvider() {
        return new ChunkProviderGenerate(this.a, this.a.getSeed());
    }

    public boolean canSpawn(int i, int j) {
        int k = this.a.a(i, j);

        return k == Block.SAND.id;
    }

    public float a(long i, float f) {
    	return f = ((int)(i % 24000L) + f) / 24000.0f - 0.15f;
    }

    public boolean d() {
        return true;
    }

    public static WorldProvider byDimension(int i) {
        return new WorldProviderNormal();
    }
}
