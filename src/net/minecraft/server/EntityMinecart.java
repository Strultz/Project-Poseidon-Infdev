package net.minecraft.server;

import org.bukkit.Location;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.*;

import java.util.List;

// CraftBukkit start
// CraftBukkit end

public class EntityMinecart extends Entity implements IInventory {

    private ItemStack[] items;
    private boolean i;
    private int k;
    private double l;
    private double m;
    private double n;
    private double o;
    private double p;
    private static final int[][][] matrix = new int[][][]{{{0, 0, -1}, {0, 0, 1}}, {{-1, 0, 0}, {1, 0, 0}}, {{-1, -1, 0}, {1, 0, 0}}, {{-1, 0, 0}, {1, -1, 0}}, {{0, 0, -1}, {0, -1, 1}}, {{0, -1, -1}, {0, 0, 1}}, {{0, 0, 1}, {1, 0, 0}}, {{0, 0, 1}, {-1, 0, 0}}, {{0, 0, -1}, {-1, 0, 0}}, {{0, 0, -1}, {1, 0, 0}}};

    // CraftBukkit start
    public boolean slowWhenEmpty = true;
    public double derailedX = 0.5;
    public double derailedY = 0.5;
    public double derailedZ = 0.5;
    public double flyingX = 0.95;
    public double flyingY = 0.95;
    public double flyingZ = 0.95;
    public double maxSpeed = 0.4D;

    public ItemStack[] getContents() {
        return this.items;
    }
    // CraftBukkit end

    public EntityMinecart(World world) {
        super(world);
        this.items = new ItemStack[27]; // CraftBukkit
        this.i = false;
        this.aI = true;
        this.b(0.98F, 0.98F);
        this.height = this.width / 2.0F;
    }

    protected boolean n() {
        return false;
    }

    protected void b() {
        this.datawatcher.a(16, (byte) 0);
        this.datawatcher.a(17, 0);
        this.datawatcher.a(18, 1);
        this.datawatcher.a(19, 0);
    }

    public boolean d_() {
        return true;
    }

    public EntityMinecart(World world, double d0, double d1, double d2) {
        this(world);
        this.setPosition(d0, d1 + (double) this.height, d2);
        this.motX = 0.0D;
        this.motY = 0.0D;
        this.motZ = 0.0D;
        this.lastX = d0;
        this.lastY = d1;
        this.lastZ = d2;
        this.world.getServer().getPluginManager().callEvent(new VehicleCreateEvent((Vehicle) this.getBukkitEntity())); // CraftBukkit
    }

