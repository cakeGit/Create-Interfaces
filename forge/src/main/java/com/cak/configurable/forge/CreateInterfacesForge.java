package com.cak.configurable.forge;

import com.cak.configurable.OMRegistry;
import com.cak.configurable.CreateConfigurable;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreateConfigurable.MOD_ID)
public class CreateInterfacesForge {
    
    public CreateInterfacesForge() {
        // registrate must be given the mod event bus on forge before registration
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        OMRegistry.REGISTRATE.registerEventListeners(eventBus);
        CreateConfigurable.init();
    }
    
}
