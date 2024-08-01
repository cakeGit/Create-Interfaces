package com.cak.interfaces.event;

import com.cak.interfaces.CreateInterfaces;
import com.cak.interfaces.content.configurable_controller.ConfigurableControllerItemRenderer;

public class ClientEvents {
    
    public static void tickClient() {
        ConfigurableControllerItemRenderer.ANIMATION_DATA.tick();
    }
    
}
