package tres.delicate_dyes_tresified.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import tres.delicate_dyes_tresified.common.blockentity.ModShulkerBoxBlockEntity;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.init.BlockEntityInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import javax.annotation.Nullable;
import java.util.Map;

public class ModShulkerBoxBlock extends ShulkerBoxBlock {
	private final DyeColorUtil color;

	public ModShulkerBoxBlock(DyeColorUtil colorIn, BlockBehaviour.Properties properties) {
		super(DyeColor.PURPLE, properties);
		this.color = colorIn;
	}

	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ModShulkerBoxBlockEntity(this.color, pPos, pState);
	}

	public DyeColorUtil getDyenamicColor() {
		return this.color;
	}

	public static ItemStack getDyenamicColoredItemStack(DyeColorUtil colorIn) {
		return new ItemStack((ItemLike)((DeferredHolder)((Map) BlockInit.DYED_BLOCKS.get(colorIn.getSerializedName())).get("shulker_box")).get());
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return createTickerHelper(pBlockEntityType, (BlockEntityType)BlockEntityInit.SHULKER_BOX.get(), ShulkerBoxBlockEntity::tick);
	}

	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof ShulkerBoxBlockEntity shulkerboxBlockEntity) {
			if (!level.isClientSide && player.isCreative() && !shulkerboxBlockEntity.isEmpty()) {
				ItemStack itemstack = getDyenamicColoredItemStack(this.getDyenamicColor());
				shulkerboxBlockEntity.saveToItem(itemstack, level.registryAccess());
				if (shulkerboxBlockEntity.hasCustomName()) {
					itemstack.set(DataComponents.ITEM_NAME, shulkerboxBlockEntity.getCustomName());
				}

				ItemEntity itementity = new ItemEntity(level, (double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, itemstack);
				itementity.setDefaultPickUpDelay();
				level.addFreshEntity(itementity);
			} else {
				shulkerboxBlockEntity.unpackLootTable(player);
			}
		}

		return super.playerWillDestroy(level, pos, state, player);
	}
}
