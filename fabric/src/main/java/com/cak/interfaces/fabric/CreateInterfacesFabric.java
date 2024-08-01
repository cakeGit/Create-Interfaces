package com.cak.interfaces.fabric;

import com.cak.interfaces.CIRegistry;
import com.cak.interfaces.CreateInterfaces;
import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.fabricmc.api.ModInitializer;

public class CreateInterfacesFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CreateInterfaces.init();
        CreateInterfaces.LOGGER.info(EnvExecutor.unsafeRunForDist(
                () -> () -> "{} is accessing Porting Lib on a Fabric client!",
                () -> () -> "{} is accessing Porting Lib on a Fabric server!"
                ), CreateInterfaces.NAME);
        // on fabric, Registrates must be explicitly finalized and registered.
        CIRegistry.REGISTRATE.register();
    }
    
}
