package tres.delicate_dyes_tresified.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import tres.delicate_dyes_tresified.common.block.ModBedBlock;
import tres.delicate_dyes_tresified.core.init.BlockEntityInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

public class ModBedBlockEntity extends BlockEntity {
	private DyeColorUtil color;

	public ModBedBlockEntity(BlockPos blockPos, BlockState blockState) {
		super((BlockEntityType) BlockEntityInit.BED.get(), blockPos, blockState);
	}

	public ModBedBlockEntity(DyeColorUtil colorIn, BlockPos blockPos, BlockState blockState) {
		this(blockPos, blockState);
		this.setColor(colorIn);
	}

	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	public DyeColorUtil getColor() {
		if (this.color == null) {
			this.color = ((ModBedBlock)this.getBlockState().getBlock()).getDyenamicColor();
		}

		return this.color;
	}

	public void setColor(DyeColorUtil color) {
		this.color = color;
	}
}
