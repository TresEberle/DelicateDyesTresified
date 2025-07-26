package tres.delicate_dyes_tresified.common.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import tres.delicate_dyes_tresified.common.blockentity.ModBannerBlockEntity;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

public class ModBannerBlock extends AbstractBannerBlock {
	public static final MapCodec<ModBannerBlock> CODEC = RecordCodecBuilder.mapCodec((builder) -> builder.group(DyeColorUtil.CODEC.fieldOf("color").forGetter(ModBannerBlock::getDyenamicColor), propertiesCodec()).apply(builder, ModBannerBlock::new));
	private static final VoxelShape SHAPE = Block.box((double)4.0F, (double)0.0F, (double)4.0F, (double)12.0F, (double)16.0F, (double)12.0F);
	private final DyeColorUtil color;

	public ModBannerBlock(DyeColorUtil color, BlockBehaviour.Properties properties) {
		super(color.getAnalogue(), properties);
		this.color = color;
	}

	protected MapCodec<? extends AbstractBannerBlock> codec() {
		return CODEC;
	}

	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ModBannerBlockEntity(pos, state);
	}

	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
		return pLevel.getBlockState(pPos.below()).isSolid();
	}

	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPE;
	}

	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return (BlockState)this.defaultBlockState().setValue(BlockStateProperties.ROTATION_16, RotationSegment.convertToSegment(pContext.getRotation() + 180.0F));
	}

	public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
		return pFacing == Direction.DOWN && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
	}

	public BlockState rotate(BlockState pState, Rotation pRotation) {
		return (BlockState)pState.setValue(BlockStateProperties.ROTATION_16, pRotation.rotate((Integer)pState.getValue(BlockStateProperties.ROTATION_16), 16));
	}

	public BlockState mirror(BlockState pState, Mirror pMirror) {
		return (BlockState)pState.setValue(BlockStateProperties.ROTATION_16, pMirror.mirror((Integer)pState.getValue(BlockStateProperties.ROTATION_16), 16));
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(new Property[]{BlockStateProperties.ROTATION_16});
	}

	public DyeColorUtil getDyenamicColor() {
		return this.color;
	}
}
