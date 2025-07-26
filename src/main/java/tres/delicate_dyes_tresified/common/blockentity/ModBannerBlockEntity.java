package tres.delicate_dyes_tresified.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import tres.delicate_dyes_tresified.common.block.ModBannerBlock;
import tres.delicate_dyes_tresified.common.block.ModWallBannerBlock;
import tres.delicate_dyes_tresified.core.init.BlockEntityInit;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import java.util.Map;

public class ModBannerBlockEntity extends BannerBlockEntity {
	private DyeColorUtil baseColor;

	public ModBannerBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
		Block var5 = state.getBlock();
		if (var5 instanceof ModBannerBlock dyenamicBannerBlock) {
			this.baseColor = dyenamicBannerBlock.getDyenamicColor();
		} else {
			var5 = state.getBlock();
			if (var5 instanceof ModWallBannerBlock dyenamicBannerBlock) {
				this.baseColor = dyenamicBannerBlock.getDyenamicColor();
			}
		}

	}

	public BlockEntityType<?> getType() {
		return (BlockEntityType) BlockEntityInit.BANNER.get();
	}

	public void setDyenamicColor(DyeColorUtil color) {
		this.baseColor = color;
	}

	public DyeColorUtil getDyenamicColor() {
		return this.baseColor;
	}

	public ItemStack getItem() {
		ItemStack itemstack = new ItemStack((ItemLike)((DeferredHolder)((Map) BlockInit.DYED_BLOCKS.get(this.baseColor.getSerializedName())).get("banner")).get());
		itemstack.applyComponents(this.collectComponents());
		return itemstack;
	}
}
