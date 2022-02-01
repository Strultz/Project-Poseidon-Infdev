package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet0KeepAlive extends Packet {
    public int randomId;

    public Packet0KeepAlive() {}
    
    public Packet0KeepAlive(int var1) {
        this.randomId = var1;
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.randomId = datainputstream.readInt();
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.randomId);
    }

    public int a() {
        return 4;
    }
}
