package tres.delicate_dyes_tresified.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

public class ModStainedGlassBlock extends TransparentBlock implements BeaconBeamBlock {
	private final DyeColorUtil color;

	public ModStainedGlassBlock(DyeColorUtil colorIn, BlockBehaviour.Properties properties) {
		super(properties);
		this.color = colorIn;
	}

	public DyeColor getColor() {
		return this.color.getAnalogue();
	}

	public Integer getBeaconColorMultiplier(BlockState state, LevelReader level, BlockPos pos, BlockPos beaconPos) {
		return this.color.getColorValue();
	}
}
