package net.minecraft.server;

public class ItemSpade extends ItemTool {

    private static Block[] bk = new Block[] { Block.GRASS, Block.DIRT, Block.SAND, Block.GRAVEL, Block.SOIL};

    public ItemSpade(int i, EnumToolMaterial enumtoolmaterial) {
        super(i, 1, enumtoolmaterial, bk);
    }

    public boolean a(Block block) {
        return false;
    }
}
