package com.cak.interfaces;

import com.simibubi.create.Create;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateInterfaces {
    public static final String MOD_ID = "create_interfaces";
    public static final String NAME = "Create: Interfaces";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
    
    public static void init() {
        LOGGER.info("{} initializing! Create version: {}", NAME, Create.VERSION);
        CIRegistry.init();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    
}
