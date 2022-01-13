package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet9Respawn extends Packet {

    public Packet9Respawn() {}
    
    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public void a(DataInputStream datainputstream) throws IOException {
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
    }

    public int a() {
        return 0;
    }
}
