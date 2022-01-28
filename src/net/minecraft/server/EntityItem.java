package net.minecraft.server;

import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class EntityItem extends Entity {

    public ItemStack itemStack;
    private int e;
    public int b = 0;
    public int pickupDelay;
    private int f = 5;
    public float d = (float) (Math.random() * 3.141592653589793D * 2.0D);
    private int lastTick = (int) (System.currentTimeMillis() / 50); // CraftBukkit

    public EntityItem(World world, double d0, double d1, double d2, ItemStack itemstack) {
        super(world);
        this.b(0.25F, 0.25F);
        this.height = this.width / 2.0F;
        this.setPosition(d0, d1, d2);
        this.itemStack = itemstack;
        // CraftBukkit start - infinite item fix
        if (this.itemStack.count <= -1) {
            this.itemStack.count = 1;
        }
        // CraftBukkit end
        this.yaw = (float) (Math.random() * 360.0D);
        this.motX = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.motY = 0.20000000298023224D;
        this.motZ = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
    }

    protected boolean n() {
        return false;
    }

    public EntityItem(World world) {
        super(world);
        this.b(0.25F, 0.25F);
        this.height = this.width / 2.0F;
    }

    protected void b() {}

    public void m_() {
        super.m_();
        // CraftBukkit start
        int currentTick = (int) (System.currentTimeMillis() / 50);
        this.pickupDelay -= (currentTick - this.lastTick);
        this.lastTick = currentTick;
        // CraftBukkit end

        this.lastX = this.locX;
        this.lastY = this.locY;
        this.lastZ = this.locZ;
        this.motY -= 0.03999999910593033D;
        if (this.world.getMaterial(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)) == Material.LAVA) {
            this.motY = 0.20000000298023224D;
            this.motX = (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            this.motZ = (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            this.world.makeSound(this, "random.fizz", 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
        }
        
        double var6 = this.locZ;
        double var4 = this.locY;
        double var2 = this.locX;
        int var8 = MathHelper.floor(var2);
        int var9 = MathHelper.floor(var4);
        int var10 = MathHelper.floor(var6);
        double var11 = var2 - (double)var8;
        double var13 = var4 - (double)var9;
        double var15 = var6 - (double)var10;
        if(Block.o[this.world.getTypeId(var8, var9, var10)]) {
            boolean var26 = !Block.o[this.world.getTypeId(var8 - 1, var9, var10)];
            boolean var3 = !Block.o[this.world.getTypeId(var8 + 1, var9, var10)];
            boolean var28 = !Block.o[this.world.getTypeId(var8, var9 - 1, var10)];
            boolean var5 = !Block.o[this.world.getTypeId(var8, var9 + 1, var10)];
            boolean var29 = !Block.o[this.world.getTypeId(var8, var9, var10 - 1)];
            boolean var7 = !Block.o[this.world.getTypeId(var8, var9, var10 + 1)];
            byte var30 = -1;
            double var24 = 9999.0D;
            if(var26 && var11 < 9999.0D) {
                var24 = var11;
                var30 = 0;
            }

            if(var3 && 1.0D - var11 < var24) {
                var24 = 1.0D - var11;
                var30 = 1;
            }

            if(var28 && var13 < var24) {
                var24 = var13;
                var30 = 2;
            }

            if(var5 && 1.0D - var13 < var24) {
                var24 = 1.0D - var13;
                var30 = 3;
            }

            if(var29 && var15 < var24) {
                var24 = var15;
                var30 = 4;
            }

            if(var7 && 1.0D - var15 < var24) {
                var30 = 5;
            }

            float var27 = this.random.nextFloat() * 0.2F + 0.1F;
            if(var30 == 0) {
                this.motX = (double)(-var27);
            }

            if(var30 == 1) {
                this.motX = (double)var27;
            }

            if(var30 == 2) {
                this.motY = (double)(-var27);
            }

            if(var30 == 3) {
                this.motY = (double)var27;
            }

            if(var30 == 4) {
                this.motZ = (double)(-var27);
            }

            if(var30 == 5) {
                this.motZ = (double)var27;
            }
        }

        this.move(this.motX, this.motY, this.motZ);
        this.motX *= 0.9800000190734863D;
        this.motY *= 0.9800000190734863D;
        this.motZ *= 0.9800000190734863D;
        if(this.onGround) {
            this.motX *= 0.699999988079071D;
            this.motZ *= 0.699999988079071D;
            this.motY *= -0.5D;
        }
        
        ++this.e;
        ++this.b;
        if (this.b >= 6000) {
            //Project Poseidon Start
            if(CraftEventFactory.callItemDespawnEvent(this).isCancelled()) {
                this.b = 0;
                return;
            }
            // CraftBukkit end
            this.die();
        }
    }

    public boolean f_() {
        return this.world.a(this.boundingBox, Material.WATER, this);
    }

    protected void burn(int i) {
        this.damageEntity((Entity) null, i);
    }

    public boolean damageEntity(Entity entity, int i) {
        this.af();
        this.f -= i;
        if (this.f <= 0) {
            this.die();
        }

        return false;
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Health", (short) ((byte) this.f));
        nbttagcompound.a("Age", (short) this.b);
        nbttagcompound.a("Item", this.itemStack.a(new NBTTagCompound()));
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.f = nbttagcompound.d("Health") & 255;
        this.b = nbttagcompound.d("Age");
        NBTTagCompound nbttagcompound1 = nbttagcompound.k("Item");

        this.itemStack = new ItemStack(nbttagcompound1);
    }

    public void b(EntityHuman entityhuman) {
        if (!this.world.isStatic) {
            int i = this.itemStack.count;

            // CraftBukkit start
            int canHold = entityhuman.inventory.canHold(this.itemStack);
            int remaining = this.itemStack.count - canHold;
            if (this.pickupDelay <= 0 && canHold > 0) {
                this.itemStack.count = canHold;
                PlayerPickupItemEvent event = new PlayerPickupItemEvent((org.bukkit.entity.Player) entityhuman.getBukkitEntity(), (org.bukkit.entity.Item) this.getBukkitEntity(), remaining);
                this.world.getServer().getPluginManager().callEvent(event);
                this.itemStack.count = canHold + remaining;

                if (event.isCancelled()) {
                    return;
                }

                // Possibly < 0; fix here so we do not have to modify code below
                this.pickupDelay = 0;
            }
            // CraftBukkit end

            if (this.pickupDelay == 0 && entityhuman.inventory.pickup(this.itemStack)) {
                this.world.makeSound(this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityhuman.receive(this, i);
                if (this.itemStack.count <= 0) {
                    this.die();
                }
            }
        }
    }
}
