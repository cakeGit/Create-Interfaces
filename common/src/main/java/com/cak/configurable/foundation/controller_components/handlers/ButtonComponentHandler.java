package com.cak.configurable.foundation.controller_components.handlers;

import com.cak.configurable.foundation.controller_components.ComponentHandler;
import com.cak.configurable.foundation.controller_components.animators.ButtonComponentAnimator;
import net.minecraft.client.KeyMapping;
import net.minecraft.nbt.CompoundTag;

import java.util.List;

public class ButtonComponentHandler extends ComponentHandler<ButtonComponentAnimator> {
    
    @Override
    public void onKeyPressed(CompoundTag tag, KeyMapping mapping, ButtonComponentAnimator animator) {
    
    }
    
    @Override
    public List<KeyMapping> getReservedKeys(CompoundTag tag) {
        return List.of();
    }
    
    @Override
    public CompoundTag addToInitialTag(CompoundTag newTag) {
        return newTag;
    }
    
}
