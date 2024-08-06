package com.cak.configurable.foundation.controller_components;

import com.cak.configurable.OMRegistry;
import com.cak.configurable.content.configurable_controller.menu.PlacedComponentSubMenu;
import com.cak.configurable.foundation.controller_components.animators.ButtonComponentAnimator;
import com.cak.configurable.foundation.controller_components.handlers.ButtonComponentHandler;
import com.cak.configurable.foundation.controller_components.submenu_handlers.ButtonComponentSubMenu;
import com.jozufozu.flywheel.util.Pair;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ControllerComponents {
    
    private static final HashMap<ItemEntry<? extends Item>, ControllerComponent<?>> ALL_COMPONENTS = new HashMap<>();
    private static HashMap<Item, ControllerComponent<?>> ALL_COMPONENTS_BY_ITEM = null;
    private static final HashMap<String, ControllerComponent<?>> ALL_COMPONENTS_BY_NAME = new HashMap<>();
    
    public static final ControllerComponent<?> BUTTON_COMPONENT = createComponent("button_component",
        Pair.of(1, 1), new ButtonComponentHandler(), ButtonComponentAnimator::new, ButtonComponentSubMenu::new, OMRegistry.BUTTON_COMPONENT
        );
    
    public static void register() {
        //Load the class
    }
    
    private static <T extends ComponentAnimator> ControllerComponent<?> createComponent(
        String name, Pair<Integer, Integer> gridDimensions,
        ComponentHandler<T> handler, Supplier<T> animatorFactory, SubMenuFactory subMenuFactory,
        ItemEntry<? extends Item> item) {
        
        ControllerComponent<?> component = new ControllerComponent<T>(name, gridDimensions, handler, animatorFactory, subMenuFactory);
        
        ALL_COMPONENTS.put(item, component);
        ALL_COMPONENTS_BY_NAME.put(name, component);
        
        return component;
    }
    
    public static ControllerComponent<?> fromItem(Item item) {
        if (ALL_COMPONENTS_BY_ITEM == null) {
            ALL_COMPONENTS_BY_ITEM = HashMap.newHashMap(ALL_COMPONENTS.size());
            for (Map.Entry<ItemEntry<? extends Item>, ControllerComponent<?>> entry : ALL_COMPONENTS.entrySet()) {
                Item itemEntry = entry.getKey().get();
                ALL_COMPONENTS_BY_ITEM.put(itemEntry, entry.getValue());
            }
        }
        return ALL_COMPONENTS_BY_ITEM.get(item);
    }
    
    public static ControllerComponent<?> fromName(String name) {
        return ALL_COMPONENTS_BY_NAME.get(name);
    }
    
}
