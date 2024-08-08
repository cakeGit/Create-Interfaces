package com.cak.configurable.network.packet;

import com.simibubi.create.foundation.networking.SimplePacketBase;
import net.minecraft.network.FriendlyByteBuf;

public class PlaceControllerComponentPacket extends SimplePacketBase {
    
    @Override
    public void write(FriendlyByteBuf buffer) {
    
    }
    
    @Override
    public boolean handle(Context context) {
        return false;
    }
    
}
