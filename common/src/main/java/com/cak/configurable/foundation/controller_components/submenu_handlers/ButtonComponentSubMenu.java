package com.cak.configurable.foundation.controller_components.submenu_handlers;

import com.cak.configurable.content.configurable_controller.menu.ConfigurableControllerMenu;
import com.cak.configurable.content.configurable_controller.menu.PlacedComponentSubMenu;
import com.cak.configurable.foundation.controller_components.ControllerComponent;
import com.jozufozu.flywheel.util.Pair;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;

public class ButtonComponentSubMenu extends PlacedComponentSubMenu {
    
    public ButtonComponentSubMenu(Pair<Integer, Integer> position, ControllerComponent<?> source, CompoundTag tag, ConfigurableControllerMenu menu) {
        super(position, source, tag, menu);
    }
    
    @Override
    public void render(GuiGraphics graphics, int x, int y, int mouseX, int mouseY) {
        renderComponentSpriteSheetSection(graphics, x, y, 0, 0, 1, 1);
    }
    
}
