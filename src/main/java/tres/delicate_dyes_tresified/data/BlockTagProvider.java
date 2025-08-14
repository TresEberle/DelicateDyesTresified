package tres.delicate_dyes_tresified.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends BlockTagsProvider {
	public BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
		super(output, provider, "delicate_dyes_tresified", helper);
	}

	protected void addTags(HolderLookup.Provider provider) {
		for(DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
			this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(new Block[]{(Block)((DeferredHolder)((Map) BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("terracotta")).get(), (Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("terracotta_slab")).get(), (Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("glazed_terracotta")).get(), (Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("concrete")).get()});
			this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("concrete_powder")).get());
			this.tag(BlockTags.BANNERS).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("banner")).get());
			this.tag(BlockTags.BEDS).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("bed")).get());
			this.tag(BlockTags.CANDLES).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("candle")).get());
			this.tag(BlockTags.CANDLE_CAKES).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("candle_cake")).get());
			this.tag(BlockTags.WOOL).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("wool")).get());
			this.tag(BlockTags.WOOL_CARPETS).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("carpet")).get());
			this.tag(BlockTags.SHULKER_BOXES).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("shulker_box")).get());
			this.tag(BlockTags.IMPERMEABLE).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass")).get());
			this.tag(Tags.Blocks.GLASS_BLOCKS).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass")).get());
			this.tag(Tags.Blocks.GLASS_BLOCKS_TINTED).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass")).get());
			this.tag(Tags.Blocks.GLASS_PANES).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass_pane")).get());
			this.tag(BlockTags.create(ResourceLocation.parse("c:glass/" + color.getSerializedName()))).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass")).get());
			this.tag(BlockTags.create(ResourceLocation.parse("c:glass_panes/" + color.getSerializedName()))).add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass_pane")).get());
		}
		for(net.minecraft.world.item.DyeColor color : net.minecraft.world.item.DyeColor.values()) {
			this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(new Block[]{(Block)((DeferredHolder)((Map)BlockInit.VANILLA_EXTENSIONS.get(color.getSerializedName())).get("terracotta_slab")).get()});

		}
	}

	public String getName() {
		return "Delicate Dyes Tresified Block Tags Provider";
	}
}
