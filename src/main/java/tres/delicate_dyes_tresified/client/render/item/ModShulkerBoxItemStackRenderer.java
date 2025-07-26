package tres.delicate_dyes_tresified.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import tres.delicate_dyes_tresified.common.block.ModShulkerBoxBlock;
import tres.delicate_dyes_tresified.common.blockentity.ModShulkerBoxBlockEntity;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import java.util.Map;

public class ModShulkerBoxItemStackRenderer extends BlockEntityWithoutLevelRenderer {
	ModShulkerBoxBlockEntity blockEntity = null;

	public ModShulkerBoxItemStackRenderer() {
		super((BlockEntityRenderDispatcher)null, (EntityModelSet)null);
	}

	public void renderByItem(ItemStack pStack, ItemDisplayContext pTransformType, PoseStack pPoseStack, MultiBufferSource buffer, int pPackedLight, int pPackedOverlay) {
		if (this.blockEntity == null) {
			this.blockEntity = new ModShulkerBoxBlockEntity(BlockPos.ZERO, ((Block)((DeferredHolder)((Map) BlockInit.DYED_BLOCKS.get(DyeColorUtil.PEACH.getSerializedName())).get("shulker_box")).get()).defaultBlockState());
		}

		Item item = pStack.getItem();
		if (item instanceof BlockItem) {
			Block block = ((BlockItem)item).getBlock();
			if (block instanceof ModShulkerBoxBlock) {
				ModShulkerBoxBlock shulkerBoxBlock = (ModShulkerBoxBlock)block;
				this.blockEntity.setColor(shulkerBoxBlock.getDyenamicColor());
				Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(this.blockEntity, pPoseStack, buffer, pPackedLight, pPackedOverlay);
			}
		}

	}
}
