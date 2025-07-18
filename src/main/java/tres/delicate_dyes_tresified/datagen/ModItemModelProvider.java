package tres.delicate_dyes_tresified.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import tres.delicate_dyes_tresified.DelicateDyesTresified;
import tres.delicate_dyes_tresified.block.ModBlocks;
import tres.delicate_dyes_tresified.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {

	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, DelicateDyesTresified.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		basicItem(ModItems.BLURPLE_DYE.get());
		basicItem(ModItems.CANARY_DYE.get());
		basicItem(ModItems.CORAL_DYE.get());
		basicItem(ModItems.ROSE_DYE.get());
		basicItem(ModItems.SACRAMENTO_DYE.get());
		basicItem(ModItems.SANGRIA_DYE.get());
		basicItem(ModItems.SKY_DYE.get());
		basicItem(ModItems.WASABI_DYE.get());

		simpleBlockItem(ModBlocks.SACRAMENTO_CARPET.get());
		simpleBlockItem(ModBlocks.SACRAMENTO_GLAZED_TERRACOTTA.get());
		simpleBlockItem(ModBlocks.SACRAMENTO_STAINED_GLASS.get());
		withExistingParent("sacramento_stained_glass_pane", modLoc("block/sacramento_stained_glass_pane"));




	}


}
