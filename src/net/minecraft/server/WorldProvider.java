package net.minecraft.server;

public abstract class WorldProvider {

    public World a;
    public float[] f = new float[16];
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

    public float a(long worldTime, float var1) {
		int var2;
		if((var1 = ((float)(var2 = (int)(worldTime % 24000L)) + var1) / 24000.0F - 0.25F) < 0.0F) {
			++var1;
		}

		if(var1 > 1.0F) {
			--var1;
		}

		float var3 = var1;
		var1 = 1.0F - (float)((Math.cos((double)var1 * 3.141592653589793D) + 1.0D) / 2.0D);
		return var3 + (var1 - var3) / 3.0F;
    }

    public boolean d() {
        return true;
    }

    public static WorldProvider byDimension() {
        return new WorldProviderNormal();
    }
}
