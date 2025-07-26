package tres.delicate_dyes_tresified;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.init.ItemInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import java.util.Map;
import java.util.function.Supplier;

public class ModCreativeModeTabs {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DelicateDyesTresified.MOD_ID);

	public static final Supplier<CreativeModeTab> DELICATE_DYES_TAB = CREATIVE_MODE_TAB.register("delicate_dyes_tresified",
			() -> CreativeModeTab.builder()
					.icon(() -> new ItemStack(BlockInit.DYED_BLOCKS.get("sacramento").get("wool").get()))
					.title(Component.translatable("creativetab.delicate_dyes_tresified.delicate_dyes_tresified"))
					.displayItems((itemDisplayParameters, output) -> {
						for(DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
							Map<String, DeferredHolder<Block, Block>> blocks = (Map) BlockInit.DYED_BLOCKS.get(color.getSerializedName());
								output.accept((ItemLike) ((DeferredHolder) blocks.get("wool")).get());
								output.accept((ItemLike) ((DeferredHolder) blocks.get("carpet")).get());
								output.accept((ItemLike) ((DeferredHolder) blocks.get("terracotta")).get());
								output.accept((ItemLike) ((DeferredHolder) blocks.get("concrete")).get());
								output.accept((ItemLike) ((DeferredHolder) blocks.get("concrete_powder")).get());
								output.accept((ItemLike) ((DeferredHolder) blocks.get("glazed_terracotta")).get());
								output.accept((ItemLike) ((DeferredHolder) blocks.get("stained_glass")).get());
								output.accept((ItemLike) ((DeferredHolder) blocks.get("stained_glass_pane")).get());
								output.accept((ItemLike) ((DeferredHolder) blocks.get("shulker_box")).get());
								output.accept((ItemLike) ((DeferredHolder) blocks.get("bed")).get());
								output.accept((ItemLike) ((DeferredHolder) blocks.get("candle")).get());
								output.accept((ItemLike) ((DeferredHolder) blocks.get("banner")).get());
							}
						ItemInit.DYE_ITEMS.forEach((s, registryObject) -> output.accept((ItemLike)registryObject.get()));
					}).build());


	public static void register(IEventBus eventBus) {
		CREATIVE_MODE_TAB.register(eventBus);
	}
}
