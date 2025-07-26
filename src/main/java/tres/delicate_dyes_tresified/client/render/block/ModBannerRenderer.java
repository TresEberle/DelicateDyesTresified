package tres.delicate_dyes_tresified.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.WallBannerBlock;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import tres.delicate_dyes_tresified.common.block.ModBannerBlock;
import tres.delicate_dyes_tresified.common.blockentity.ModBannerBlockEntity;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

public class ModBannerRenderer extends BannerRenderer {
	private final ModelPart pole;
	private final ModelPart bar;
	private final ModelPart flag;

	public ModBannerRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
		ModelPart root = context.bakeLayer(ModelLayers.BANNER); // or your custom LayerLocation if you're using one
		this.pole = root.getChild("pole");
		this.bar = root.getChild("bar");
		this.flag = root.getChild("flag");
	}

	public void render(BannerBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
		boolean isItem = pBlockEntity.getLevel() == null;
		pPoseStack.pushPose();
		long i;
		if (isItem) {
			i = 0L;
			pPoseStack.translate(0.5F, 0.5F, 0.5F);
			this.pole.visible = true;
		} else {
			i = pBlockEntity.getLevel().getGameTime();
			BlockState blockstate = pBlockEntity.getBlockState();
			if (blockstate.getBlock() instanceof ModBannerBlock) {
				pPoseStack.translate(0.5F, 0.5F, 0.5F);
				float f1 = -RotationSegment.convertToDegrees((Integer)blockstate.getValue(BannerBlock.ROTATION));
				pPoseStack.mulPose(Axis.YP.rotationDegrees(f1));
				this.pole.visible = true;
			} else {
				pPoseStack.translate(0.5F, -0.16666667F, 0.5F);
				float f3 = -((Direction)blockstate.getValue(WallBannerBlock.FACING)).toYRot();
				pPoseStack.mulPose(Axis.YP.rotationDegrees(f3));
				pPoseStack.translate(0.0F, -0.3125F, -0.4375F);
				this.pole.visible = false;
			}
		}

		pPoseStack.pushPose();
		pPoseStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
		VertexConsumer vertexconsumer = ModelBakery.BANNER_BASE.buffer(pBuffer, RenderType::entitySolid);
		this.pole.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
		this.bar.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
		BlockPos blockpos = pBlockEntity.getBlockPos();
		float f2 = ((float)Math.floorMod((long)(blockpos.getX() * 7 + blockpos.getY() * 9 + blockpos.getZ() * 13) + i, 100L) + pPartialTick) / 100.0F;
		this.flag.xRot = (-0.0125F + 0.01F * Mth.cos(((float)Math.PI * 2F) * f2)) * (float)Math.PI;
		this.flag.y = -32.0F;
		DyeColorUtil var10000;
		if (pBlockEntity instanceof ModBannerBlockEntity dyenamicBannerBlockEntity) {
			var10000 = dyenamicBannerBlockEntity.getDyenamicColor();
		} else {
			var10000 = DyeColorUtil.getColor(pBlockEntity.getBaseColor());
		}

		DyeColorUtil baseColor = var10000;
		renderPatterns(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, this.flag, ModelBakery.BANNER_BASE, true, baseColor, pBlockEntity.getPatterns(), false);
		pPoseStack.popPose();
		pPoseStack.popPose();
	}

	public static void renderPatterns(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay, ModelPart pFlagPart, Material pFlagMaterial, boolean pBanner, DyeColorUtil pBaseColor, BannerPatternLayers pPatterns, boolean pGlint) {
		pFlagPart.render(pPoseStack, pFlagMaterial.buffer(pBuffer, RenderType::entitySolid, pGlint), pPackedLight, pPackedOverlay);
		renderPatternLayer(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pFlagPart, pBanner ? Sheets.BANNER_BASE : Sheets.SHIELD_BASE, pBaseColor);

		for(int i = 0; i < 16 && i < pPatterns.layers().size(); ++i) {
			BannerPatternLayers.Layer patternLayer = (BannerPatternLayers.Layer)pPatterns.layers().get(i);
			Material material = pBanner ? Sheets.getBannerMaterial(patternLayer.pattern()) : Sheets.getShieldMaterial(patternLayer.pattern());
			renderPatternLayer(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pFlagPart, material, DyeColorUtil.getColor(patternLayer.color()));
		}

	}

	private static void renderPatternLayer(PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, ModelPart flagPart, Material material, DyeColorUtil color) {
		int i = color.getColorComponentValue();
		flagPart.render(poseStack, material.buffer(buffer, RenderType::entityNoOutline), packedLight, packedOverlay, i);
	}
}
