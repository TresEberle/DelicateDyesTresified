package tres.delicate_dyes_tresified.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import tres.delicate_dyes_tresified.DelicateDyesTresified;
import tres.delicate_dyes_tresified.block.ModBlocks;
import tres.delicate_dyes_tresified.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {

	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, DelicateDyesTresified.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		basicItem(ModItems.CANARY_DYE.get());
		basicItem(ModItems.SACRAMENTO_DYE.get());
	}
}
