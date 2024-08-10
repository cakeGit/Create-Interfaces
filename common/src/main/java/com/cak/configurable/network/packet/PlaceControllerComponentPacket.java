package com.cak.configurable.network.packet;

import com.jozufozu.flywheel.util.Pair;
import com.simibubi.create.foundation.networking.SimplePacketBase;
import net.minecraft.network.FriendlyByteBuf;

public class PlaceControllerComponentPacket extends SimplePacketBase {
    
    public PlaceControllerComponentPacket(FriendlyByteBuf byteBuf) {
    
    }
    public PlaceControllerComponentPacket(int sourceSlot, int targetSlot, Pair<Integer, Integer> placePosition) {
    
    }
    
    @Override
    public void write(FriendlyByteBuf buffer) {
    
    }
    
    @Override
    public boolean handle(Context context) {
        return false;
    }
    
}
