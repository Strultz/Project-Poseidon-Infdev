package net.minecraft.server;

public class EntityPig extends EntityAnimal {

    public EntityPig(World world) {
        super(world);
        this.texture = "/mob/pig.png";
        this.b(0.9F, 0.9F);
    }

    protected String g() {
        return "mob.pig";
    }

    protected String h() {
        return "mob.pig";
    }

    protected String i() {
        return "mob.pigdeath";
    }

    protected int j() {
        return Item.PORK.id;
    }
}
