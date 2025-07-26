package tres.delicate_dyes_tresified.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import tres.delicate_dyes_tresified.DelicateDyesTresified;
import tres.delicate_dyes_tresified.common.blockentity.ModBedBlockEntity;
import tres.delicate_dyes_tresified.core.init.BlockEntityInit;

public class ModBedRenderer implements BlockEntityRenderer<ModBedBlockEntity> {
	private final ModelPart headRoot;
	private final ModelPart footRoot;

	public ModBedRenderer(BlockEntityRendererProvider.Context pContext) {
		this.headRoot = pContext.bakeLayer(ModelLayers.BED_HEAD);
		this.footRoot = pContext.bakeLayer(ModelLayers.BED_FOOT);
	}

	public void render(ModBedBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
		Material material = (Material) DelicateDyesTresified.BED_MATERIAL_MAP.get(pBlockEntity.getColor().getSerializedName());
		Level level = pBlockEntity.getLevel();
		if (level != null) {
			BlockState blockstate = pBlockEntity.getBlockState();
			DoubleBlockCombiner.NeighborCombineResult<? extends ModBedBlockEntity> neighborcombineresult = DoubleBlockCombiner.combineWithNeigbour((BlockEntityType) BlockEntityInit.BED.get(), BedBlock::getBlockType, BedBlock::getConnectedDirection, ChestBlock.FACING, blockstate, level, pBlockEntity.getBlockPos(), (p_112202_, p_112203_) -> false);
			int i = ((Int2IntFunction)neighborcombineresult.apply(new BrightnessCombiner())).get(pPackedLight);
			this.renderPiece(pPoseStack, pBufferSource, blockstate.getValue(BedBlock.PART) == BedPart.HEAD ? this.headRoot : this.footRoot, (Direction)blockstate.getValue(BedBlock.FACING), material, i, pPackedOverlay, false);
		} else {
			this.renderPiece(pPoseStack, pBufferSource, this.headRoot, Direction.SOUTH, material, pPackedLight, pPackedOverlay, false);
			this.renderPiece(pPoseStack, pBufferSource, this.footRoot, Direction.SOUTH, material, pPackedLight, pPackedOverlay, true);
		}

	}

	private void renderPiece(PoseStack pPoseStack, MultiBufferSource pBufferSource, ModelPart pModelPart, Direction pDirection, Material pMaterial, int pPackedLight, int pPackedOverlay, boolean pFoot) {
		pPoseStack.pushPose();
		pPoseStack.translate((double)0.0F, (double)0.5625F, pFoot ? (double)-1.0F : (double)0.0F);
		pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
		pPoseStack.translate((double)0.5F, (double)0.5F, (double)0.5F);
		pPoseStack.mulPose(Axis.ZP.rotationDegrees(180.0F + pDirection.toYRot()));
		pPoseStack.translate((double)-0.5F, (double)-0.5F, (double)-0.5F);
		VertexConsumer vertexconsumer = pMaterial.buffer(pBufferSource, RenderType::entitySolid);
		pModelPart.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
		pPoseStack.popPose();
	}
}
