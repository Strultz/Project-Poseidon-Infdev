package net.minecraft.server;

import java.util.Random;

public class BlockGears extends Block {
	protected BlockGears() {
        super(55, 62, Material.ORIENTABLE);
        this.a(true);
    }
	
	@Override
    public AxisAlignedBB e(World world, final int n, final int n2, final int n3) {
        return null;
    }
    
    @Override
    public final boolean a() {
        return false;
    }
    
    @Override
    public final int a(final Random random) {
        return 1;
    }
    
    @Override
    public final boolean k_() {
        return false;
    }
}
