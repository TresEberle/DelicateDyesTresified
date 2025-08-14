package tres.delicate_dyes_tresified;

import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.DyeColor;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import tres.delicate_dyes_tresified.core.init.BlockEntityInit;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.init.ItemInit;
import tres.delicate_dyes_tresified.core.init.EntityInit;
import tres.delicate_dyes_tresified.core.init.RecipeSerializerInit;


import java.util.HashMap;
import java.util.Map;

@Mod(DelicateDyesTresified.MOD_ID)
public class DelicateDyesTresified {
	public static final String MOD_ID = "delicate_dyes_tresified";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final Map<String, Material> BED_MATERIAL_MAP = new HashMap();
	public static final Map<String, Material> SHULKER_MATERIAL_MAP = new HashMap();
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES ;


	public DelicateDyesTresified(IEventBus modEventBus, ModContainer modContainer) {
//		modEventBus.addListener(this::commonSetup);
//		NeoForge.EVENT_BUS.register(this);
		ModCreativeModeTabs.register(modEventBus);
//		modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);


		BlockInit.register();
		ItemInit.register();
		EntityInit.register();
		BlockInit.BLOCKS.register(modEventBus);
		ItemInit.ITEMS.register(modEventBus);
		EntityInit.ENTITIES.register(modEventBus);
		BlockEntityInit.BLOCK_ENTITY_TYPES.register(modEventBus);
		RecipeSerializerInit.RECIPE_SERIALIZERS.register(modEventBus);
		ATTACHMENT_TYPES.register(modEventBus);
	}

	static {
		ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, "delicate_dyes_tresified");
	}
}
