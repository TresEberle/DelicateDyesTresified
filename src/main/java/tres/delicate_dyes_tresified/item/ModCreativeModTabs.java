package tres.delicate_dyes_tresified.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import tres.delicate_dyes_tresified.DelicateDyesTresified;
import tres.delicate_dyes_tresified.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModTabs {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DelicateDyesTresified.MOD_ID);

	public static final Supplier<CreativeModeTab> DELICATE_DYES_TAB = CREATIVE_MODE_TAB.register("delicate_dyes_tresified",
			() -> CreativeModeTab.builder()
					.icon(() -> new ItemStack(ModBlocks.SACRAMENTO_WOOL.get()))
					.title(Component.translatable("creativetab.delicate_dyes_tresified.delicate_dyes_tresified"))
					.displayItems((itemDisplayParameters, output) -> {
						output.accept(ModBlocks.BLURPLE_WOOL);

						output.accept(ModItems.CANARY_DYE);
						output.accept(ModBlocks.CANARY_WOOL);

						output.accept(ModBlocks.CORAL_WOOL);

						output.accept(ModBlocks.ROSE_WOOL);

						output.accept(ModBlocks.SACRAMENTO_WOOL);
						output.accept(ModBlocks.SACRAMENTO_CARPET);
						output.accept(ModBlocks.SACRAMENTO_CONCRETE);
						output.accept(ModBlocks.SACRAMENTO_CONCRETE_POWDER);
						output.accept(ModBlocks.SACRAMENTO_GLAZED_TERRACOTTA);
						output.accept(ModBlocks.SACRAMENTO_STAINED_GLASS);
						output.accept(ModItems.SACRAMENTO_DYE);

						output.accept(ModBlocks.SANGRIA_WOOL);

						output.accept(ModBlocks.SKY_WOOL);

						output.accept(ModBlocks.WASABI_WOOL);



					}).build());


	public static void register(IEventBus eventBus) {
		CREATIVE_MODE_TAB.register(eventBus);
	}
}
