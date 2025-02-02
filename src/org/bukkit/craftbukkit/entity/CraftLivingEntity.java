package org.bukkit.craftbukkit.entity;

import net.minecraft.server.Entity;
import net.minecraft.server.*;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.*;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class CraftLivingEntity extends CraftEntity implements LivingEntity {
    public CraftLivingEntity(final CraftServer server, final EntityLiving entity) {
        super(server, entity);
    }

    public int getHealth() {
        return getHandle().health;
    }

    public void setHealth(int health) {
        if ((health < 0) || (health > 200)) {
            throw new IllegalArgumentException("Health must be between 0 and 200");
        }

        if (entity instanceof EntityPlayer && health == 0) {
            ((EntityPlayer) entity).die((Entity) null);
        }

        getHandle().health = health;
    }

    @Override
    public EntityLiving getHandle() {
        return (EntityLiving) entity;
    }

    public void setHandle(final EntityLiving entity) {
        super.setHandle((Entity) entity);
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "CraftLivingEntity{" + "id=" + getEntityId() + '}';
    }

    public double getEyeHeight() {
        return 1.0D;
    }

    private List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance, int maxLength) {
        if (maxDistance > 120) {
            maxDistance = 120;
        }
        ArrayList<Block> blocks = new ArrayList<Block>();
        Iterator<Block> itr = new BlockIterator(this, maxDistance);
        while (itr.hasNext()) {
            Block block = itr.next();
            blocks.add(block);
            if (maxLength != 0 && blocks.size() > maxLength) {
                blocks.remove(0);
            }
            int id = block.getTypeId();
            if (transparent == null) {
                if (id != 0) {
                    break;
                }
            } else {
                if (!transparent.contains((byte) id)) {
                    break;
                }
            }
        }
        return blocks;
    }

    public List<Block> getLineOfSight(HashSet<Byte> transparent, int maxDistance) {
        return getLineOfSight(transparent, maxDistance, 0);
    }

    public Block getTargetBlock(HashSet<Byte> transparent, int maxDistance) {
        List<Block> blocks = getLineOfSight(transparent, maxDistance, 1);
        return blocks.get(0);
    }

    public List<Block> getLastTwoTargetBlocks(HashSet<Byte> transparent, int maxDistance) {
        return getLineOfSight(transparent, maxDistance, 2);
    }

    public Arrow shootArrow() {
        net.minecraft.server.World world = ((CraftWorld) getWorld()).getHandle();
        EntityArrow arrow = new EntityArrow(world, getHandle());

        world.addEntity(arrow);
        return (Arrow) arrow.getBukkitEntity();
    }

    public int getRemainingAir() {
        return getHandle().airTicks;
    }

    public void setRemainingAir(int ticks) {
        getHandle().airTicks = ticks;
    }

    public int getMaximumAir() {
        return getHandle().maxAirTicks;
    }

    public void setMaximumAir(int ticks) {
        getHandle().maxAirTicks = ticks;
    }

    public void damage(int amount) {
        entity.damageEntity((Entity) null, amount);
    }

    public void damage(int amount, org.bukkit.entity.Entity source) {
        entity.damageEntity(((CraftEntity) source).getHandle(), amount);
    }

    public Location getEyeLocation() {
        Location loc = getLocation();
        loc.setY(loc.getY() + getEyeHeight());
        return loc;
    }

    public int getMaximumNoDamageTicks() {
        return getHandle().maxNoDamageTicks;
    }

    public void setMaximumNoDamageTicks(int ticks) {
        getHandle().maxNoDamageTicks = ticks;
    }

    public int getLastDamage() {
        return getHandle().lastDamage;
    }

    public void setLastDamage(int damage) {
        getHandle().lastDamage = damage;
    }

    public int getNoDamageTicks() {
        return getHandle().noDamageTicks;
    }

    public void setNoDamageTicks(int ticks) {
        getHandle().noDamageTicks = ticks;
    }
}