    public boolean damageEntity(Entity entity, int i) {
        if (!this.world.isStatic && !this.dead) {
            // CraftBukkit start
            Vehicle vehicle = (Vehicle) this.getBukkitEntity();
            org.bukkit.entity.Entity passenger = (entity == null) ? null : entity.getBukkitEntity();

            VehicleDamageEvent event = new VehicleDamageEvent(vehicle, passenger, i);
            this.world.getServer().getPluginManager().callEvent(event);

            if (event.isCancelled()) {
                return true;
            }

            i = event.getDamage();
            // CraftBukkit end

            this.func_70494_i(-this.func_70493_k());
            this.func_70497_h(10);
            this.af();
            this.setDamage(this.getDamage() + i * 10);
            if (this.getDamage() > 40) {
                // CraftBukkit start
                VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, passenger);
                this.world.getServer().getPluginManager().callEvent(destroyEvent);

                if (destroyEvent.isCancelled()) {
                    this.setDamage(40); // Maximize damage so this doesn't get triggered again right away
                    return true;
                }
                // CraftBukkit end

                this.die();
                this.a(Item.MINECART.id, 1, 0.0F);
                EntityMinecart entityminecart = this;

                for (int j = 0; j < entityminecart.getSize(); ++j) {
                    ItemStack itemstack = entityminecart.getItem(j);

                    if (itemstack != null) {
                        float f = this.random.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.count > 0) {
                            int k = this.random.nextInt(21) + 10;

                            if (k > itemstack.count) {
                                k = itemstack.count;
                            }

                            itemstack.count -= k;
                            EntityItem entityitem = new EntityItem(this.world, this.locX + (double) f, this.locY + (double) f1, this.locZ + (double) f2, new ItemStack(itemstack.id, k, itemstack.getData()));
                            float f3 = 0.05F;

                            entityitem.motX = (double) ((float) this.random.nextGaussian() * f3);
                            entityitem.motY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
                            entityitem.motZ = (double) ((float) this.random.nextGaussian() * f3);
                            this.world.addEntity(entityitem);
                        }
                        entityminecart.setItem(j, null);
                    }
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public boolean l_() {
        return !this.dead;
    }

    public void die() {
        for (int i = 0; i < this.getSize(); ++i) {
            ItemStack itemstack = this.getItem(i);

            if (itemstack != null) {
                float f = this.random.nextFloat() * 0.8F + 0.1F;
                float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                while (itemstack.count > 0) {
                    int j = this.random.nextInt(21) + 10;

                    if (j > itemstack.count) {
                        j = itemstack.count;
                    }

                    itemstack.count -= j;
                    EntityItem entityitem = new EntityItem(this.world, this.locX + (double) f, this.locY + (double) f1, this.locZ + (double) f2, new ItemStack(itemstack.id, j, itemstack.getData()));
                    float f3 = 0.05F;

                    entityitem.motX = (double) ((float) this.random.nextGaussian() * f3);
                    entityitem.motY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
                    entityitem.motZ = (double) ((float) this.random.nextGaussian() * f3);
                    this.world.addEntity(entityitem);
                }
            }
        }

        super.die();
    }

    public void m_() {
        // CraftBukkit start
        double prevX = this.locX;
        double prevY = this.locY;
        double prevZ = this.locZ;
        float prevYaw = this.yaw;
        float prevPitch = this.pitch;
        // CraftBukkit end
        
        if (this.func_70496_j() > 0)
        {
            this.func_70497_h(this.func_70496_j() - 1);
        }

        if (this.getDamage() > 0)
        {
            this.setDamage(this.getDamage() - 1);
        }

        if(this.locY < -2.0D) {
            this.motX *= 0.85D;
            this.motY *= 0.85D;
            this.motZ *= 0.85D;
            this.Y();
        }

        double d0;

        if (this.world.isStatic && this.k > 0) {
            if (this.k > 0) {
                double d1 = this.locX + (this.l - this.locX) / (double) this.k;
                double d2 = this.locY + (this.m - this.locY) / (double) this.k;
                double d3 = this.locZ + (this.n - this.locZ) / (double) this.k;

                for (d0 = this.o - (double) this.yaw; d0 < -180.0D; d0 += 360.0D) {
                    ;
                }

                while (d0 >= 180.0D) {
                    d0 -= 360.0D;
                }

                this.yaw = (float) ((double) this.yaw + d0 / (double) this.k);
                this.pitch = (float) ((double) this.pitch + (this.p - (double) this.pitch) / (double) this.k);
                --this.k;
                this.setPosition(d1, d2, d3);
                this.c(this.yaw, this.pitch);
            } else {
                this.setPosition(this.locX, this.locY, this.locZ);
                this.c(this.yaw, this.pitch);
            }
        } else {
            this.lastX = this.locX;
            this.lastY = this.locY;
            this.lastZ = this.locZ;
            this.motY -= 0.03999999910593033D;
            int i = MathHelper.floor(this.locX);
            int j = MathHelper.floor(this.locY);
            int k = MathHelper.floor(this.locZ);

            if (BlockMinecartTrack.g(this.world, i, j - 1, k)) {
                --j;
            }

            // CraftBukkit
            double d4 = this.maxSpeed;

            d0 = 0.0078125D;
            int l = this.world.getTypeId(i, j, k);

            if (BlockMinecartTrack.c(l)) {
                Vec3D vec3d = this.h(this.locX, this.locY, this.locZ);
                int i1 = this.world.getData(i, j, k);

                this.locY = (double) j;
                
                if (i1 >= 2 && i1 <= 5) {
                    this.locY = (double) (j + 1);
                }

                if (i1 == 2) {
                    this.motX -= d0;
                }

                if (i1 == 3) {
                    this.motX += d0;
                }

                if (i1 == 4) {
                    this.motZ += d0;
                }

                if (i1 == 5) {
                    this.motZ -= d0;
                }

                int[][] aint = matrix[i1];
                double d5 = (double) (aint[1][0] - aint[0][0]);
                double d6 = (double) (aint[1][2] - aint[0][2]);
                double d7 = Math.sqrt(d5 * d5 + d6 * d6);
                double d8 = this.motX * d5 + this.motZ * d6;

                if (d8 < 0.0D) {
                    d5 = -d5;
                    d6 = -d6;
                }

                double d9 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);

                this.motX = d9 * d5 / d7;
                this.motZ = d9 * d6 / d7;
                double d10 = 0.0D;
                double d11 = (double) i + 0.5D + (double) aint[0][0] * 0.5D;
                double d12 = (double) k + 0.5D + (double) aint[0][2] * 0.5D;
                double d13 = (double) i + 0.5D + (double) aint[1][0] * 0.5D;
                double d14 = (double) k + 0.5D + (double) aint[1][2] * 0.5D;

                d5 = d13 - d11;
                d6 = d14 - d12;
                double d15;
                double d16;
                double d17;

                if (d5 == 0.0D) {
                    this.locX = (double) i + 0.5D;
                    d10 = this.locZ - (double) k;
                } else if (d6 == 0.0D) {
                    this.locZ = (double) k + 0.5D;
                    d10 = this.locX - (double) i;
                } else {
                    d16 = this.locX - d11;
                    d15 = this.locZ - d12;
                    d17 = (d16 * d5 + d15 * d6) * 2.0D;
                    d10 = d17;
                }

                this.locX = d11 + d5 * d10;
                this.locZ = d12 + d6 * d10;
                this.setPosition(this.locX, this.locY + (double) this.height, this.locZ);
                d16 = this.motX;
                d15 = this.motZ;

                if (d16 < -d4) {
                    d16 = -d4;
                }

                if (d16 > d4) {
                    d16 = d4;
                }

                if (d15 < -d4) {
                    d15 = -d4;
                }

                if (d15 > d4) {
                    d15 = d4;
                }

                this.move(d16, 0.0D, d15);
                if (aint[0][1] != 0 && MathHelper.floor(this.locX) - i == aint[0][0] && MathHelper.floor(this.locZ) - k == aint[0][2]) {
                    this.setPosition(this.locX, this.locY + (double) aint[0][1], this.locZ);
                } else if (aint[1][1] != 0 && MathHelper.floor(this.locX) - i == aint[1][0] && MathHelper.floor(this.locZ) - k == aint[1][2]) {
                    this.setPosition(this.locX, this.locY + (double) aint[1][1], this.locZ);
                }

                // CraftBukkit
                if (!this.slowWhenEmpty) {
                    this.motX *= 0.996999979019165D;
                    this.motY *= 0.0D;
                    this.motZ *= 0.996999979019165D;
                } else {
                    this.motX *= 0.9599999785423279D;
                    this.motY *= 0.0D;
                    this.motZ *= 0.9599999785423279D;
                }

                Vec3D vec3d1 = this.h(this.locX, this.locY, this.locZ);

                if (vec3d1 != null && vec3d != null) {
                    double d19 = (vec3d.b - vec3d1.b) * 0.05D;

                    d9 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
                    if (d9 > 0.0D) {
                        this.motX = this.motX / d9 * (d9 + d19);
                        this.motZ = this.motZ / d9 * (d9 + d19);
                    }

                    this.setPosition(this.locX, vec3d1.b, this.locZ);
                }

                int j1 = MathHelper.floor(this.locX);
                int k1 = MathHelper.floor(this.locZ);

                if (j1 != i || k1 != k) {
                    d9 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
                    this.motX = d9 * (double) (j1 - i);
                    this.motZ = d9 * (double) (k1 - k);
                }
            } else {
                if (this.motX < -d4) {
                    this.motX = -d4;
                }

                if (this.motX > d4) {
                    this.motX = d4;
                }

                if (this.motZ < -d4) {
                    this.motZ = -d4;
                }

                if (this.motZ > d4) {
                    this.motZ = d4;
                }

                if (this.onGround) {
                    // CraftBukkit start
                    this.motX *= this.derailedX;
                    this.motY *= this.derailedY;
                    this.motZ *= this.derailedZ;
                    // CraftBukkit start
                }

                this.move(this.motX, this.motY, this.motZ);
                if (!this.onGround) {
                    // CraftBukkit start
                    this.motX *= this.flyingX;
                    this.motY *= this.flyingY;
                    this.motZ *= this.flyingZ;
                    // CraftBukkit start
                }
            }

            this.pitch = 0.0F;
            double d22 = this.lastX - this.locX;
            double d23 = this.lastZ - this.locZ;

            if (d22 * d22 + d23 * d23 > 0.0010D) {
                this.yaw = (float) (Math.atan2(d23, d22) * 180.0D / 3.141592653589793D);
                if (this.i) {
                    this.yaw += 180.0F;
                }
            }

            double d24;

            for (d24 = (double) (this.yaw - this.lastYaw); d24 >= 180.0D; d24 -= 360.0D) {
                ;
            }

            while (d24 < -180.0D) {
                d24 += 360.0D;
            }

            if (d24 < -170.0D || d24 >= 170.0D) {
                this.yaw += 180.0F;
                this.i = !this.i;
            }

            this.c(this.yaw, this.pitch);

            // CraftBukkit start
            org.bukkit.World bworld = this.world.getWorld();
            Location from = new Location(bworld, prevX, prevY, prevZ, prevYaw, prevPitch);
            Location to = new Location(bworld, this.locX, this.locY, this.locZ, this.yaw, this.pitch);
            Vehicle vehicle = (Vehicle) this.getBukkitEntity();

            this.world.getServer().getPluginManager().callEvent(new VehicleUpdateEvent(vehicle));

            if (!from.equals(to)) {
                this.world.getServer().getPluginManager().callEvent(new VehicleMoveEvent(vehicle, from, to));
            }
            // CraftBukkit end

            List list = this.world.b((Entity) this, this.boundingBox.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (list != null && list.size() > 0) {
                for (int l1 = 0; l1 < list.size(); ++l1) {
                    Entity entity = (Entity) list.get(l1);

                    if (entity.d_() && entity instanceof EntityMinecart) {
                        entity.collide(this);
                    }
                }
            }
        }
    }

    public Vec3D h(double d0, double d1, double d2) {
        int i = MathHelper.floor(d0);
        int j = MathHelper.floor(d1);
        int k = MathHelper.floor(d2);

        if (BlockMinecartTrack.g(this.world, i, j - 1, k)) {
            --j;
        }

        int l = this.world.getTypeId(i, j, k);

        if (BlockMinecartTrack.c(l)) {
            int i1 = this.world.getData(i, j, k);

            d1 = (double) j;

            if (i1 >= 2 && i1 <= 5) {
                d1 = (double) (j + 1);
            }

            int[][] aint = matrix[i1];
            double d3 = 0.0D;
            double d4 = (double) i + 0.5D + (double) aint[0][0] * 0.5D;
            double d5 = (double) j + 0.5D + (double) aint[0][1] * 0.5D;
            double d6 = (double) k + 0.5D + (double) aint[0][2] * 0.5D;
            double d7 = (double) i + 0.5D + (double) aint[1][0] * 0.5D;
            double d8 = (double) j + 0.5D + (double) aint[1][1] * 0.5D;
            double d9 = (double) k + 0.5D + (double) aint[1][2] * 0.5D;
            double d10 = d7 - d4;
            double d11 = (d8 - d5) * 2.0D;
            double d12 = d9 - d6;

            if (d10 == 0.0D) {
                d0 = (double) i + 0.5D;
                d3 = d2 - (double) k;
            } else if (d12 == 0.0D) {
                d2 = (double) k + 0.5D;
                d3 = d0 - (double) i;
            } else {
                double d13 = d0 - d4;
                double d14 = d2 - d6;
                double d15 = (d13 * d10 + d14 * d12) * 2.0D;

                d3 = d15;
            }

            d0 = d4 + d10 * d3;
            d1 = d5 + d11 * d3;
            d2 = d6 + d12 * d3;
            if (d11 < 0.0D) {
                ++d1;
            }

            if (d11 > 0.0D) {
                d1 += 0.5D;
            }

            return Vec3D.create(d0, d1, d2);
        } else {
            return null;
        }
    }

    protected void b(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.a("Slot", (byte) i);
                this.items[i].a(nbttagcompound1);
                nbttaglist.a((NBTBase) nbttagcompound1);
            }
        }

        nbttagcompound.a("Items", (NBTBase) nbttaglist);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = nbttagcompound.l("Items");

        this.items = new ItemStack[this.getSize()];

        for (int i = 0; i < nbttaglist.c(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.a(i);
            int j = nbttagcompound1.c("Slot") & 255;

            if (j >= 0 && j < this.items.length) {
                this.items[j] = new ItemStack(nbttagcompound1);
            }
        }
        
        datawatcher.watch(16, (byte)getDirtHeightRaw());
    }

    public void collide(Entity entity) {
        if (!this.world.isStatic) {
            // CraftBukkit start
            Vehicle vehicle = (Vehicle) this.getBukkitEntity();
            org.bukkit.entity.Entity hitEntity = (entity == null) ? null : entity.getBukkitEntity();

            VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent(vehicle, hitEntity);
            this.world.getServer().getPluginManager().callEvent(collisionEvent);

            if (collisionEvent.isCancelled()) {
                return;
            }
            // CraftBukkit end

            double d0 = entity.locX - this.locX;
            double d1 = entity.locZ - this.locZ;
            double d2 = d0 * d0 + d1 * d1;

            // CraftBukkit - Collision
            if (d2 >= 9.999999747378752E-5D && !collisionEvent.isCollisionCancelled()) {
                d2 = (double) MathHelper.a(d2);
                d0 /= d2;
                d1 /= d2;
                double d3 = 1.0D / d2;

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                d0 *= d3;
                d1 *= d3;
                d0 *= 0.10000000149011612D;
                d1 *= 0.10000000149011612D;
                d0 *= (double) (1.0F - this.bu);
                d1 *= (double) (1.0F - this.bu);
                d0 *= 0.5D;
                d1 *= 0.5D;
                if (entity instanceof EntityMinecart) {
                    double d7 = entity.motX + this.motX;
                    double d8 = entity.motZ + this.motZ;

                    d7 /= 2.0D;
                    d8 /= 2.0D;
                    this.motX *= 0.20000000298023224D;
                    this.motZ *= 0.20000000298023224D;
                    this.b(d7 - d0, 0.0D, d8 - d1);
                    entity.motX *= 0.20000000298023224D;
                    entity.motZ *= 0.20000000298023224D;
                    entity.b(d7 + d0, 0.0D, d8 + d1);
                } else {
                    this.b(-d0, 0.0D, -d1);
                    entity.b(d0 / 4.0D, 0.0D, d1 / 4.0D);
                }
            }
        }
    }

    public int getSize() {
        return 27;
    }

    public ItemStack getItem(int i) {
        return this.items[i];
    }

    public ItemStack splitStack(int i, int j) {
        if (this.items[i] != null) {
            ItemStack itemstack;

            if (this.items[i].count <= j) {
                itemstack = this.items[i];
                this.items[i] = null;
                return itemstack;
            } else {
                itemstack = this.items[i].a(j);
                if (this.items[i].count == 0) {
                    this.items[i] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public void setItem(int i, ItemStack itemstack) {
        this.items[i] = itemstack;
        if (itemstack != null && itemstack.count > this.getMaxStackSize()) {
            itemstack.count = this.getMaxStackSize();
        }
    }

    public String getName() {
        return "Minecart";
    }

    public int getMaxStackSize() {
        return 64;
    }

    public void update() {
        if (!this.world.isStatic) {
            datawatcher.watch(16, (byte)getDirtHeightRaw());
        }
    }

    public boolean a(EntityHuman entityhuman) {
        if (!this.world.isStatic) {
            entityhuman.a((IInventory) this);
        }

        return true;
    }
    
    public final float getDirtHeight() {
        return (float)datawatcher.a(16) / (float)this.items.length;
    }

    public final int getDirtHeightRaw() {
        int n = 0;
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null) {
                ++n;
            }
        }
        return n;
    }
    
    public void setDamage(int par1)
    {
        this.datawatcher.watch(19, Integer.valueOf(par1));
    }
    
    public int getDamage()
    {
        return this.datawatcher.b(19);
    }

    public void func_70497_h(int par1)
    {
        this.datawatcher.watch(17, Integer.valueOf(par1));
    }

    public int func_70496_j()
    {
        return this.datawatcher.b(17);
    }

    public void func_70494_i(int par1)
    {
        this.datawatcher.watch(18, Integer.valueOf(par1));
    }

    public int func_70493_k()
    {
        return this.datawatcher.b(18);
    }

    public boolean a_(EntityHuman entityhuman) {
        return this.dead ? false : entityhuman.g(this) <= 64.0D;
    }
}
