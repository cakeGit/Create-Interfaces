package com.cak.interfaces.foundation.client;

import com.cak.interfaces.CIRegistry;
import com.cak.interfaces.CreateInterfaces;
import com.cak.interfaces.foundation.controller_components.ComponentAnimator;
import com.cak.interfaces.foundation.controller_components.ComponentHandler;
import com.cak.interfaces.foundation.controller_components.ControllerComponent;
import com.cak.interfaces.foundation.controller_components.ControllerComponents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ConfigurableControllerHandler {

    @Nullable
    ItemStack currentStack = null;
    ArrayList<ActiveComponent<?>> components = new ArrayList<>();
    boolean using = false;
    
    public void tickClient() {
        LocalPlayer player = Minecraft.getInstance().player;
        
        if (player == null) return;
        
        ItemStack currentPlayerStack = player.getItemInHand(player.getUsedItemHand());
        if (currentPlayerStack != currentStack)
            disconnect();
        
        if (currentPlayerStack.is(CIRegistry.CONFIGURABLE_CONTROLLER.get())) {
            connect(currentPlayerStack);
        }
    }
    
    public void connect(ItemStack stack) {
        CreateInterfaces.LOGGER.info("Connected");
        currentStack = stack;
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("controller_components")) {
            loadComponents(tag.getCompound("controller_components"));
        }
    }
    
    private void loadComponents(CompoundTag componentsData) {
        int componentsCount = componentsData.contains("count") ? componentsData.getInt("count") : 0;
        if (componentsCount == 0) return;
        
        for (int i = 0; i < componentsCount; i++) {
            CompoundTag componentTag = componentsData.getCompound("component_" + i);
            ControllerComponent<?> component = ControllerComponents.fromName(componentTag.getString("name"));
            
            createActiveComponent(component, componentTag);
        }
    }
    
    private <T extends ComponentAnimator> void createActiveComponent(ControllerComponent<T> component, CompoundTag tag) {
        components.add(new ActiveComponent<T>(
            tag, component.getHandler(), component.createAnimator()
        ));
    }
    
    
    public void disconnect() {
        CreateInterfaces.LOGGER.info("Disconnected");
        currentStack = null;
        components = new ArrayList<>();
    }
    
    public boolean isUsing() {
        return using;
    }
    
    public void toggleUsing() {
        using = !using;
    }
    
    private static class ActiveComponent<T extends ComponentAnimator> {
        
        CompoundTag tag;
        ComponentHandler<T> handler;
        T animator;
        
        public ActiveComponent(CompoundTag tag, ComponentHandler<T> handler, T animator) {
            this.tag = tag;
            this.handler = handler;
            this.animator = animator;
        }
        
        public void onKeyPressed(KeyMapping mapping) {
            handler.onKeyPressed(tag, mapping, animator);
        }
        
    }
    
}
