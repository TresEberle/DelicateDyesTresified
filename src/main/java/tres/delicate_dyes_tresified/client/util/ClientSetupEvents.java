package tres.delicate_dyes_tresified.client.util;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import tres.delicate_dyes_tresified.DelicateDyesTresified;
import tres.delicate_dyes_tresified.client.render.block.ModBannerRenderer;
import tres.delicate_dyes_tresified.client.render.entity.ModSheepRenderer;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.init.BlockEntityInit;
import tres.delicate_dyes_tresified.core.init.EntityInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;
import tres.delicate_dyes_tresified.client.render.block.ModShulkerBoxBlockEntityRenderer;
import tres.delicate_dyes_tresified.client.render.block.ModBedRenderer;

import java.util.Map;

@EventBusSubscriber(
		value = {Dist.CLIENT},
		modid = "delicate_dyes_tresified",
		bus = EventBusSubscriber.Bus.MOD
)
public class ClientSetupEvents {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer((BlockEntityType)BlockEntityInit.BED.get(), ModBedRenderer::new);
		event.registerBlockEntityRenderer((BlockEntityType)BlockEntityInit.SHULKER_BOX.get(), ModShulkerBoxBlockEntityRenderer::new);
		event.registerEntityRenderer((EntityType) EntityInit.SHEEP.get(), ModSheepRenderer::new);
		event.registerBlockEntityRenderer((BlockEntityType)BlockEntityInit.BANNER.get(), ModBannerRenderer::new);
	}

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		for(DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
			ItemBlockRenderTypes.setRenderLayer((Block)((DeferredHolder)((Map) BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass")).get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass_pane")).get(), RenderType.translucent());
			DelicateDyesTresified.BED_MATERIAL_MAP.put(color.getSerializedName(), new Material(Sheets.BED_SHEET, ResourceLocation.fromNamespaceAndPath("delicate_dyes_tresified", "entity/bed/" + color.getSerializedName())));
			DelicateDyesTresified.SHULKER_MATERIAL_MAP.put(color.getSerializedName(), new Material(Sheets.SHULKER_SHEET, ResourceLocation.fromNamespaceAndPath("delicate_dyes_tresified", "entity/shulker/" + color.getSerializedName())));
		}

	}
}