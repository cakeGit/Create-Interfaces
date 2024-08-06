package com.cak.configurable;

import com.cak.configurable.content.configurable_controller.menu.ConfigurableControllerMenu;
import com.cak.configurable.content.configurable_controller.menu.ConfigurableControllerScreen;
import com.cak.configurable.content.configurable_controller.ConfigurableControllerItem;
import com.cak.configurable.content.configurable_controller.ConfigurableControllerItemRenderer;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.MenuEntry;
import net.minecraft.world.item.Item;

public class OMRegistry {
	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CreateConfigurable.MOD_ID);
	
	public static final ItemEntry<ConfigurableControllerItem> CONFIGURABLE_CONTROLLER = REGISTRATE
		.item("configurable_controller", ConfigurableControllerItem::new)
		.transform(CreateRegistrate.customRenderedItem(() -> ConfigurableControllerItemRenderer::new))
		.register();
	
	public static final ItemEntry<Item> BUTTON_COMPONENT = REGISTRATE
		.item("button_component", Item::new)
		.register();
	
	public static final MenuEntry<ConfigurableControllerMenu> CONFIGURABLE_CONTROLLER_MENU = REGISTRATE
		.menu("configurable_controller_menu", ConfigurableControllerMenu::new, () -> ConfigurableControllerScreen::new)
		.register();
	
	public static void init() {
		// load the class and register everything
		CreateConfigurable.LOGGER.info("Registering blocks for " + CreateConfigurable.NAME);
	}
}
