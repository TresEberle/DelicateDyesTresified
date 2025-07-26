package tres.delicate_dyes_tresified.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ShulkerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.state.BlockState;
import tres.delicate_dyes_tresified.DelicateDyesTresified;
import tres.delicate_dyes_tresified.common.blockentity.ModShulkerBoxBlockEntity;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

public class ModShulkerBoxBlockEntityRenderer implements BlockEntityRenderer<ModShulkerBoxBlockEntity> {
	private final ShulkerModel<?> model;

	public ModShulkerBoxBlockEntityRenderer(BlockEntityRendererProvider.Context pContext) {
		this.model = new ShulkerModel(pContext.bakeLayer(ModelLayers.SHULKER));
	}

	public void render(ModShulkerBoxBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
		Direction direction = Direction.UP;
		if (pBlockEntity.hasLevel()) {
			BlockState blockstate = pBlockEntity.getLevel().getBlockState(pBlockEntity.getBlockPos());
			if (blockstate.getBlock() instanceof ShulkerBoxBlock) {
				direction = (Direction)blockstate.getValue(ShulkerBoxBlock.FACING);
			}
		}

		DyeColorUtil dyecolor = pBlockEntity.getDyenamicColor();
		Material material = Sheets.DEFAULT_SHULKER_TEXTURE_LOCATION;
		if (dyecolor != null && DelicateDyesTresified.SHULKER_MATERIAL_MAP.containsKey(dyecolor.getSerializedName())) {
			material = (Material)DelicateDyesTresified.SHULKER_MATERIAL_MAP.get(dyecolor.getSerializedName());
		}

		pPoseStack.pushPose();
		pPoseStack.translate((double)0.5F, (double)0.5F, (double)0.5F);
		pPoseStack.scale(0.9995F, 0.9995F, 0.9995F);
		pPoseStack.mulPose(direction.getRotation());
		pPoseStack.scale(1.0F, -1.0F, -1.0F);
		pPoseStack.translate((double)0.0F, (double)-1.0F, (double)0.0F);
		ModelPart modelpart = this.model.getLid();
		modelpart.setPos(0.0F, 24.0F - pBlockEntity.getProgress(pPartialTick) * 0.5F * 16.0F, 0.0F);
		modelpart.yRot = 270.0F * pBlockEntity.getProgress(pPartialTick) * ((float)Math.PI / 180F);
		VertexConsumer vertexconsumer = material.buffer(pBufferSource, RenderType::entityCutoutNoCull);
		this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, -1);
		pPoseStack.popPose();
	}
}
