package net.minecraft.server;

import java.io.File;
import java.util.List;

public class ServerNBTManager extends PlayerNBTManager {

    public ServerNBTManager(File file1, String s, boolean flag) {
        super(file1, s, flag);
    }

    public IChunkLoader a(WorldProvider worldprovider) {
        File file1 = this.a();

        return new ChunkRegionLoader(file1);
    }

    public void a(WorldData worlddata, List list) {
        worlddata.a(19132);
        super.a(worlddata, list);
    }

    public void e() {
        RegionFileCache.a();
    }
}
