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
import tres.delicate_dyes_tresified.common.block.ModBedBlock;
import tres.delicate_dyes_tresified.common.blockentity.ModBedBlockEntity;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import java.util.Map;

public class ModBedItemStackRenderer extends BlockEntityWithoutLevelRenderer {
	ModBedBlockEntity blockEntity = null;

	public ModBedItemStackRenderer() {
		super((BlockEntityRenderDispatcher)null, (EntityModelSet)null);
	}

	public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource buffer, int pPackedLight, int pPackedOverlay) {
		if (this.blockEntity == null) {
			this.blockEntity = new ModBedBlockEntity(BlockPos.ZERO, ((Block)((DeferredHolder)((Map) BlockInit.DYED_BLOCKS.get(DyeColorUtil.PEACH.getSerializedName())).get("bed")).get()).defaultBlockState());
		}

		Item item = stack.getItem();
		if (item instanceof BlockItem) {
			Block block = ((BlockItem)item).getBlock();
			if (block instanceof ModBedBlock) {
				ModBedBlock bedBlock = (ModBedBlock)block;
				this.blockEntity.setColor(bedBlock.getDyenamicColor());
				Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(this.blockEntity, poseStack, buffer, pPackedLight, pPackedOverlay);
			}
		}

	}
}
