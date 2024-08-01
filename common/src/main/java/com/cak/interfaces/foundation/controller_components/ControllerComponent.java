package com.cak.interfaces.foundation.controller_components;

import com.jozufozu.flywheel.util.Pair;
import net.minecraft.nbt.Tag;

import java.util.function.Supplier;

public class ControllerComponent<T extends ComponentAnimator> {
    
    String name;
    
    Pair<Integer, Integer> gridDimensions;
    
    ComponentHandler<T> handler;
    
    Supplier<T> animatorFactory;
    
    public ControllerComponent(String name, Pair<Integer, Integer> gridDimensions, ComponentHandler<T> handler, Supplier<T> animatorFactory) {
        this.name = name;
        this.gridDimensions = gridDimensions;
        this.handler = handler;
        this.animatorFactory = animatorFactory;
    }
    
    public ComponentHandler<T> getHandler() {
        return handler;
    }
    
    public T createAnimator() {
        return animatorFactory.get();
    }
    
}
