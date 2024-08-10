package com.cak.configurable.network.fabric;

import com.cak.configurable.network.PlatformPackets;

public class PlatformPacketsImpl {
    
    public static PlatformPackets.GenericNetworker getChannel() {
        return FabricNetworker.of(FabricPackets.getChannel());
    }
    
    public static void registerPackets() {
        FabricPackets.registerPackets();
    }
    
}
