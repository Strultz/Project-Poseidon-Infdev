package net.minecraft.server;

import java.util.Random;

public class BlockGears extends Block {
	protected BlockGears() {
        super(55, 62, Material.ORIENTABLE);
        this.a(true);
    }
	
    public AxisAlignedBB e(World world, final int n, final int n2, final int n3) {
        return null;
    }
    
	public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }
    
    public final int a(final Random random) {
        return 1;
    }
    
    public final boolean k_() {
        return false;
    }
}
