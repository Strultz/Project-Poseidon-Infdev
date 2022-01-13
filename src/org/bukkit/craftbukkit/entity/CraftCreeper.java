package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityCreeper;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Creeper;
import org.bukkit.event.entity.CreeperPowerEvent;

public class CraftCreeper extends CraftMonster implements Creeper {

    public CraftCreeper(CraftServer server, EntityCreeper entity) {
        super(server, entity);
    }

    @Override
    public EntityCreeper getHandle() {
        return (EntityCreeper) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftCreeper";
    }

    public boolean isPowered() {
        return getHandle().isPowered();
    }

    public void setPowered(boolean powered) {
        // CraftBukkit start
        CraftServer server = this.server;
        org.bukkit.entity.Entity entity = this.getHandle().getBukkitEntity();

        if (powered) {
            CreeperPowerEvent event = new CreeperPowerEvent(entity, CreeperPowerEvent.PowerCause.SET_ON);
            server.getPluginManager().callEvent(event);

            if (!event.isCancelled()) {
                getHandle().setPowered(true);
            }
        } else {
            CreeperPowerEvent event = new CreeperPowerEvent(entity, CreeperPowerEvent.PowerCause.SET_OFF);
            server.getPluginManager().callEvent(event);

            if (!event.isCancelled()) {
                getHandle().setPowered(false);
            }
        }

        // CraftBukkit end

    }

}
