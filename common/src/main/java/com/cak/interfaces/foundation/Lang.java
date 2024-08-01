package com.cak.interfaces.foundation;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class Lang {
    
    public static MutableComponent translateGui(String key) {
        return Component.translatable("gui.create_interface." + key);
    }
    
}
