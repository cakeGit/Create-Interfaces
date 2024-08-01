package com.cak.interfaces.content.configurable_controller.menu;

import com.simibubi.create.foundation.gui.menu.MenuBase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class ConfigurableControllerMenu extends MenuBase<ItemStack> {
    
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
    
    }
    
    @Override
    protected void addSlots() {
    
    }
    
    @Override
    protected void saveData(ItemStack contentHolder) {
    
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return player.getSlot(index).get();
    }
    
}
