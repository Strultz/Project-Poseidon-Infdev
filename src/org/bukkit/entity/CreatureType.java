package org.bukkit.entity;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CreatureType {
    CREEPER("Creeper"),
    GIANT("Giant"),
    MOB("Mob"),
    MONSTER("Monster"),
    PIG("Pig"),
    SHEEP("Sheep"),
    SKELETON("Skeleton"),
    SPIDER("Spider"),
    ZOMBIE("Zombie");

    private String name;

    private static final Map<String, CreatureType> mapping = new HashMap<String, CreatureType>();

    static {
        for (CreatureType type : EnumSet.allOf(CreatureType.class)) {
            mapping.put(type.name, type);
        }
    }

    private CreatureType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CreatureType fromName(String name) {
        return mapping.get(name);
    }
}
