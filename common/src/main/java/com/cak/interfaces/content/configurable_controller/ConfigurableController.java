package com.cak.interfaces.content.configurable_controller;

import com.cak.interfaces.CIRegistry;
import com.cak.interfaces.content.configurable_controller.menu.ConfigurableControllerMenu;
import com.cak.interfaces.foundation.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigurableController extends Item implements MenuProvider {
    
    public ConfigurableController(Properties properties) {
        super(properties);
    }
    
    @Override
    public @NotNull Component getDisplayName() {
        return Lang.translateGui("title.configurable_controller");
    }
    
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ConfigurableControllerMenu(CIRegistry.CONFIGURABLE_CONTROLLER_MENU.get(), i, inventory, player.getMainHandItem());
    }
    
}
