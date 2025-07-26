package tres.delicate_dyes_tresified.client.render.entity;

import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import tres.delicate_dyes_tresified.common.entity.ModSheep;

public class ModSheepRenderer extends MobRenderer<ModSheep, SheepModel<ModSheep>> {
	private static final ResourceLocation SHEARED_SHEEP_TEXTURES = ResourceLocation.withDefaultNamespace("textures/entity/sheep/sheep.png");

	public ModSheepRenderer(EntityRendererProvider.Context context) {
		super(context, new SheepModel(context.bakeLayer(ModelLayers.SHEEP)), 0.7F);
		this.addLayer(new ModSheepFurLayer(this, context.getModelSet()));
	}

	public ResourceLocation getTextureLocation(ModSheep sheep) {
		return SHEARED_SHEEP_TEXTURES;
	}
}
