package com.cak.interfaces.forge;

import com.cak.interfaces.CIRegistry;
import com.cak.interfaces.CreateInterfaces;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreateInterfaces.MOD_ID)
public class CreateInterfacesForge {
    
    public CreateInterfacesForge() {
        // registrate must be given the mod event bus on forge before registration
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        CIRegistry.REGISTRATE.registerEventListeners(eventBus);
        CreateInterfaces.init();
    }
    
}
