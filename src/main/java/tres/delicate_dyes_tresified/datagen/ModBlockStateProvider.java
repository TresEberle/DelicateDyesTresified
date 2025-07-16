package tres.delicate_dyes_tresified.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import tres.delicate_dyes_tresified.DelicateDyesTresified;
import tres.delicate_dyes_tresified.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, DelicateDyesTresified.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		blocksWithItem(ModBlocks.BLURPLE_WOOL);
		blocksWithItem(ModBlocks.CANARY_WOOL);
		blocksWithItem(ModBlocks.CORAL_WOOL);
		blocksWithItem(ModBlocks.ROSE_WOOL);
		blocksWithItem(ModBlocks.SACRAMENTO_WOOL);
		blocksWithItem(ModBlocks.SACRAMENTO_CARPET);
		blocksWithItem(ModBlocks.SACRAMENTO_TERRACOTTA);
		blocksWithItem(ModBlocks.SACRAMENTO_CONCRETE);
		blocksWithItem(ModBlocks.SACRAMENTO_CONCRETE_POWDER);
		blocksWithItem(ModBlocks.SACRAMENTO_GLAZED_TERRACOTTA);
		blocksWithItem(ModBlocks.SACRAMENTO_STAINED_GLASS);
		blocksWithItem(ModBlocks.SANGRIA_WOOL);
		blocksWithItem(ModBlocks.SKY_WOOL);
		blocksWithItem(ModBlocks.WASABI_WOOL);
	}

	private void blocksWithItem(DeferredBlock<?> deferredBlock){
		simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
	}
}
