package tres.delicate_dyes_tresified.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import tres.delicate_dyes_tresified.block.ModBlocks;


import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
	protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
	}

	@Override
	protected void generate() {
		dropSelf(ModBlocks.BLURPLE_WOOL.get());
		dropSelf(ModBlocks.CANARY_WOOL.get());
		dropSelf(ModBlocks.CORAL_WOOL.get());
		dropSelf(ModBlocks.ROSE_WOOL.get());
		dropSelf(ModBlocks.SACRAMENTO_WOOL.get());
		dropSelf(ModBlocks.SACRAMENTO_CARPET.get());
		dropSelf(ModBlocks.SACRAMENTO_TERRACOTTA.get());
		dropSelf(ModBlocks.SACRAMENTO_CONCRETE.get());
		dropSelf(ModBlocks.SACRAMENTO_CONCRETE_POWDER.get());
		dropSelf(ModBlocks.SACRAMENTO_GLAZED_TERRACOTTA.get());
		dropWhenSilkTouch(ModBlocks.SACRAMENTO_STAINED_GLASS.get());
		dropWhenSilkTouch(ModBlocks.SACRAMENTO_STAINED_GLASS_PANE.get());
		dropSelf(ModBlocks.SANGRIA_WOOL.get());
		dropSelf(ModBlocks.SKY_WOOL.get());
		dropSelf(ModBlocks.WASABI_WOOL.get());

	}

	@Override
	protected @NotNull Iterable<Block> getKnownBlocks() {
		return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
	}
}
