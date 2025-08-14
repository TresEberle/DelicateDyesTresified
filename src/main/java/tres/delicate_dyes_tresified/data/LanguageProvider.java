package tres.delicate_dyes_tresified.data;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.init.ItemInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import java.util.Map;

public class LanguageProvider extends net.neoforged.neoforge.common.data.LanguageProvider {
	public LanguageProvider(PackOutput output) {
		super(output, "delicate_dyes_tresified", "en_us");
	}

	protected void addTranslations() {
		this.add("entity.delicate_dyes_tresified.sheep", "Sheep");
		this.add("creativetab.delicate_dyes_tresified.delicate_dyes_tresified", "Delicate Dyes Tresified");

		for(DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
			String colorName = this.capName(color.getSerializedName());
			this.add((Block)((DeferredHolder)((Map) BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("bed")).get(), colorName + " Bed");
			this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("banner")).get(), colorName + " Banner");
			this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("shulker_box")).get(), colorName + " Shulker Box");
			this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("candle")).get(), colorName + " Candle");
			this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("wool")).get(), colorName + " Wool");
			this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("carpet")).get(), colorName + " Carpet");
			this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("terracotta")).get(), colorName + " Terracotta");
			this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("terracotta_slab")).get(), colorName + " Terracotta Slab");
			this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("glazed_terracotta")).get(), colorName + " Glazed Terracotta");
			this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("concrete")).get(), colorName + " Concrete");
			this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("concrete_powder")).get(), colorName + " Concrete Powder");
			this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass")).get(), colorName + " Stained Glass");
			this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass_pane")).get(), colorName + " Stained Glass Pane");
			this.add((Item)((DeferredHolder) ItemInit.DYE_ITEMS.get(color.getSerializedName() + "_dye")).get(), colorName + " Dye");
		}
		for(net.minecraft.world.item.DyeColor color : net.minecraft.world.item.DyeColor.values()) {
			String colorName = this.capName(color.getSerializedName());
			this.add((Block)((DeferredHolder)((Map)BlockInit.VANILLA_EXTENSIONS.get(color.getSerializedName())).get("terracotta_slab")).get(), colorName + " Terracotta Slab");
		}
	}

	public String getName() {
		return "Delicate Dyes Tresified translation provider";
	}

	private String capName(String name) {
		String[] nameParts = name.split("_");

		for(int i = 0; i < nameParts.length; ++i) {
			String var10002 = nameParts[i].substring(0, 1).toUpperCase();
			nameParts[i] = var10002 + nameParts[i].substring(1);
		}

		return String.join(" ", nameParts);
	}
}

