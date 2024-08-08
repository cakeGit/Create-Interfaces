package com.cak.configurable.content.configurable_controller.menu;

import com.cak.configurable.CreateConfigurable;
import com.cak.configurable.foundation.controller_components.ControllerComponent;
import com.jozufozu.flywheel.util.Pair;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

public abstract class PlacedComponentSubMenu {
    
    public static ResourceLocation COMPONENTS_TEXTURE = CreateConfigurable.asResource("textures/gui/configurable_controller/components.png");
    
    ControllerComponent<?> source;
    Pair<Integer, Integer> position;
    CompoundTag tag;
    ConfigurableControllerMenu menu;
    
    public PlacedComponentSubMenu(Pair<Integer, Integer> position, ControllerComponent<?> source, CompoundTag tag, ConfigurableControllerMenu menu) {
        this.position = position;
        this.source = source;
        this.tag = tag;
        this.menu = menu;
    }
    
    protected static void renderComponentSpriteSheetSection(GuiGraphics graphics, int x, int y, int gridX, int gridY, int gridWidth, int gridHeight) {
        graphics.blit(COMPONENTS_TEXTURE, x, y, gridX * 42, gridY * 42, gridWidth * 42, gridHeight * 42, 256, 256);
    }
    
    abstract public void render(GuiGraphics graphics, int x, int y, int mouseX, int mouseY);
    
    public CompoundTag getTag() {
        return tag;
    }
    
}
