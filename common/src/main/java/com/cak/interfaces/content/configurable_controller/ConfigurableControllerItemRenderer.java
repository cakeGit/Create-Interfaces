package com.cak.interfaces.content.configurable_controller;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModel;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModelRenderer;
import com.simibubi.create.foundation.item.render.PartialItemModelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class ConfigurableControllerItemRenderer extends CustomRenderedItemModelRenderer {
    
    @Override
    protected void render(
        ItemStack stack, CustomRenderedItemModel model,
        PartialItemModelRenderer renderer, ItemDisplayContext transformType,
        PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        
    }
    
}
