package com.cak.interfaces;

import com.cak.interfaces.content.configurable_controller.ConfigurableController;
import com.cak.interfaces.content.configurable_controller.ConfigurableControllerItemRenderer;
import com.cak.interfaces.content.configurable_controller.menu.ConfigurableControllerMenu;
import com.cak.interfaces.content.configurable_controller.menu.ConfigurableControllerScreen;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.MenuBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.MenuEntry;
import net.minecraft.world.level.block.Block;

public class CIRegistry {
	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CreateInterfaces.MOD_ID);
	
	public static final ItemEntry<ConfigurableController> CONFIGURABLE_CONTROLLER = REGISTRATE
		.item("configurable_controller", ConfigurableController::new)
		.transform(CreateRegistrate.customRenderedItem(() -> ConfigurableControllerItemRenderer::new))
		.register();
	
	public static final MenuEntry<ConfigurableControllerMenu> CONFIGURABLE_CONTROLLER_MENU = REGISTRATE
		.menu("configurable_controller_menu", ConfigurableControllerMenu::new, () -> ConfigurableControllerScreen::new)
		.register();
	
	
	public static void init() {
		// load the class and register everything
		CreateInterfaces.LOGGER.info("Registering blocks for " + CreateInterfaces.NAME);
	}
}
