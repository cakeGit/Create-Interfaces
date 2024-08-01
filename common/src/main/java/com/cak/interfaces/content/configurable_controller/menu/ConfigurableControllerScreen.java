package com.cak.interfaces.content.configurable_controller.menu;

import com.simibubi.create.content.trains.schedule.ScheduleMenu;
import com.simibubi.create.foundation.gui.menu.AbstractSimiContainerScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

public class ConfigurableControllerScreen extends AbstractSimiContainerScreen<ConfigurableControllerMenu> {
    
    public ConfigurableControllerScreen(ConfigurableControllerMenu container, Inventory inv, Component title) {
        super(container, inv, title);
    }
    
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
    
    }
    
}
