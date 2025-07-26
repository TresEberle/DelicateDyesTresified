package tres.delicate_dyes_tresified.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import tres.delicate_dyes_tresified.common.block.ModBannerBlock;
import tres.delicate_dyes_tresified.common.blockentity.ModBannerBlockEntity;
import tres.delicate_dyes_tresified.core.init.BlockInit;

import java.util.Map;

public class ModBannerItemStackRenderer extends BlockEntityWithoutLevelRenderer {
	ModBannerBlockEntity blockEntity = null;

	public ModBannerItemStackRenderer() {
		super((BlockEntityRenderDispatcher)null, (EntityModelSet)null);
	}

	public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource buffer, int pPackedLight, int pPackedOverlay) {
		if (this.blockEntity == null) {
			this.blockEntity = new ModBannerBlockEntity(BlockPos.ZERO, ((Block)((DeferredHolder)((Map) BlockInit.DYED_BLOCKS.get("mint")).get("banner")).get()).defaultBlockState());
		}

		this.blockEntity.fromItem(stack, DyeColor.RED);
		Item item = stack.getItem();
		if (item instanceof BlockItem) {
			Block block = ((BlockItem)item).getBlock();
			if (block instanceof ModBannerBlock) {
				ModBannerBlock bannerBlock = (ModBannerBlock)block;
				this.blockEntity.setDyenamicColor(bannerBlock.getDyenamicColor());
				Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(this.blockEntity, poseStack, buffer, pPackedLight, pPackedOverlay);
			}
		}

	}
}