package tres.delicate_dyes_tresified.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import tres.delicate_dyes_tresified.common.blockentity.ModBedBlockEntity;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

public class ModBedBlock extends BedBlock {
	private final DyeColorUtil color;

	public ModBedBlock(DyeColorUtil colorIn, BlockBehaviour.Properties properties) {
		super(DyeColor.WHITE, properties);
		this.color = colorIn;
	}

	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ModBedBlockEntity(this.color, pPos, pState);
	}

	public DyeColorUtil getDyenamicColor() {
		return this.color;
	}
}
