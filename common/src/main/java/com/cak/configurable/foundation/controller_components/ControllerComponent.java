package com.cak.configurable.foundation.controller_components;

import com.jozufozu.flywheel.util.Pair;
import net.minecraft.nbt.CompoundTag;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ControllerComponent<T extends ComponentAnimator> {
    
    String name;
    Pair<Integer, Integer> gridDimensions;
    ComponentHandler<T> handler;
    Supplier<T> animatorFactory;
    SubMenuFactory subMenuFactory;
    
    public ControllerComponent(String name, Pair<Integer, Integer> gridDimensions, ComponentHandler<T> handler, Supplier<T> animatorFactory, SubMenuFactory subMenuFactory) {
        this.name = name;
        this.gridDimensions = gridDimensions;
        this.handler = handler;
        this.animatorFactory = animatorFactory;
        this.subMenuFactory = subMenuFactory;
    }
    
    public ComponentHandler<T> getHandler() {
        return handler;
    }
    
    public T createAnimator() {
        return animatorFactory.get();
    }
    
    public String getName() {
        return name;
    }
    
    public Pair<Integer, Integer> getGridDimensions() {
        return gridDimensions;
    }
    
    public SubMenuFactory getSubMenuFactory() {
        return subMenuFactory;
    }
    
    public static void forEachComponentOfTag(CompoundTag tag, BiConsumer<ControllerComponent<?>, CompoundTag> componentConsumer) {
        int componentsCount = tag.contains("count") ? tag.getInt("count") : 0;
        if (componentsCount == 0) return;
        
        for (int i = 0; i < componentsCount; i++) {
            CompoundTag componentTag = tag.getCompound("component_" + i);
            ControllerComponent<?> component = ControllerComponents.fromName(componentTag.getString("name"));
            
            componentConsumer.accept(component, componentTag);
        }
        
    }
    
    public static CompoundTag createInitialTag(ControllerComponent<?> component) {
        CompoundTag newTag = new CompoundTag();
        newTag.putString("name", component.getName());
        return component.getHandler().addToInitialTag(newTag);
    }
    
    public static Pair<Integer, Integer> getPositionOfComponent(CompoundTag tag) {
        return Pair.of(tag.getInt("positionX"), tag.getInt("positionY"));
    }
    
}
