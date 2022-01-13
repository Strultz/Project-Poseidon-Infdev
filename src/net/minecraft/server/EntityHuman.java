package net.minecraft.server;

import org.bukkit.craftbukkit.TrigMath;
import org.bukkit.craftbukkit.entity.CraftItem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Iterator;
import java.util.List;

// CraftBukkit start
// CraftBukkit end

public abstract class EntityHuman extends EntityLiving {

    public InventoryPlayer inventory = new InventoryPlayer(this);
    public Container defaultContainer;
    public Container activeContainer;
    public byte l = 0;
    public int m = 0;
    public float n;
    public float o;
    public boolean p = false;
    public int q = 0;
    public String name;
    public int dimension;
    public double t;
    public double u;
    public double v;
    public double w;
    public double x;
    public double y;
    public float B;
    public float C;
    private int d = 0;
    
    public EntityHuman(World world) {
        super(world);
        this.defaultContainer = new ContainerPlayer(this.inventory, !world.isStatic);
        this.activeContainer = this.defaultContainer;
        this.height = 1.62F;
        ChunkCoordinates chunkcoordinates = world.getSpawn();

        this.setPositionRotation((double) chunkcoordinates.x + 0.5D, (double) (chunkcoordinates.y + 1), (double) chunkcoordinates.z + 0.5D, 0.0F, 0.0F);
        this.health = 20;
        this.U = "humanoid";
        this.T = 180.0F;
        this.maxFireTicks = 20;
        this.texture = "/mob/char.png";
    }

    protected void b() {
        super.b();
        this.datawatcher.a(16, Byte.valueOf((byte) 0));
    }

    public void m_() {
        super.m_();
        if (!this.world.isStatic && this.activeContainer != null && !this.activeContainer.b(this)) {
            this.y();
            this.activeContainer = this.defaultContainer;
        }

        this.t = this.w;
        this.u = this.x;
        this.v = this.y;
        double d0 = this.locX - this.w;
        double d1 = this.locY - this.x;
        double d2 = this.locZ - this.y;
        double d3 = 10.0D;

        if (d0 > d3) {
            this.t = this.w = this.locX;
        }

        if (d2 > d3) {
            this.v = this.y = this.locZ;
        }

        if (d1 > d3) {
            this.u = this.x = this.locY;
        }

        if (d0 < -d3) {
            this.t = this.w = this.locX;
        }

        if (d2 < -d3) {
            this.v = this.y = this.locZ;
        }

        if (d1 < -d3) {
            this.u = this.x = this.locY;
        }

        this.w += d0 * 0.25D;
        this.y += d2 * 0.25D;
        this.x += d1 * 0.25D;
    }

    protected boolean D() {
        return this.health <= 0;
    }

    protected void y() {
        this.activeContainer = this.defaultContainer;
    }

    protected void c_() {
        if (this.p) {
            ++this.q;
            if (this.q >= 8) {
                this.q = 0;
                this.p = false;
            }
        } else {
            this.q = 0;
        }

        this.aa = (float) this.q / 8.0F;
    }

