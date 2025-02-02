package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityCreeper;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Creeper;

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

}
