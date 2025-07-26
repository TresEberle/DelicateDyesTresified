package tres.delicate_dyes_tresified.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import tres.delicate_dyes_tresified.common.block.ModShulkerBoxBlock;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;
import tres.delicate_dyes_tresified.core.init.BlockEntityInit;


import javax.annotation.Nonnull;

public class ModShulkerBoxBlockEntity extends ShulkerBoxBlockEntity {
	private DyeColorUtil color;

	public ModShulkerBoxBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(DyeColor.PURPLE, blockPos, blockState);
	}

	public ModShulkerBoxBlockEntity(DyeColorUtil colorIn, BlockPos blockPos, BlockState blockState) {
		this(blockPos, blockState);
		this.setColor(colorIn);
	}

	@Nonnull
	public BlockEntityType<?> getType() {
		return (BlockEntityType)BlockEntityInit.SHULKER_BOX.get();
	}

	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	public DyeColorUtil getDyenamicColor() {
		if (this.color == null) {
			this.color = ((ModShulkerBoxBlock)this.getBlockState().getBlock()).getDyenamicColor();
		}

		return this.color;
	}

	public void setColor(DyeColorUtil color) {
		this.color = color;
	}
}