package tres.delicate_dyes_tresified.core.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tres.delicate_dyes_tresified.common.recipe.ModShulkerBoxColoringRecipe;

public class RecipeSerializerInit {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS;
	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ModShulkerBoxColoringRecipe>> SHULKER;
//	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<DyenamicArmorColoringRecipe>> ARMOR;
//	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<DyenamicFireworkStarRecipe>> FIREWORK_STAR;
//
	static {
		RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, "delicate_dyes_tresified");
		SHULKER = RECIPE_SERIALIZERS.register("shulker_box_coloring", () -> new SimpleCraftingRecipeSerializer(ModShulkerBoxColoringRecipe::new));
//		ARMOR = RECIPE_SERIALIZERS.register("armor_coloring", () -> new SimpleCraftingRecipeSerializer(DyenamicArmorColoringRecipe::new));
//		FIREWORK_STAR = RECIPE_SERIALIZERS.register("firework_star", () -> new SimpleCraftingRecipeSerializer(DyenamicFireworkStarRecipe::new));
	}
}
