package com.cak.interfaces.content.configurable_controller;

import com.cak.interfaces.CIRegistry;
import com.cak.interfaces.content.configurable_controller.menu.ConfigurableControllerMenu;
import com.cak.interfaces.foundation.client.Lang;
import com.cak.interfaces.CreateInterfacesClient;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigurableController extends Item implements MenuProvider {
    
    public ConfigurableController(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (level.isClientSide)
            CreateInterfacesClient.CONTROLLER_HANDLER.toggleUsing();
        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }
    
    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide)
            CreateInterfacesClient.CONTROLLER_HANDLER.toggleUsing();
        return InteractionResult.PASS;
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
