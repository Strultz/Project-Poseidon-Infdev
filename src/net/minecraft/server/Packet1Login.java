package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet1Login extends Packet {

    public int a;
    public String name;
    public long c;
	public byte loginFlag; // originally dimension but there are no dimensions in Infdev

    public Packet1Login() {}

    public Packet1Login(String s, int i, long j) {
        this.name = s;
        this.a = i;
        this.c = j;
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = datainputstream.readInt();
        this.name = a(datainputstream, 16);
        this.c = datainputstream.readLong();
		this.loginFlag = datainputstream.readByte();
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.a);
        a(this.name, dataoutputstream);
        dataoutputstream.writeLong(this.c);
		dataoutputstream.writeByte(this.loginFlag);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 4 + this.name.length() + 4 + 5;
    }
}