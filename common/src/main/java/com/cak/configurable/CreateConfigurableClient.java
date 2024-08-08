package com.cak.configurable;

import com.cak.configurable.foundation.client.ConfigurableControllerHandler;
import com.cak.configurable.network.PlatformPackets;

public class CreateConfigurableClient {
    
    public static final ConfigurableControllerHandler CONTROLLER_HANDLER = new ConfigurableControllerHandler();
    
    public static void init() {
        PlatformPackets.getChannel().initClientListener();
    }
    
}
