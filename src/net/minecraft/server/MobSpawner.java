package net.minecraft.server;

import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public final class MobSpawner {
    private int field_a;
    private Class field_b;
    private Class[] field_c;
    
    public MobSpawner(final int field_a, final Class field_b, final Class[] field_c) {
        this.field_a = field_a;
        this.field_b = field_b;
        this.field_c = field_c;
    }
    
    public final void func_a(final World world) {
        if (world.a(this.field_b) < this.field_a) {
            this.spawn(world, 1);
        }
    }
    
    public int spawn(final World world, int n) {
    	n = 0;
        for (int var3 = 0; var3 < world.players.size(); ++var3)
        {
            EntityPlayer entity = (EntityPlayer)world.players.get(var3);
	        final int floor_double = MathHelper.floor(entity.locX);
	        final int floor_double2 = MathHelper.floor(entity.locZ);
	        for (int i = 0; i <= 0; ++i) {
	            final int nextInt = world.random.nextInt(this.field_c.length);
	            final int n2 = floor_double + world.random.nextInt(256) - 128;
	            final int nextInt2 = world.random.nextInt(128);
	            final int n3 = floor_double2 + world.random.nextInt(256) - 128;
	            if (!world.p(n2, nextInt2, n3) && world.getMaterial(n2, nextInt2, n3) == Material.AIR) {
	                for (int j = 0; j < 6; ++j) {
	                    int n4 = n2;
	                    int n5 = nextInt2;
	                    int n6 = n3;
	                    for (int k = 0; k < 6; ++k) {
	                        n4 += world.random.nextInt(6) - world.random.nextInt(6);
	                        n5 += world.random.nextInt(1) - world.random.nextInt(1);
	                        n6 += world.random.nextInt(6) - world.random.nextInt(6);
	                        if (world.p(n4, n5 - 1, n6) && !world.p(n4, n5, n6) && !world.getMaterial(n4, n5, n6).isLiquid() && !world.p(n4, n5 + 1, n6)) {
	                            final float n7 = n4 + 0.5f;
	                            final float n8 = n5 + 1.0f;
	                            final float n9 = n6 + 0.5f;
	                            if (entity != null) {
	                                final double n10 = n7 - entity.locX;
	                                final double n11 = n8 - entity.locY;
	                                final double n12 = n9 - entity.locZ;
	                                if (n10 * n10 + n11 * n11 + n12 * n12 < 256.0) {
	                                    continue;
	                                }
	                            }
	                            else {
	                                final float n13 = n7 - world.getSpawn().x;
	                                final float n14 = n8 - world.getSpawn().y;
	                                final float n15 = n9 - world.getSpawn().z;
	                                if (n13 * n13 + n14 * n14 + n15 * n15 < 256.0f) {
	                                    continue;
	                                }
	                            }
	                            EntityLiving entityLiving;
	                            try {
	                                entityLiving = (EntityLiving)this.field_c[nextInt].getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
	                            }
	                            catch (Exception ex) {
	                                ex.printStackTrace();
	                                continue;
	                            }
	                            entityLiving.setPositionRotation(n7, n8, n9, world.random.nextFloat() * 360.0f, 0.0f);
	                            if (entityLiving.d()) {
	                                ++n;
	                                world.addEntity(entityLiving, SpawnReason.NATURAL);
	                            }
	                        }
	                    }
	                }
	            }
	        }
        }
        return n;
    }
}
