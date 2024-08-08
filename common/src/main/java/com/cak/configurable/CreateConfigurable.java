package com.cak.configurable;

import com.cak.configurable.foundation.controller_components.ControllerComponents;
import com.cak.configurable.network.PlatformPackets;
import com.simibubi.create.Create;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateConfigurable {
    public static final String MOD_ID = "create_configurable";
    public static final String NAME = "Create: Configurable";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
    
    public static void init() {
        LOGGER.info("{} initializing! Create version: {}", NAME, Create.VERSION);
        OMRegistry.init();
        ControllerComponents.register();
        PlatformPackets.getChannel().initServerListener();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    
}
