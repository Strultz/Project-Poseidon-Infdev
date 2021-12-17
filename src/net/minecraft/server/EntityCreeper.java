package net.minecraft.server;

// CraftBukkit start

import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.event.entity.ExplosionPrimeEvent;
// CraftBukkit end

public class EntityCreeper extends EntityMonster {

    int fuseTicks;
    int b;

    public EntityCreeper(World world) {
        super(world);
        this.texture = "/mob/creeper.png";
    }

    protected void b() {
        super.b();
        this.datawatcher.a(16, Byte.valueOf((byte) -1));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
    }

    protected void b(Entity entity, float f) {
        if (!this.world.isStatic) {
            if (this.fuseTicks > 0) {
                this.e(-1);
                --this.fuseTicks;
                if (this.fuseTicks < 0) {
                    this.fuseTicks = 0;
                }
            }
        }
    }

    public void m_() {
        this.b = this.fuseTicks;
        if (this.world.isStatic) {
            int i = this.x();

            if (i > 0 && this.fuseTicks == 0) {
                this.world.makeSound(this, "random.fuse", 1.0F, 0.5F);
            }

            this.fuseTicks += i;
            if (this.fuseTicks < 0) {
                this.fuseTicks = 0;
            }

            if (this.fuseTicks >= 30) {
                this.fuseTicks = 30;
            }
        }

        super.m_();
        if (this.target == null && this.fuseTicks > 0) {
            this.e(-1);
            --this.fuseTicks;
            if (this.fuseTicks < 0) {
                this.fuseTicks = 0;
            }
        }
    }

    protected String h() {
        return "mob.creeper";
    }

    protected String i() {
        return "mob.creeperdeath";
    }

    public void die(Entity entity) {
        super.die(entity);
    }

    protected void a(Entity entity, float f) {
        if (!this.world.isStatic) {
            int i = this.x();

            if ((i > 0 || f >= 3.0F) && (i <= 0 || f >= 7.0F)) {
                this.e(-1);
                --this.fuseTicks;
                if (this.fuseTicks < 0) {
                    this.fuseTicks = 0;
                }
            } else {
                if (this.fuseTicks == 0) {
                    this.world.makeSound(this, "random.fuse", 1.0F, 0.5F);
                }

                this.e(1);
                ++this.fuseTicks;
                if (this.fuseTicks >= 30) {
                    // CraftBukkit start
                    float radius = 3.0F;

                    ExplosionPrimeEvent event = new ExplosionPrimeEvent(CraftEntity.getEntity(this.world.getServer(), this), radius, false);
                    this.world.getServer().getPluginManager().callEvent(event);

                    if (!event.isCancelled()) {
                        this.world.createExplosion(this, this.locX, this.locY, this.locZ, event.getRadius(), event.getFire());
                        this.die();
                    } else {
                        this.fuseTicks = 0;
                    }
                    // CraftBukkit end
                }

                this.e = true;
            }
        }
    }

    protected int j() {
        return Item.SULPHUR.id;
    }

    private int x() {
        return this.datawatcher.a(16);
    }

    private void e(int i) {
        this.datawatcher.watch(16, Byte.valueOf((byte) i));
    }
}
