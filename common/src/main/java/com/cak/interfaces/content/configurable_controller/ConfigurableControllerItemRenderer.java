package com.cak.interfaces.content.configurable_controller;

import com.cak.interfaces.CreateInterfaces;
import com.jozufozu.flywheel.core.PartialModel;
import com.jozufozu.flywheel.util.AnimationTickHolder;
import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModel;
import com.simibubi.create.foundation.item.render.CustomRenderedItemModelRenderer;
import com.simibubi.create.foundation.item.render.PartialItemModelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class ConfigurableControllerItemRenderer extends CustomRenderedItemModelRenderer {
    
    public static PartialModel CONTROLLER_BASE = new PartialModel(CreateInterfaces.asResource("configurable_controller/configurable_controller"));
    
    public static final ConfigurableControllerEquipAnimationData ANIMATION_DATA = new ConfigurableControllerEquipAnimationData();
    private static final ConfigurableControllerEquipAnimationData EMPTY_ANIMATION_DATA = new ConfigurableControllerEquipAnimationData();
    
    @Override
    protected void render(
        ItemStack stack, CustomRenderedItemModel model,
        PartialItemModelRenderer renderer, ItemDisplayContext transformType,
        PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        ms.pushPose();
        ms.scale(0.8f, 0.8f, 0.8f);
        
        TransformStack ts = TransformStack.cast(ms);
        
        ConfigurableControllerEquipAnimationData animationData = transformType.firstPerson() ? ANIMATION_DATA : EMPTY_ANIMATION_DATA;
        
        animationData.setActiveStack(stack);
        
        if (!transformType.firstPerson()) {
            ms.translate(0, 0.35f, 0);
            ts.rotateY(205f);
            ts.rotateX(-15f);
        } else {
            Vec3 position = animationData.getModelPosition(AnimationTickHolder.getPartialTicks());
            Vec3 rotation = animationData.getModelRotation(AnimationTickHolder.getPartialTicks());
            
            ms.translate(position.x, position.y, position.z);
            ts.rotateY(rotation.y);
            ts.rotateZ(rotation.z);
            ts.rotateX(rotation.x);
        }
        
        //Render the base
        renderer.render(CONTROLLER_BASE.get(), light);
        //Components
        
        ms.popPose();
    }
    
}
