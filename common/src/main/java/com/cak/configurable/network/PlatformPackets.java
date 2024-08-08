package com.cak.configurable.network;

import com.simibubi.create.foundation.networking.SimplePacketBase;
import dev.architectury.injectables.annotations.ExpectPlatform;

public class PlatformPackets {
    
    @ExpectPlatform
    public static GenericNetworker getChannel() {
        throw new AssertionError();
    }
    
    @ExpectPlatform
    public static void registerPackets() {
        throw new AssertionError();
    }
    
    public interface GenericNetworker {
        
        void initServerListener();
        void initClientListener();
        void sendToServer(SimplePacketBase packet);
        
    }
    
}
