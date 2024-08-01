package com.cak.interfaces.content.configurable_controller;

import com.cak.interfaces.CreateInterfacesClient;
import com.jozufozu.flywheel.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class ConfigurableControllerEquipAnimationData {
    
    float equipAnimation = 0;
    
    float equipAnimationSpeed = 1 / 20f;
    
    float nextAnimationDelta = 0;
    
    Pair<Vec3, Vec3> modelEquipRotation = Pair.of(new Vec3(-20, 180, -2.5), new Vec3(-35, 175, -10));
    
    Pair<Vec3, Vec3> modelEquipPosition = Pair.of(new Vec3(0, 0.25, -0.2), new Vec3(0, 0.5, -0.2));
    
    ItemStack activeStack;
    
    public Vec3 getModelRotation(float pt) {
        return interpolateVectorPair(modelEquipRotation, equipAnimation + nextAnimationDelta * pt);
    }
    
    public Vec3 getModelPosition(float pt) {
        return interpolateVectorPair(modelEquipPosition, equipAnimation + nextAnimationDelta * pt);
    }
    
    public void tick() {
        if (Minecraft.getInstance().isPaused() || activeStack == null) return;
        equipAnimation += nextAnimationDelta;
        float equipAnimationTarget = CreateInterfacesClient.CONTROLLER_HANDLER.isUsing() ? 1 : 0;
        equipAnimationSpeed = 1 / 10f;
        nextAnimationDelta = Math.clamp(equipAnimationTarget - equipAnimation, -equipAnimationSpeed, equipAnimationSpeed);
    }
    
    public void connect(ItemStack stack) {
        activeStack = stack;
    }
    
    public void disconnect() {
        equipAnimation = 0;
        activeStack = null;
    }
    
    private Vec3 interpolateVectorPair(Pair<Vec3, Vec3> vectors, float animation) {
        return vectors.first().lerp(vectors.second(), linearToSquareInterpolation(animation));
    }
    
    private float linearToSquareInterpolation(float x) {
        if (x < 0) return 0;
        if (x > 1) return 1;
        if (x == 0.5) return 0.5f;
        else if (x < 0.5)
            return 2f * x * x;
        else
            return 2f * (-((x - 1) * (x - 1)) + 0.5f);
    }
    
    public void setActiveStack(ItemStack activeStack) {
        this.activeStack = activeStack;
    }
    
}
