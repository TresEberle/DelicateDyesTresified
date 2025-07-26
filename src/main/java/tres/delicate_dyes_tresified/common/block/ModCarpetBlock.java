package tres.delicate_dyes_tresified.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

public class ModCarpetBlock extends WoolCarpetBlock {
	protected static final VoxelShape SHAPE = Block.box((double)0.0F, (double)0.0F, (double)0.0F, (double)16.0F, (double)1.0F, (double)16.0F);
	private final DyeColorUtil color;

	public ModCarpetBlock(DyeColorUtil colorIn, BlockBehaviour.Properties properties) {
		super(colorIn.getAnalogue(), properties);
		this.color = colorIn;
	}

	public DyeColorUtil getDyenamicColor() {
		return this.color;
	}

	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPE;
	}

	public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
		return !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
	}

	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
		return !pLevel.isEmptyBlock(pPos.below());
	}
}
