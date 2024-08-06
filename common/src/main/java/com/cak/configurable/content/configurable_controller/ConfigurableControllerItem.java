package com.cak.configurable.content.configurable_controller;

import com.cak.configurable.CreateConfigurableClient;
import com.cak.configurable.OMRegistry;
import com.cak.configurable.content.configurable_controller.menu.ConfigurableControllerMenu;
import com.cak.configurable.foundation.client.Lang;
import io.github.fabricators_of_create.porting_lib.util.NetworkHooks;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigurableControllerItem extends Item implements MenuProvider {
    
    public ConfigurableControllerItem(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        
        if (player.isShiftKeyDown() && hand == InteractionHand.MAIN_HAND) {
            if (!level.isClientSide && player instanceof ServerPlayer && player.mayBuild())
                NetworkHooks.openScreen((ServerPlayer) player, this, buf -> {
                    buf.writeItem(heldItem);
                });
            return InteractionResultHolder.pass(heldItem);
        }
        
        if (level.isClientSide)
            CreateConfigurableClient.CONTROLLER_HANDLER.toggleUsing();
        
        return InteractionResultHolder.pass(heldItem);
    }
    
//    @Override
//    public InteractionResult useOn(UseOnContext context) {
//        if (!(context.getPlayer() instanceof Player player)) return InteractionResult.FAIL;
//
//        else if (context.getLevel().isClientSide)
//            CreateInterfacesClient.CONTROLLER_HANDLER.toggleUsing();
//        return InteractionResult.PASS;
//    }
    
    @Override
    public @NotNull Component getDisplayName() {
        return Lang.translateGui("title.configurable_controller");
    }
    
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ConfigurableControllerMenu(OMRegistry.CONFIGURABLE_CONTROLLER_MENU.get(), i, inventory, player.getMainHandItem());
    }
    
}
