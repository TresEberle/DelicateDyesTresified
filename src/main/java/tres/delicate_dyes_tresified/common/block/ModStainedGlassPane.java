package tres.delicate_dyes_tresified.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

public class ModStainedGlassPane extends IronBarsBlock {
	private final DyeColorUtil color;

	public ModStainedGlassPane(DyeColorUtil colorIn, BlockBehaviour.Properties properties) {
		super(properties);
		this.color = colorIn;
		this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(NORTH, Boolean.FALSE)).setValue(EAST, Boolean.FALSE)).setValue(SOUTH, Boolean.FALSE)).setValue(WEST, Boolean.FALSE)).setValue(WATERLOGGED, Boolean.FALSE));
	}

	public DyeColorUtil getColor() {
		return this.color;
	}

	public Integer getBeaconColorMultiplier(BlockState state, LevelReader level, BlockPos pos, BlockPos beaconPos) {
		return this.getColor().getColorComponentValue();
	}
}
