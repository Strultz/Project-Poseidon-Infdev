package net.minecraft.server;

import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// CraftBukkit

public final class SpawnerCreature {
    private static final Class[] animals = { EntityPig.class, EntitySheep.class };
    private static final Class[] monsters = { EntityZombie.class, EntitySkeleton.class, EntitySpider.class, EntityCreeper.class };
    private static Set b = new HashSet();
    
    public SpawnerCreature() {}

    protected static ChunkPosition a(World world, int i, int j) {
        int k = i + world.random.nextInt(16);
        int l = world.random.nextInt(128);
        int i1 = j + world.random.nextInt(16);

        return new ChunkPosition(k, l, i1);
    }

    public static final int spawnEntities(World world, boolean flag, boolean flag1) {
        if (!flag && !flag1) {
            return 0;
        } else {
            b.clear();

            int i;
            int j;

            for (i = 0; i < world.players.size(); ++i) {
                EntityHuman entityhuman = (EntityHuman) world.players.get(i);
                int k = MathHelper.floor(entityhuman.locX / 16.0D);

                j = MathHelper.floor(entityhuman.locZ / 16.0D);
                byte b0 = 8;

                for (int l = -b0; l <= b0; ++l) {
                    for (int i1 = -b0; i1 <= b0; ++i1) {
                        b.add(new ChunkCoordIntPair(l + k, i1 + j));
                    }
                }
            }

            i = 0;
            ChunkCoordinates chunkcoordinates = world.getSpawn();
            EnumCreatureType[] aenumcreaturetype = EnumCreatureType.values();

            j = aenumcreaturetype.length;

            for (int j1 = 0; j1 < j; ++j1) {
                EnumCreatureType enumcreaturetype = aenumcreaturetype[j1];

                if ((!enumcreaturetype.d() || flag1) && (enumcreaturetype.d() || flag) && world.a(enumcreaturetype.a()) <= enumcreaturetype.b() * b.size() / 256) {
                    Iterator iterator = b.iterator();

                    label113:
                    while (iterator.hasNext()) {
                        ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) iterator.next();
                            int k1 = 0;

                            Class[] entitieslist = enumcreaturetype == EnumCreatureType.MONSTER ? SpawnerCreature.monsters : SpawnerCreature.animals;
                            int pick = world.random.nextInt(entitieslist.length);
                            Class entityClass = entitieslist[pick];

                            ChunkPosition chunkposition = a(world, chunkcoordintpair.x * 16, chunkcoordintpair.z * 16);
                            int i2 = chunkposition.x;
                            int j2 = chunkposition.y;
                            int k2 = chunkposition.z;

                            if (!world.p(i2, j2, k2) && world.getMaterial(i2, j2, k2) == Material.AIR) {
                                int l2 = 0;

                                for (int i3 = 0; i3 < 3; ++i3) {
                                    int j3 = i2;
                                    int k3 = j2;
                                    int l3 = k2;
                                    byte b1 = 6;

                                    for (int i4 = 0; i4 < 4; ++i4) {
                                        j3 += world.random.nextInt(b1) - world.random.nextInt(b1);
                                        k3 += world.random.nextInt(1) - world.random.nextInt(1);
                                        l3 += world.random.nextInt(b1) - world.random.nextInt(b1);
                                        if (a(enumcreaturetype, world, j3, k3, l3)) {
                                            float f = (float) j3 + 0.5F;
                                            float f1 = (float) k3;
                                            float f2 = (float) l3 + 0.5F;

                                            if (world.a((double) f, (double) f1, (double) f2, 24.0D) == null) {
                                                float f3 = f - (float) chunkcoordinates.x;
                                                float f4 = f1 - (float) chunkcoordinates.y;
                                                float f5 = f2 - (float) chunkcoordinates.z;
                                                float f6 = f3 * f3 + f4 * f4 + f5 * f5;

                                                if (f6 >= 576.0F) {
                                                    EntityLiving entityliving;

                                                    try {
                                                        entityliving = (EntityLiving) entityClass.getConstructor(new Class[] { World.class}).newInstance(new Object[] { world});
                                                    } catch (Exception exception) {
                                                        exception.printStackTrace();
                                                        return i;
                                                    }

                                                    entityliving.setPositionRotation((double) f, (double) f1, (double) f2, world.random.nextFloat() * 360.0F, 0.0F);
                                                    if (entityliving.d()) {
                                                        ++l2;
                                                        // CraftBukkit - added a reason for spawning this creature
                                                        world.addEntity(entityliving, SpawnReason.NATURAL);
                                                        if (l2 >= entityliving.l()) {
                                                            continue label113;
                                                        }
                                                    }

                                                    i += l2;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                    }
                }
            }

            return i;
        }
    }

    private static boolean a(EnumCreatureType enumcreaturetype, World world, int i, int j, int k) {
        return world.p(i, j - 1, k) && !world.p(i, j, k) && !world.getMaterial(i, j, k).isLiquid() && !world.p(i, j + 1, k);
    }
}