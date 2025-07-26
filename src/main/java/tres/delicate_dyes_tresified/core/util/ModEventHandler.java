package tres.delicate_dyes_tresified.core.util;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.init.EntityInit;
import tres.delicate_dyes_tresified.core.init.ItemInit;

import java.util.Map;

@EventBusSubscriber(
		bus = EventBusSubscriber.Bus.MOD,
		modid = "delicate_dyes_tresified"
)
public class ModEventHandler {
	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		for(DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
			Map<String, DeferredHolder<Block, Block>> blocks = (Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName());
			CauldronInteraction.WATER.map().put(((Block)((DeferredHolder)blocks.get("banner")).get()).asItem(), CauldronInteraction.BANNER);
		}

	}

	@SubscribeEvent
	public static void onEntityAttributeCreate(EntityAttributeCreationEvent event) {
		event.put((EntityType)EntityInit.SHEEP.get(), Sheep.createAttributes().build());
	}

	@SubscribeEvent
	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register((EntityType) EntityInit.SHEEP.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (animal, worldIn, reason, pos, random) -> false, RegisterSpawnPlacementsEvent.Operation.OR);
	}

	@SubscribeEvent
	public static void tabContents(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
			ItemInit.DYE_ITEMS.forEach((s, registryObject) -> event.accept((ItemLike)registryObject.get()));
		}

		for(DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
			Map<String, DeferredHolder<Block, Block>> blocks = (Map) BlockInit.DYED_BLOCKS.get(color.getSerializedName());
			if (event.getTabKey().equals(CreativeModeTabs.COLORED_BLOCKS)) {
				event.accept((ItemLike)((DeferredHolder)blocks.get("wool")).get());
				event.accept((ItemLike)((DeferredHolder)blocks.get("carpet")).get());
				event.accept((ItemLike)((DeferredHolder)blocks.get("terracotta")).get());
				event.accept((ItemLike)((DeferredHolder)blocks.get("concrete")).get());
				event.accept((ItemLike)((DeferredHolder)blocks.get("concrete_powder")).get());
				event.accept((ItemLike)((DeferredHolder)blocks.get("glazed_terracotta")).get());
				event.accept((ItemLike)((DeferredHolder)blocks.get("stained_glass")).get());
				event.accept((ItemLike)((DeferredHolder)blocks.get("stained_glass_pane")).get());
			}

			if (event.getTabKey().equals(CreativeModeTabs.COLORED_BLOCKS) || event.getTabKey().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
				event.accept((ItemLike)((DeferredHolder)blocks.get("candle")).get());
				event.accept((ItemLike)((DeferredHolder)blocks.get("bed")).get());
				event.accept((ItemLike)((DeferredHolder)blocks.get("shulker_box")).get());
				event.accept((ItemLike)((DeferredHolder)blocks.get("banner")).get());
			}
		}

	}
}
