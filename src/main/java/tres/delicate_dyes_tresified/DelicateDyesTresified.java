package tres.delicate_dyes_tresified;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.DyeColor;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import tres.delicate_dyes_tresified.block.ModBlocks;
import tres.delicate_dyes_tresified.item.ModCreativeModTabs;
import tres.delicate_dyes_tresified.item.ModItems;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(DelicateDyesTresified.MOD_ID)
public class DelicateDyesTresified {
	public static final String MOD_ID = "delicate_dyes_tresified";
	public static final Logger LOGGER = LogUtils.getLogger();

	public DelicateDyesTresified(IEventBus modEventBus, ModContainer modContainer) {
		modEventBus.addListener(this::commonSetup);

		NeoForge.EVENT_BUS.register(this);

		ModCreativeModTabs.register(modEventBus);

		ModItems.register(modEventBus);
		ModBlocks.register(modEventBus);


		// Register our mod's ModConfigSpec so that FML can create and load the config file for us
		modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

		System.out.println("=== DyeColor List ===");
		for (DyeColor color : DyeColor.values()) {
			System.out.println("Dye: " + color.getName());
		}
	}

	private void commonSetup(FMLCommonSetupEvent event) {

	}

	// Add the example block item to the building blocks tab
	private void addCreative(BuildCreativeModeTabContentsEvent event) {

	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		// Do something when the server starts
		LOGGER.info("HELLO from server starting");
	}
}
