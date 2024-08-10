package com.cak.configurable.network.fabric;

import com.cak.configurable.network.PlatformPackets;
import com.simibubi.create.foundation.networking.SimplePacketBase;
import me.pepperbell.simplenetworking.SimpleChannel;

public class FabricNetworker implements PlatformPackets.GenericNetworker {
    
    SimpleChannel channel;
    
    private FabricNetworker(SimpleChannel channel) {
        this.channel = channel;
    }
    
    public static PlatformPackets.GenericNetworker of(SimpleChannel channel) {
        return new FabricNetworker(channel);
    }
    
    @Override
    public void initServerListener() {
        channel.initServerListener();
    }
    
    @Override
    public void initClientListener() {
        channel.initClientListener();
    }
    
    @Override
    public void sendToServer(SimplePacketBase packet) {
        channel.sendToServer(packet);
    }
    
}
