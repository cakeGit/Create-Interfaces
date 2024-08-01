package com.cak.interfaces.fabric;

import com.cak.interfaces.CIRegistry;
import com.cak.interfaces.CreateInterfaces;
import com.cak.interfaces.event.ClientEvents;
import com.cak.interfaces.CreateInterfacesClient;
import com.tterrag.registrate.fabric.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class CreateInterfacesFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CreateInterfaces.init();
//        CreateInterfaces.LOGGER.info(EnvExecutor.unsafeRunForDist(
//                () -> () -> "{} is accessing Porting Lib on a Fabric client!",
//                () -> () -> "{} is accessing Porting Lib on a Fabric server!"
//                ), CreateInterfaces.NAME);
        // on fabric, Registrates must be explicitly finalized and registered.
        CIRegistry.REGISTRATE.register();
        
        ClientTickEvents.START_CLIENT_TICK.register((minecraft) -> {
            ClientEvents.tickClient();
        });
        
        EnvExecutor.runWhenOn(EnvType.CLIENT, () -> CreateInterfacesClient::init);
    }
    
}
