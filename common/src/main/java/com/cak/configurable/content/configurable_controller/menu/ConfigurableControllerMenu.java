package com.cak.configurable.content.configurable_controller.menu;

import com.cak.configurable.content.configurable_controller.ConfigurableControllerItem;
import com.cak.configurable.foundation.controller_components.ControllerComponent;
import com.jozufozu.flywheel.util.Pair;
import com.simibubi.create.foundation.gui.menu.MenuBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;

public class ConfigurableControllerMenu extends MenuBase<ItemStack> {
    
    public ArrayList<PlacedComponentSubMenu> components = new ArrayList<>();
    
    public ConfigurableControllerMenu(MenuType<?> type, int id, Inventory inv, FriendlyByteBuf extraData) {
        super(type, id, inv, extraData);
    }
    
    public ConfigurableControllerMenu(MenuType<?> type, int id, Inventory inv, ItemStack controllerStack) {
        super(type, id, inv, controllerStack);
    }
    
    @Override
    protected ItemStack createOnClient(FriendlyByteBuf extraData) {
        return extraData.readItem();
    }
    
    @Override
    protected void initAndReadInventory(ItemStack contentHolder) {
        if (!(contentHolder.getItem() instanceof ConfigurableControllerItem controllerItem)) throw new AssertionError("Opened a configurable controller menu on a non controller item");
        
        ControllerComponent.forEachComponentOfTag(contentHolder.getOrCreateTag().getCompound("controller_components"), (component, tag) -> {
            components.add(component.getSubMenuFactory().apply(
                ControllerComponent.getPositionOfComponent(tag),
                component, tag, this
            ));
        });
    }
    
    @Override
    protected void addSlots() {
        for (int hotbarSlot = 0; hotbarSlot < 9; ++hotbarSlot)
            this.addSlot(new Slot(playerInventory, hotbarSlot, 24 + hotbarSlot * 18, 200));
    }
    
    public void placeComponent(Pair<Integer, Integer> position, ControllerComponent<?> component) {
        CompoundTag tag = ControllerComponent.createInitialTag(component);
        components.add(component.getSubMenuFactory().apply(position, component, tag, this));
        saveData(contentHolder);
    }
    
    @Override
    protected void saveData(ItemStack contentHolder) {
        CompoundTag tag = new CompoundTag();
        ControllerComponent.writePlacedComponentsToTag(components, tag);
        contentHolder.getOrCreateTag().put("controller_components", tag);
        
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return player.getSlot(index).get();
    }
    
}
