package tres.delicate_dyes_tresified.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
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
		carpetBlock(ModBlocks.SACRAMENTO_CARPET, modLoc("block/sacramento_wool"));
		simpleBlockItem(ModBlocks.SACRAMENTO_CARPET.get(),
				new ModelFile.UncheckedModelFile("delicate_dyes_tresified:block/sacramento_carpet"));

		blocksWithItem(ModBlocks.SACRAMENTO_TERRACOTTA);
		blocksWithItem(ModBlocks.SACRAMENTO_CONCRETE);
		blocksWithItem(ModBlocks.SACRAMENTO_CONCRETE_POWDER);
		//blocksWithItem(ModBlocks.SACRAMENTO_GLAZED_TERRACOTTA);

		horizontalBlock(ModBlocks.SACRAMENTO_GLAZED_TERRACOTTA.get(),
				models()
						.withExistingParent("sacramento_glazed_terracotta", "block/template_glazed_terracotta")
						.texture("pattern", modLoc("block/sacramento_glazed_terracotta"))
		);

		blocksWithItem(ModBlocks.SACRAMENTO_STAINED_GLASS);
		blocksWithItem(ModBlocks.SANGRIA_WOOL);
		blocksWithItem(ModBlocks.SKY_WOOL);
		blocksWithItem(ModBlocks.WASABI_WOOL);
	}

	private void blocksWithItem(DeferredBlock<?> deferredBlock){
		simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
	}

	private void blockItem(DeferredBlock<?> deferredBlock) {
		simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("delicate_dyes_tresified:block/" + deferredBlock.getId().getPath()));
	}

	private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
		simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("delicate_dyes_tresified:block/" + deferredBlock.getId().getPath()));
	}

	private void carpetBlock(DeferredBlock<?> deferredBlock, ResourceLocation texture) {
		Block block = deferredBlock.get();

		ModelFile model = models().withExistingParent(deferredBlock.getId().getPath(), mcLoc("block/carpet"))
				.texture("wool", texture.toString());

		getVariantBuilder(block)
				.partialState()
				.setModels(ConfiguredModel.builder().modelFile(model).build());

		simpleBlockItem(block, model);
	}

	private String blockName(DeferredBlock<?> deferredBlock) {
		return deferredBlock.getId().getPath();
	}


}
