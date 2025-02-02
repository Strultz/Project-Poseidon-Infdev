package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityPig;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Pig;

public class CraftPig extends CraftAnimals implements Pig {
    public CraftPig(CraftServer server, EntityPig entity) {
        super(server, entity);
    }

    public EntityPig getHandle() {
        return (EntityPig) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftPig";
    }
}
