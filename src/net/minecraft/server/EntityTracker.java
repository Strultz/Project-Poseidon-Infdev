package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EntityTracker {

    private Set a = new HashSet();
    public EntityList b = new EntityList(); //Project Poseidon: private -> public
    private MinecraftServer c;
    private int d;
    private int e;

    public EntityTracker(MinecraftServer minecraftserver, int i) {
        this.c = minecraftserver;
        this.e = i;
        this.d = minecraftserver.serverConfigurationManager.a();
    }

    // CraftBukkit - synchronized
    public synchronized void track(Entity entity) {
        if (entity instanceof EntityPlayer) {
            this.a(entity, 512, 2);
            EntityPlayer entityplayer = (EntityPlayer) entity;
            Iterator iterator = this.a.iterator();

            while (iterator.hasNext()) {
                EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) iterator.next();

                if (entitytrackerentry.tracker != entityplayer) {
                    entitytrackerentry.b(entityplayer);
                }
            }
        } else if (entity instanceof EntityArrow) {
            this.a(entity, 64, 20, false);
        } else if (entity instanceof EntityItem) {
            this.a(entity, 64, 20, true);
        } else if (entity instanceof EntityMinecart) {
            this.a(entity, 160, 5, true);
        } else if (entity instanceof IAnimal) {
            this.a(entity, 160, 3);
        } else if (entity instanceof EntityTNTPrimed) {
            this.a(entity, 160, 10, true);
        } else if (entity instanceof EntityFallingSand) {
            this.a(entity, 160, 20, true);
        } else if (entity instanceof EntityPainting) {
            this.a(entity, 160, Integer.MAX_VALUE, false);
        }
    }

    public void a(Entity entity, int i, int j) {
        this.a(entity, i, j, false);
    }

    // CraftBukkit - synchronized
    public synchronized void a(Entity entity, int i, int j, boolean flag) {
        if (i > this.d) {
            i = this.d;
        }

        if (this.b.b(entity.id)) {
            // CraftBukkit - removed exception throw as tracking an already tracked entity theoretically shouldn't cause any issues.
            // throw new IllegalStateException("Entity is already tracked!");
        } else {
            EntityTrackerEntry entitytrackerentry = new EntityTrackerEntry(entity, i, j, flag);

            this.a.add(entitytrackerentry);
            this.b.a(entity.id, entitytrackerentry);
            entitytrackerentry.scanPlayers(this.c.getWorldServer(this.e).players);
        }
    }

    // CraftBukkit - synchronized
    public synchronized void untrackEntity(Entity entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entity;
            Iterator iterator = this.a.iterator();

            while (iterator.hasNext()) {
                EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) iterator.next();

                entitytrackerentry.a(entityplayer);
            }
        }

        EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry) this.b.d(entity.id);

        if (entitytrackerentry1 != null) {
            this.a.remove(entitytrackerentry1);
            entitytrackerentry1.a();
        }
    }

    // CraftBukkit - synchronized
    public synchronized void updatePlayers() {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) iterator.next();

            entitytrackerentry.track(this.c.getWorldServer(this.e).players);
            if (entitytrackerentry.m && entitytrackerentry.tracker instanceof EntityPlayer) {
                arraylist.add((EntityPlayer) entitytrackerentry.tracker);
            }
        }

        for (int i = 0; i < arraylist.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer) arraylist.get(i);
            Iterator iterator1 = this.a.iterator();

            while (iterator1.hasNext()) {
                EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry) iterator1.next();

                if (entitytrackerentry1.tracker != entityplayer) {
                    entitytrackerentry1.b(entityplayer);
                }
            }
        }
    }

    // CraftBukkit - synchronized
    public synchronized void a(Entity entity, Packet packet) {
        EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) this.b.a(entity.id);

        if (entitytrackerentry != null) {
            entitytrackerentry.a(packet);
        }
    }

    // CraftBukkit - synchronized
    public synchronized void sendPacketToEntity(Entity entity, Packet packet) {
        EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) this.b.a(entity.id);

        if (entitytrackerentry != null) {
            entitytrackerentry.b(packet);
        }
    }

    // CraftBukkit - synchronized
    public synchronized void untrackPlayer(EntityPlayer entityplayer) {
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) iterator.next();

            entitytrackerentry.c(entityplayer);
        }
    }
    
    // Poseidon
    // CraftBukkit - synchronized
    public synchronized void a(EntityPlayer entityplayer, Chunk chunk) {
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) iterator.next();

            if (entitytrackerentry.tracker != entityplayer && entitytrackerentry.tracker.bH == chunk.x && entitytrackerentry.tracker.bJ == chunk.z) {
                entitytrackerentry.b(entityplayer);
            }
        }
    }
}
