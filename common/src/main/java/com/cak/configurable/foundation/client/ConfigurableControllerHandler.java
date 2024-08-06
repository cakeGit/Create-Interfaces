package com.cak.configurable.foundation.client;

import com.cak.configurable.CreateConfigurable;
import com.cak.configurable.OMRegistry;
import com.cak.configurable.foundation.controller_components.ComponentAnimator;
import com.cak.configurable.foundation.controller_components.ComponentHandler;
import com.cak.configurable.foundation.controller_components.ControllerComponent;
import com.cak.configurable.foundation.controller_components.ControllerComponents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
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
        
        if (currentPlayerStack.is(OMRegistry.CONFIGURABLE_CONTROLLER.get())) {
            connect(currentPlayerStack);
        }
    }
    
    public void connect(ItemStack stack) {
        CreateConfigurable.LOGGER.info("Connected");
        currentStack = stack;
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("controller_components")) {
            ControllerComponent.forEachComponentOfTag(tag.getCompound("controller_components"), this::createActiveComponent);
        }
    }
    
    private <T extends ComponentAnimator> void createActiveComponent(ControllerComponent<T> component, CompoundTag tag) {
        components.add(new ActiveComponent<>(
            tag, component.getHandler(), component.createAnimator()
        ));
    }
    
    
    public void disconnect() {
        CreateConfigurable.LOGGER.info("Disconnected");
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
