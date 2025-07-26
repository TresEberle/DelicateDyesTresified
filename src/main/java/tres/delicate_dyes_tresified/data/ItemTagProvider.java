package tres.delicate_dyes_tresified.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.init.ItemInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends ItemTagsProvider {
	public ItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future, CompletableFuture<TagsProvider.TagLookup<Block>> provider, ExistingFileHelper helper) {
		super(output, future, provider, "delicate_dyes_tresified", helper);
	}

	protected void addTags(HolderLookup.Provider provider) {
		IntrinsicHolderTagsProvider.IntrinsicTagAppender<Item> shulkerBoxes = this.tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath("delicate_dyes_tresified", "shulker_boxes")));
		shulkerBoxes.add(new Item[]{Items.SHULKER_BOX, Items.WHITE_SHULKER_BOX, Items.ORANGE_SHULKER_BOX, Items.MAGENTA_SHULKER_BOX, Items.LIGHT_BLUE_SHULKER_BOX, Items.YELLOW_SHULKER_BOX, Items.LIME_SHULKER_BOX, Items.PINK_SHULKER_BOX, Items.GRAY_SHULKER_BOX, Items.LIGHT_GRAY_SHULKER_BOX, Items.CYAN_SHULKER_BOX, Items.PURPLE_SHULKER_BOX, Items.BLUE_SHULKER_BOX, Items.BROWN_SHULKER_BOX, Items.GREEN_SHULKER_BOX, Items.RED_SHULKER_BOX, Items.BLACK_SHULKER_BOX});

		for(DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
			this.copy(BlockTags.BANNERS, ItemTags.BANNERS);
			this.copy(BlockTags.BEDS, ItemTags.BEDS);
			this.copy(BlockTags.CANDLES, ItemTags.CANDLES);
			this.copy(BlockTags.WOOL, ItemTags.WOOL);
			this.copy(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS);
			shulkerBoxes.add(((Block)((DeferredHolder)((Map) BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("shulker_box")).get()).asItem());
			this.copy(Tags.Blocks.GLASS_BLOCKS, net.neoforged.neoforge.common.Tags.Items.GLASS_BLOCKS);
			this.copy(Tags.Blocks.GLASS_PANES, net.neoforged.neoforge.common.Tags.Items.GLASS_PANES);
			this.copy(Tags.Blocks.GLASS_BLOCKS_TINTED, net.neoforged.neoforge.common.Tags.Items.GLASS_BLOCKS_TINTED);
			this.tag(ItemTags.create(ResourceLocation.parse("delicate_dyes_tresified:glass/" + color.getSerializedName()))).add(((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass")).get()).asItem());
			this.tag(ItemTags.create(ResourceLocation.parse("delicate_dyes_tresified:glass_panes/" + color.getSerializedName()))).add(((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass_pane")).get()).asItem());
			this.tag(net.neoforged.neoforge.common.Tags.Items.DYES).add((Item)((DeferredHolder) ItemInit.DYE_ITEMS.get(color.getSerializedName() + "_dye")).get());
			this.tag(ItemTags.create(ResourceLocation.parse("delicate_dyes_tresified:dyes/" + color.getSerializedName()))).add((Item)((DeferredHolder)ItemInit.DYE_ITEMS.get(color.getSerializedName() + "_dye")).get());
		}

	}

	public String getName() {
		return "Delicate Dyes Tresified Item Tags Provider";
	}
}
