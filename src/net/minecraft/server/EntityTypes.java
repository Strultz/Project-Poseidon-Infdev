package net.minecraft.server;

import java.util.HashMap;
import java.util.Map;

public class EntityTypes {

    private static Map a = new HashMap();
    private static Map b = new HashMap();
    private static Map c = new HashMap();
    private static Map d = new HashMap();

    public EntityTypes() {}

    private static void a(Class oclass, String s, int i) {
        a.put(s, oclass);
        b.put(oclass, s);
        c.put(Integer.valueOf(i), oclass);
        d.put(oclass, Integer.valueOf(i));
    }

    public static Entity a(String s, World world) {
        Entity entity = null;

        try {
            Class oclass = (Class) a.get(s);

            if (oclass != null) {
                entity = (Entity) oclass.getConstructor(new Class[] { World.class}).newInstance(new Object[] { world});
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return entity;
    }

    public static Entity a(NBTTagCompound nbttagcompound, World world) {
        Entity entity = null;

        try {
            Class oclass = (Class) a.get(nbttagcompound.getString("id"));

            if (oclass != null) {
                entity = (Entity) oclass.getConstructor(new Class[] { World.class}).newInstance(new Object[] { world});
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (entity != null) {
            entity.e(nbttagcompound);
        } else {
            System.out.println("Skipping Entity with id " + nbttagcompound.getString("id"));
        }

        return entity;
    }

    public static int a(Entity entity) {
        return ((Integer) d.get(entity.getClass())).intValue();
    }

    public static String b(Entity entity) {
        return (String) b.get(entity.getClass());
    }

    static {
        a(EntityArrow.class, "Arrow", 10);
        a(EntityItem.class, "Item", 1);
        a(EntityPainting.class, "Painting", 9);
        a(EntityLiving.class, "Mob", 48);
        a(EntityMonster.class, "Monster", 49);
        a(EntityCreeper.class, "Creeper", 50);
        a(EntitySkeleton.class, "Skeleton", 51);
        a(EntitySpider.class, "Spider", 52);
        a(EntityGiantZombie.class, "Giant", 53);
        a(EntityZombie.class, "Zombie", 54);
        a(EntityPig.class, "Pig", 90);
        a(EntitySheep.class, "Sheep", 91);
        a(EntityTNTPrimed.class, "PrimedTnt", 20);
        a(EntityFallingSand.class, "FallingSand", 21);
        a(EntityMinecart.class, "Minecart", 40);
    }
}