    public void v() {
        // CraftBukkit - spawnMonsters -> allowMonsters
        if (!this.world.allowMonsters && this.health < 20 && this.ticksLived % 20 * 12 == 0) {
            this.b(1, RegainReason.REGEN);
        }

        this.inventory.f();
        this.n = this.o;
        super.v();
        float f = MathHelper.a(this.motX * this.motX + this.motZ * this.motZ);
        // CraftBukkit - Math -> TrigMath
        float f1 = (float) TrigMath.atan(-this.motY * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.onGround || this.health <= 0) {
            f = 0.0F;
        }

        if (this.onGround || this.health <= 0) {
            f1 = 0.0F;
        }

        this.o += (f - this.o) * 0.4F;
        this.aj += (f1 - this.aj) * 0.8F;
        if (this.health > 0) {
            List list = this.world.b((Entity) this, this.boundingBox.b(1.0D, 0.0D, 1.0D));

            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    Entity entity = (Entity) list.get(i);

                    if (!entity.dead) {
                        this.i(entity);
                    }
                }
            }
        }
    }

    private void i(Entity entity) {
        entity.b(this);
    }

    public void die(Entity entity) {
        super.die(entity);
        this.b(0.2F, 0.2F);
        this.setPosition(this.locX, this.locY, this.locZ);
        this.motY = 0.10000000149011612D;
        if (this.name.equals("Notch")) {
            this.a(new ItemStack(Item.APPLE, 1), true);
        }

        this.inventory.h();
        if (entity != null) {
            this.motX = (double) (-MathHelper.cos((this.af + this.yaw) * 3.1415927F / 180.0F) * 0.1F);
            this.motZ = (double) (-MathHelper.sin((this.af + this.yaw) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.motX = this.motZ = 0.0D;
        }

        this.height = 0.1F;
    }

    public void c(Entity entity, int i) {
        this.m += i;
    }

    public void F() {
        this.a(this.inventory.splitStack(this.inventory.itemInHandIndex, 1), false);
    }

    public void b(ItemStack itemstack) {
        this.a(itemstack, false);
    }

    public void a(ItemStack itemstack, boolean flag) {
        if (itemstack != null) {
            EntityItem entityitem = new EntityItem(this.world, this.locX, this.locY - 0.30000001192092896D + (double) this.t(), this.locZ, itemstack);

            entityitem.pickupDelay = 40;
            float f = 0.1F;
            float f1;

            if (flag) {
                f1 = this.random.nextFloat() * 0.5F;
                float f2 = this.random.nextFloat() * 3.1415927F * 2.0F;

                entityitem.motX = (double) (-MathHelper.sin(f2) * f1);
                entityitem.motZ = (double) (MathHelper.cos(f2) * f1);
                entityitem.motY = 0.20000000298023224D;
            } else {
                f = 0.3F;
                entityitem.motX = (double) (-MathHelper.sin(this.yaw / 180.0F * 3.1415927F) * MathHelper.cos(this.pitch / 180.0F * 3.1415927F) * f);
                entityitem.motZ = (double) (MathHelper.cos(this.yaw / 180.0F * 3.1415927F) * MathHelper.cos(this.pitch / 180.0F * 3.1415927F) * f);
                entityitem.motY = (double) (-MathHelper.sin(this.pitch / 180.0F * 3.1415927F) * f + 0.1F);
                f = 0.02F;
                f1 = this.random.nextFloat() * 3.1415927F * 2.0F;
                f *= this.random.nextFloat();
                entityitem.motX += Math.cos((double) f1) * (double) f;
                entityitem.motY += (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
                entityitem.motZ += Math.sin((double) f1) * (double) f;
            }

            // CraftBukkit start
            Player player = (Player) this.getBukkitEntity();
            CraftItem drop = new CraftItem(this.world.getServer(), entityitem);

            PlayerDropItemEvent event = new PlayerDropItemEvent(player, drop);
            this.world.getServer().getPluginManager().callEvent(event);

            if (event.isCancelled()) {
                player.getInventory().addItem(drop.getItemStack());
                return;
            }
            // CraftBukkit end

            this.a(entityitem);
        }
    }

    protected void a(EntityItem entityitem) {
        this.world.addEntity(entityitem);
    }

    public float a(Block block) {
        float f = this.inventory.a(block);

        if (this.a(Material.WATER)) {
            f /= 5.0F;
        }

        if (!this.onGround) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(Block block) {
        return this.inventory.b(block);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.l("Inventory");

        this.inventory.b(nbttaglist);
        this.dimension = nbttagcompound.e("Dimension");
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Inventory", (NBTBase) this.inventory.a(new NBTTagList()));
        nbttagcompound.a("Dimension", this.dimension);
    }

    public void a(IInventory iinventory) {}

    public void b(int i, int j, int k) {}

    public void receive(Entity entity, int i) {}

    public float t() {
        return 0.12F;
    }

    protected void s() {
        this.height = 1.62F;
    }

    public boolean damageEntity(Entity entity, int i) {
        this.ay = 0;
        if (this.health <= 0) {
            return false;
        } else {
            if (entity instanceof EntityMonster || entity instanceof EntityArrow) {
                if (this.world.spawnMonsters == 0) {
                    i = 0;
                }

                if (this.world.spawnMonsters == 1) {
                    i = i / 3 + 1;
                }

                if (this.world.spawnMonsters == 3) {
                    i = i * 3 / 2;
                }
            }

            if (i == 0) {
                return false;
            } else {
                Object object = entity;

                if (entity instanceof EntityArrow && ((EntityArrow) entity).shooter != null) {
                    object = ((EntityArrow) entity).shooter;
                }

                if (object instanceof EntityLiving) {
                    // CraftBukkit start - this is here instead of EntityMonster because EntityLiving(s) that aren't monsters
                    // also damage the player in this way. For example, EntitySlime.

                    // We handle projectiles in their individual classes!
                    if (!(entity.getBukkitEntity() instanceof Projectile)) {
                        org.bukkit.entity.Entity damager = ((Entity) object).getBukkitEntity();
                        org.bukkit.entity.Entity damagee = this.getBukkitEntity();

                        EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(damager, damagee, EntityDamageEvent.DamageCause.ENTITY_ATTACK, i);
                        this.world.getServer().getPluginManager().callEvent(event);

                        if (event.isCancelled() || event.getDamage() == 0) {
                            return false;
                        }

                        i = event.getDamage();
                    }
                    // CraftBukkit end
                }

                return super.damageEntity(entity, i);
            }
        }
    }

    protected boolean j_() {
        return false;
    }

    protected void c(int i) {
        int j = 25 - this.inventory.g();
        int k = i * j + this.d;

        this.inventory.c(i);
        i = k / 25;
        this.d = k % 25;
        super.c(i);
    }

    public void a(TileEntityFurnace tileentityfurnace) {}

    public void a(TileEntitySign tileentitysign) {}

    public void c(Entity entity) {
        if (!entity.a(this)) {
            ItemStack itemstack = this.G();

            if (itemstack != null && entity instanceof EntityLiving) {
                itemstack.a((EntityLiving) entity);
                // CraftBukkit - bypass infinite items; <= 0 -> == 0
                if (itemstack.count == 0) {
                    itemstack.a(this);
                    this.H();
                }
            }
        }
    }

    public ItemStack G() {
        return this.inventory.getItemInHand();
    }

    public void H() {
        this.inventory.setItem(this.inventory.itemInHandIndex, (ItemStack) null);
    }

    public double I() {
        return (double) (this.height - 0.5F);
    }

    public void w() {
        this.q = -1;
        this.p = true;
    }

    public void d(Entity entity) {
        int i = this.inventory.a(entity);

        if (i > 0) {
            if (this.motY < 0.0D) {
                ++i;
            }

            // CraftBukkit start - Don't call the event when the entity is human since it will be called with damageEntity
            if (entity instanceof EntityLiving && !(entity instanceof EntityHuman)) {
                org.bukkit.entity.Entity damager = this.getBukkitEntity();
                org.bukkit.entity.Entity damagee = (entity == null) ? null : entity.getBukkitEntity();

                EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(damager, damagee, EntityDamageEvent.DamageCause.ENTITY_ATTACK, i);
                this.world.getServer().getPluginManager().callEvent(event);

                if (event.isCancelled() || event.getDamage() == 0) {
                    return;
                }

                i = event.getDamage();
            }
            // CraftBukkit end

            // CraftBukkit start - Return when the damage fails so that the item will not lose durability
            if (!entity.damageEntity(this, i)) {
                return;
            }
            // CraftBukkit end

            ItemStack itemstack = this.G();

            if (itemstack != null && entity instanceof EntityLiving) {
                itemstack.a((EntityLiving) entity, this);
                // CraftBukkit - bypass infinite items; <= 0 -> == 0
                if (itemstack.count == 0) {
                    itemstack.a(this);
                    this.H();
                }
            }
        }
    }

    public void a(ItemStack itemstack) {}

    public void die() {
        super.die();
        this.defaultContainer.a(this);
        if (this.activeContainer != null) {
            this.activeContainer.a(this);
        }
    }

    public void a(String s) {}

    protected void O() {
        super.O();
    }

    public void a(float f, float f1) {
        double d0 = this.locX;
        double d1 = this.locY;
        double d2 = this.locZ;

        super.a(f, f1);
    }
}
