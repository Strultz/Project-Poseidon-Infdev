package net.minecraft.server;

import org.bukkit.BlockChangeDelegate;
import org.bukkit.craftbukkit.generator.*;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;

// CraftBukkit start

public class WorldServer extends World implements BlockChangeDelegate {
    // CraftBukkit end

    public ChunkProviderServer chunkProviderServer;
    public boolean weirdIsOpCache = false;
    public boolean canSave;
    public final MinecraftServer server; // CraftBukkit - private -> public final
    private EntityList G = new EntityList();

    // CraftBukkit start - change signature
    public WorldServer(MinecraftServer minecraftserver, IDataManager idatamanager, String s, int i, long j, ChunkGenerator gen) {
        super(idatamanager, s, j, WorldProvider.byDimension(), gen);
        this.server = minecraftserver;

        this.dimension = i;
        this.pvpMode = minecraftserver.pvpMode;
        this.manager = new PlayerManager(minecraftserver, this.dimension, minecraftserver.propertyManager.getInt("view-distance", 10));
    }

    public final int dimension;
    public EntityTracker tracker;
    public PlayerManager manager;
    // CraftBukkit end

    public void entityJoinedWorld(Entity entity, boolean flag) {
        /* CraftBukkit start - We prevent spawning in general, so this butchering is not needed
        if (!this.server.spawnAnimals && (entity instanceof EntityAnimal || entity instanceof EntityWaterAnimal)) {
            entity.die();
        }
        // CraftBukkit end */

        super.entityJoinedWorld(entity, flag);
    }

    public void vehicleEnteredWorld(Entity entity, boolean flag) {
        super.entityJoinedWorld(entity, flag);
    }

    protected IChunkProvider b() {
        IChunkLoader ichunkloader = this.w.a(this.worldProvider);

        // CraftBukkit start
        InternalChunkGenerator gen = new NormalChunkGenerator(this, this.getSeed());

        this.chunkProviderServer = new ChunkProviderServer(this, ichunkloader, gen);
        // CraftBukkit end

        return this.chunkProviderServer;
    }

    public List getTileEntities(int i, int j, int k, int l, int i1, int j1) {
        ArrayList arraylist = new ArrayList();

        for (int k1 = 0; k1 < this.c.size(); ++k1) {
            TileEntity tileentity = (TileEntity) this.c.get(k1);

            if (tileentity.x >= i && tileentity.y >= j && tileentity.z >= k && tileentity.x < l && tileentity.y < i1 && tileentity.z < j1) {
                arraylist.add(tileentity);
            }
        }

        return arraylist;
    }

    public boolean a(EntityHuman entityhuman, int i, int j, int k) {
        int l = (int) MathHelper.abs((float) (i - this.worldData.c()));
        int i1 = (int) MathHelper.abs((float) (k - this.worldData.e()));

        if (l > i1) {
            i1 = l;
        }

        // CraftBukkit - Configurable spawn protection
        return i1 > this.getServer().getSpawnRadius() || this.server.serverConfigurationManager.isOp(entityhuman.name);
    }

    protected void c(Entity entity) {
        super.c(entity);
        this.G.a(entity.id, entity);
    }

    protected void d(Entity entity) {
        super.d(entity);
        this.G.d(entity.id);
    }

    public Entity getEntity(int i) {
        return (Entity) this.G.a(i);
    }

    public void a(Entity entity, byte b0) {
        Packet38EntityStatus packet38entitystatus = new Packet38EntityStatus(entity.id, b0);

        // CraftBukkit
        this.server.getTracker(this.dimension).sendPacketToEntity(entity, packet38entitystatus);
    }

    public Explosion createExplosion(Entity entity, double d0, double d1, double d2, float f, boolean flag) {
        // CraftBukkit start
        Explosion explosion = super.createExplosion(entity, d0, d1, d2, f, flag);

        if (explosion.wasCanceled) {
            return explosion;
        }

        /* Remove
        explosion.a = flag;
        explosion.a();
        explosion.a(false);
        */
        this.server.serverConfigurationManager.sendPacketNearby(d0, d1, d2, 64.0D, this.dimension, new Packet60Explosion(d0, d1, d2, f, explosion.blocks));
        // CraftBukkit end
        return explosion;
    }
    
    public void saveLevel() {
        this.w.e();
    }
}
