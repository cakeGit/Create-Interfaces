package com.cak.configurable.event;

import com.cak.configurable.content.configurable_controller.ConfigurableControllerItemRenderer;

public class ClientEvents {
    
    public static void tickClient() {
        ConfigurableControllerItemRenderer.ANIMATION_DATA.tick();
    }
    
}
