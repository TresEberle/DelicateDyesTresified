package tres.delicate_dyes_tresified.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallBannerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import tres.delicate_dyes_tresified.common.blockentity.ModBannerBlockEntity;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

public class ModWallBannerBlock extends WallBannerBlock {
	private final DyeColorUtil color;

	public ModWallBannerBlock(DyeColorUtil color, BlockBehaviour.Properties properties) {
		super(color.getAnalogue(), properties);
		this.color = color;
	}

	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ModBannerBlockEntity(pos, state);
	}

	public DyeColorUtil getDyenamicColor() {
		return this.color;
	}
}
