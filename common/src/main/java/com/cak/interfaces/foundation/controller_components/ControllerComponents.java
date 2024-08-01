package com.cak.interfaces.foundation.controller_components;

import com.cak.interfaces.CIRegistry;
import com.cak.interfaces.foundation.controller_components.animators.ButtonComponentAnimator;
import com.cak.interfaces.foundation.controller_components.handlers.ButtonComponentHandler;
import com.jozufozu.flywheel.util.Pair;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.function.Supplier;

public class ControllerComponents {
    
    public static HashMap<ItemEntry<? extends Item>, ControllerComponent<?>> ALL_COMPONENTS = new HashMap<>();
    public static HashMap<String, ControllerComponent<?>> ALL_COMPONENTS_BY_NAME = new HashMap<>();
    
    public static final ControllerComponent<?> BUTTON_COMPONENT = createComponent("button_component",
        Pair.of(1, 1), new ButtonComponentHandler(), ButtonComponentAnimator::new, CIRegistry.BUTTON_COMPONENT
        );
    
    public static void register() {
        //Load the class
    }
    
    private static <T extends ComponentAnimator> ControllerComponent<?> createComponent(
        String name, Pair<Integer, Integer> gridDimensions,
        ComponentHandler<T> handler, Supplier<T> animatorFactory,
        ItemEntry<? extends Item> item) {
        
        ControllerComponent<?> component = new ControllerComponent<T>(name, gridDimensions, handler, animatorFactory);
        ALL_COMPONENTS.put(item, component);
        ALL_COMPONENTS_BY_NAME.put(name, component);
        
        return component;
    }
    
    public static ControllerComponent<?> fromName(String name) {
        return ALL_COMPONENTS_BY_NAME.get(name);
    }
    
}
