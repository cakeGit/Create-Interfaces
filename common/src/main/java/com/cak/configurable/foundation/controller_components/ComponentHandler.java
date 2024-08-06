package com.cak.configurable.foundation.controller_components;

import net.minecraft.client.KeyMapping;
import net.minecraft.nbt.CompoundTag;

import java.util.List;

public abstract class ComponentHandler<T extends ComponentAnimator> {
    
    abstract public void onKeyPressed(CompoundTag tag, KeyMapping mapping, T animator);
    
    abstract public List<KeyMapping> getReservedKeys(CompoundTag tag);
    
    abstract public CompoundTag addToInitialTag(CompoundTag newTag);
    
}
