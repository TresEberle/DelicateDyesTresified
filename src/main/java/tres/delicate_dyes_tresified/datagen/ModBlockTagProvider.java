package tres.delicate_dyes_tresified.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tres.delicate_dyes_tresified.DelicateDyesTresified;
import tres.delicate_dyes_tresified.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
	public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, DelicateDyesTresified.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
//		tag(BlockTags.MINEABLE_WITH_PICKAXE)
//			.add(ModBlocks.SACRAMENTO_TERRACOTTA.get())
//			.add(ModBlocks.SACRAMENTO_CONCRETE.get())
//			.add(ModBlocks.SACRAMENTO_GLAZED_TERRACOTTA.get());

		tag(BlockTags.WOOL)
			.add(ModBlocks.BLURPLE_WOOL.get())
			.add(ModBlocks.CANARY_WOOL.get())
			.add(ModBlocks.CORAL_WOOL.get())
			.add(ModBlocks.ROSE_WOOL.get())
			.add(ModBlocks.SACRAMENTO_WOOL.get())
			.add(ModBlocks.SANGRIA_WOOL.get())
			.add(ModBlocks.SKY_WOOL.get())
			.add(ModBlocks.WASABI_WOOL.get());

		tag(BlockTags.CONCRETE_POWDER)
			.add(ModBlocks.SACRAMENTO_CONCRETE_POWDER.get());

		tag(BlockTags.TERRACOTTA)
			.add(ModBlocks.SACRAMENTO_TERRACOTTA.get());

		tag(BlockTags.WOOL_CARPETS)
			.add(ModBlocks.SACRAMENTO_CARPET.get());

	}
}
