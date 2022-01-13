package net.minecraft.server;

import java.util.Random;

public final class WorldGenFlowers extends WorldGenerator {
	private int blockId;

	public WorldGenFlowers(int var1) {
		this.blockId = var1;
	}

	public final boolean a(World var1, Random var2, int var3, int var4, int var5) {
		for(int var6 = 0; var6 < 64; ++var6) {
			int var7 = var3 + var2.nextInt(8) - var2.nextInt(8);
			int var8 = var4 + var2.nextInt(4) - var2.nextInt(4);
			int var9 = var5 + var2.nextInt(8) - var2.nextInt(8);
			if(var1.getTypeId(var7, var8, var9) == 0 && ((BlockFlower)Block.byId[this.blockId]).f(var1, var7, var8, var9)) {
				var1.setRawTypeId(var7, var8, var9, this.blockId);
			}
		}

		return true;
	}
}
