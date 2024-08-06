package com.cak.configurable.foundation.controller_components;

import com.cak.configurable.content.configurable_controller.menu.ConfigurableControllerMenu;
import com.cak.configurable.content.configurable_controller.menu.PlacedComponentSubMenu;
import com.jozufozu.flywheel.util.Pair;
import net.minecraft.nbt.CompoundTag;

public interface SubMenuFactory {
    PlacedComponentSubMenu apply(Pair<Integer, Integer> position, ControllerComponent<?> source, CompoundTag tag, ConfigurableControllerMenu menu);
}
