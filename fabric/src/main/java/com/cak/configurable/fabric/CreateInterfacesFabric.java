package com.cak.configurable.fabric;

import com.cak.configurable.OMRegistry;
import com.cak.configurable.CreateConfigurable;
import com.cak.configurable.event.ClientEvents;
import com.cak.configurable.CreateConfigurableClient;
import com.tterrag.registrate.fabric.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class CreateInterfacesFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CreateConfigurable.init();
//        CreateInterfaces.LOGGER.info(EnvExecutor.unsafeRunForDist(
//                () -> () -> "{} is accessing Porting Lib on a Fabric client!",
//                () -> () -> "{} is accessing Porting Lib on a Fabric server!"
//                ), CreateInterfaces.NAME);
        // on fabric, Registrates must be explicitly finalized and registered.
        OMRegistry.REGISTRATE.register();
        
        ClientTickEvents.START_CLIENT_TICK.register((minecraft) -> {
            ClientEvents.tickClient();
        });
        
        EnvExecutor.runWhenOn(EnvType.CLIENT, () -> CreateConfigurableClient::init);
    }
    
}
