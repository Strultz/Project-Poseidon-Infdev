package net.minecraft.server;

public class SlotResult extends Slot {

    private final IInventory d;
    private EntityHuman e;

    public SlotResult(EntityHuman entityhuman, IInventory iinventory, IInventory iinventory1, int i, int j, int k) {
        super(iinventory1, i, j, k);
        this.e = entityhuman;
        this.d = iinventory;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    public void a(ItemStack itemstack) {
        itemstack.b(this.e.world, this.e);

        for (int i = 0; i < this.d.getSize(); ++i) {
            ItemStack itemstack1 = this.d.getItem(i);

            if (itemstack1 != null) {
                this.d.splitStack(i, 1);
                if (itemstack1.getItem().i()) {
                    this.d.setItem(i, new ItemStack(itemstack1.getItem().h()));
                }
            }
        }
    }
}
