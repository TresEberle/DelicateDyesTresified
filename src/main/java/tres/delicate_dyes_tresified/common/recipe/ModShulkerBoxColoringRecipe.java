package tres.delicate_dyes_tresified.common.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import tres.delicate_dyes_tresified.common.block.ModShulkerBoxBlock;
import tres.delicate_dyes_tresified.common.item.ModDyeItem;
import tres.delicate_dyes_tresified.core.init.RecipeSerializerInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

public class ModShulkerBoxColoringRecipe extends CustomRecipe {
	public ModShulkerBoxColoringRecipe(CraftingBookCategory category) {
		super(category);
	}

	public boolean matches(CraftingInput inv, Level pLevel) {
		int boxes = 0;
		int dyes = 0;
		int slots = inv.size();

		for(int i = 0; i < slots; ++i) {
			ItemStack slotStack = inv.getItem(i);
			if (!slotStack.isEmpty()) {
				Item item = slotStack.getItem();
				if (item instanceof BlockItem) {
					BlockItem blockItem = (BlockItem)item;
					if (blockItem.getBlock() instanceof ShulkerBoxBlock) {
						if (boxes >= 1) {
							return false;
						}

						++boxes;
						continue;
					}
				}

				if (!(item instanceof ModDyeItem)) {
					return false;
				}

				if (dyes >= 1) {
					return false;
				}

				++dyes;
			}
		}

		return dyes == 1 && boxes == 1;
	}

	public ItemStack assemble(CraftingInput inv, HolderLookup.Provider pRegistryAccess) {
		ItemStack boxStack = ItemStack.EMPTY;
		DyeColorUtil color = DyeColorUtil.PEACH;
		int slots = inv.size();

		for(int i = 0; i < slots; ++i) {
			ItemStack slotStack = inv.getItem(i);
			if (!slotStack.isEmpty()) {
				Item item = slotStack.getItem();
				if (item instanceof BlockItem) {
					BlockItem blockItem = (BlockItem)item;
					if (blockItem.getBlock() instanceof ShulkerBoxBlock) {
						boxStack = slotStack;
						continue;
					}
				}

				color = DyeColorUtil.getColor(slotStack);
			}
		}

		ItemLike block = ModShulkerBoxBlock.getDyenamicColoredItemStack(color).getItem();
		return boxStack.transmuteCopy(block, 1);
	}

	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 2;
	}

	public RecipeSerializer<?> getSerializer() {
		return (RecipeSerializer) RecipeSerializerInit.SHULKER.get();
	}
}
