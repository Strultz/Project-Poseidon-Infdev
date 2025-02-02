package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityMinecart;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

public class CraftMinecart extends CraftVehicle implements Minecart {
    protected EntityMinecart minecart;
    protected CraftInventory inventory;

    public CraftMinecart(CraftServer server, EntityMinecart entity) {
        super(server, entity);
        minecart = entity;
        inventory = new CraftInventory(entity);
    }

    public Inventory getInventory() {
        return inventory;
    }
    
    public void setDamage(int damage) {
        minecart.setDamage(damage);
    }

    public int getDamage() {
        return minecart.getDamage();
    }

    public double getMaxSpeed() {
        return minecart.maxSpeed;
    }

    public void setMaxSpeed(double speed) {
        if (speed >= 0D) {
            minecart.maxSpeed = speed;
        }
    }

    public boolean isSlowWhenEmpty() {
        return minecart.slowWhenEmpty;
    }

    public void setSlowWhenEmpty(boolean slow) {
        minecart.slowWhenEmpty = slow;
    }

    public Vector getFlyingVelocityMod() {
        return new Vector(minecart.flyingX, minecart.flyingY, minecart.flyingZ);
    }

    public void setFlyingVelocityMod(Vector flying) {
        minecart.flyingX = flying.getX();
        minecart.flyingY = flying.getY();
        minecart.flyingZ = flying.getZ();
    }

    public Vector getDerailedVelocityMod() {
        return new Vector(minecart.derailedX, minecart.derailedY, minecart.derailedZ);
    }

    public void setDerailedVelocityMod(Vector derailed) {
        minecart.derailedX = derailed.getX();
        minecart.derailedY = derailed.getY();
        minecart.derailedZ = derailed.getZ();
    }

    @Override
    public String toString() {
        return "CraftMinecart{" + "inventory=" + inventory + '}';
    }

}
