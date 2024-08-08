package com.cak.configurable.content.configurable_controller.menu;

import com.cak.configurable.foundation.controller_components.ControllerComponent;
import com.cak.configurable.foundation.controller_components.ControllerComponents;
import com.cak.configurable.CreateConfigurable;
import com.jozufozu.flywheel.util.Pair;
import com.mojang.blaze3d.systems.RenderSystem;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.menu.AbstractSimiContainerScreen;
import com.simibubi.create.foundation.gui.widget.IconButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class ConfigurableControllerScreen extends AbstractSimiContainerScreen<ConfigurableControllerMenu> {
    
    ResourceLocation BACKGROUND = CreateConfigurable.asResource("textures/gui/configurable_controller/background.png");
    ResourceLocation SELECTION = CreateConfigurable.asResource("textures/gui/configurable_controller/selection_cell.png");
    
    int gridTopX = 21;
    int gridTopY = 19;
    
    IconButton confirmButton;
    
    public ConfigurableControllerScreen(ConfigurableControllerMenu container, Inventory inv, Component title) {
        super(container, inv, title);
        
        imageWidth = 256;
        imageHeight = 256;
    }
    
    @Override
    protected void init() {
        super.init();
        clearWidgets();
        
        confirmButton = new IconButton(leftPos + 214, topPos + 202, AllIcons.I_CONFIRM);
        confirmButton.withCallback(() -> minecraft.player.closeContainer());
        addRenderableWidget(confirmButton);
    }
    
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);
        
        for (PlacedComponentSubMenu component : menu.components) {
            Pair<Integer, Integer> position = component.position;
            
            component.render(graphics, leftPos + gridTopX + position.first() * 42, topPos + gridTopY + position.second() * 42, mouseX, mouseY);
        }
        
        ControllerComponent<?> heldComponent = ControllerComponents.fromItem(menu.getCarried().getItem());
        
        if (heldComponent == null) return;
        
        RenderSystem.enableBlend();
        graphics.setColor(0.1f, 0.9f, 0.3f, 0.6f);
        Pair<Integer, Integer> gridDimensions = heldComponent.getGridDimensions();
        
        Pair<Integer, Integer> currentCellPos = screenToCellPos(mouseX, mouseY);
        
        for (int x = 0; x < gridDimensions.first(); x++)
            for (int y = 0; y < gridDimensions.first(); y++) {
                Pair<Integer, Integer> thisCellPos = Pair.of(
                    currentCellPos.first() + x,
                    currentCellPos.second() + y
                );
                if (!isValidCellPos(thisCellPos)) continue;
                
                graphics.blit(SELECTION,
                    leftPos + gridTopX + 42 * currentCellPos.first(),
                    topPos + gridTopY + 42 * currentCellPos.second(),
                    0, 0, 42, 42);
            }
        
        graphics.setColor(1f, 1f, 1f, 1f);
    }
    
    public static boolean isValidCellPos(Pair<Integer, Integer> cellPos) {
        return ((cellPos.first() >= 0 && cellPos.first() <= 4) &&
            (cellPos.second() >= 0 && cellPos.second() <= 3));
    }
    
    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        Pair<Integer, Integer> cellPos = screenToCellPos((int) pMouseX, (int) pMouseY);
        ItemStack stack = menu.getCarried();
        ControllerComponent<?> carriedComponent = stack == ItemStack.EMPTY ? null : ControllerComponents.fromItem(stack.getItem());
        
        if (isValidCellPos(cellPos) && carriedComponent != null) {
            menu.placeComponent(cellPos, carriedComponent);
            return true;
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }
    
    private Pair<Integer, Integer> screenToCellPos(int mouseX, int mouseY) {
        return
            Pair.of(
                Math.floorDiv(mouseX - (leftPos + gridTopX), 42),
                Math.floorDiv(mouseY - (topPos + gridTopY), 42)
            );
    }
    
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(BACKGROUND, leftPos, topPos, 0, 0, 256, 256);
    }
    
}
