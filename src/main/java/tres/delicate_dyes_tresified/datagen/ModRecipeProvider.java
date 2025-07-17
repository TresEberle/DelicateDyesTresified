package tres.delicate_dyes_tresified.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import tres.delicate_dyes_tresified.block.ModBlocks;
import tres.delicate_dyes_tresified.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput){

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CANARY_WOOL.get(), 1)
                .requires(ModItems.CANARY_DYE)
                .requires(ItemTags.WOOL)
                .unlockedBy("has_canary_dye", has(ModItems.CANARY_DYE))
                .save(recipeOutput, "delicate_dyes_tresified:canary_wool_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SACRAMENTO_WOOL.get(), 1)
                .requires(ModItems.SACRAMENTO_DYE)
                .requires(ItemTags.WOOL)
                .unlockedBy("has_sacramento_dye", has(ModItems.SACRAMENTO_DYE))
                .save(recipeOutput, "delicate_dyes_tresified:sacramento_wool_from_dye");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SACRAMENTO_CARPET.get(), 3)
                .pattern("##")
                .define('#', ModBlocks.SACRAMENTO_WOOL.get())
                .unlockedBy("has_sacramento_wool", has(ModBlocks.SACRAMENTO_WOOL))
                .save(recipeOutput, "delicate_dyes_tresified:sacramento_carpet_from_wool");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SACRAMENTO_TERRACOTTA.get(), 8)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .define('#', ItemTags.TERRACOTTA)
                .define('X', ModItems.SACRAMENTO_DYE)
                .unlockedBy("has_sacramento_dye", has(ModItems.SACRAMENTO_DYE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SACRAMENTO_CONCRETE_POWDER.get(), 8)
                .requires(ModItems.SACRAMENTO_DYE)
                .requires(Blocks.SAND)
                .requires(Blocks.SAND)
                .requires(Blocks.SAND)
                .requires(Blocks.SAND)
                .requires(Blocks.GRAVEL)
                .requires(Blocks.GRAVEL)
                .requires(Blocks.GRAVEL)
                .requires(Blocks.GRAVEL)
                .unlockedBy("has_sacramento_dye", has(ModItems.SACRAMENTO_DYE))
                .save(recipeOutput);

        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(ModBlocks.SACRAMENTO_TERRACOTTA.get()),           // ingredient
                        RecipeCategory.BUILDING_BLOCKS,                                 // category
                        ModBlocks.SACRAMENTO_GLAZED_TERRACOTTA.get(),                   // result
                        0.1f,                                                            // experience
                        200                                                             // cooking time (ticks)
                )
                .unlockedBy("has_sacramento_terracotta", has(ModBlocks.SACRAMENTO_TERRACOTTA.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SACRAMENTO_STAINED_GLASS.get(), 8)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .define('#', Blocks.GLASS)
                .define('X', ModItems.SACRAMENTO_DYE)
                .unlockedBy("has_sacramento_dye", has(ModItems.SACRAMENTO_DYE))
                .save(recipeOutput, "delicate_dyes_tresified:sacramento_stained_glass_regular");

        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(ModBlocks.SACRAMENTO_CONCRETE_POWDER.get()),
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SACRAMENTO_STAINED_GLASS.get(),
                        0.1f,
                        200
                )
                .unlockedBy("has_sacramento_concrete_powder", has(ModBlocks.SACRAMENTO_CONCRETE_POWDER.get()))
                .save(recipeOutput, "delicate_dyes_tresified:sacramento_stained_glass_from_concrete_powder");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModItems.SACRAMENTO_DYE.get(), 2)
                .requires(Items.GREEN_DYE)
                .requires(Items.CYAN_DYE)
                .unlockedBy("has_green_dye", has(Items.GREEN_DYE))
                .unlockedBy("has_cyan_dye", has(Items.CYAN_DYE))
                .save(recipeOutput, "delicate_dyes_tresified:sacramento_dye_from_green_and_cyan");

    }
}
