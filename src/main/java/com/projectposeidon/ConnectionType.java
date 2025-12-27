package com.projectposeidon;

public enum ConnectionType {
    NORMAL(false),
    RELEASE2BETA(false),
    RELEASE2BETA_ONLINE_MODE_IP_FORWARDING(true),
    RELEASE2BETA_OFFLINE_MODE_IP_FORWARDING(true),
    BUNGEECORD(false),                          // These 2 aren't used but I'm
    BUNGEECORD_ONLINE_MODE_IP_FORWARDING(true), // leaving them in just in case
    BUNGEECORD_OFFLINE_MODE_IP_FORWARDING(true),
    UNKNOWN(false);

    private final boolean ipForwarding;

    private ConnectionType(boolean ipForwarding) {
        this.ipForwarding = ipForwarding;
    }

    public boolean usesIpForwarding() {
        return this.ipForwarding;
    }

    public static ConnectionType getConnectionType(byte id) {
        switch (id) {
        case 0:  return NORMAL;
        case 1:  return RELEASE2BETA;
        case 25: return RELEASE2BETA_OFFLINE_MODE_IP_FORWARDING;
        case 26: return RELEASE2BETA_ONLINE_MODE_IP_FORWARDING;
        case 2:  return BUNGEECORD_OFFLINE_MODE_IP_FORWARDING;
        default: return UNKNOWN;
        }
    }
}
