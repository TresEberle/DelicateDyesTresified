package tres.delicate_dyes_tresified.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import tres.delicate_dyes_tresified.DelicateDyesTresified;
import tres.delicate_dyes_tresified.block.ModBlocks;

import java.util.concurrent.CompletableFuture;


public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, DelicateDyesTresified.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.TERRACOTTA)
            .add(ModBlocks.SACRAMENTO_TERRACOTTA.get().asItem());

        tag(ItemTags.WOOL)
            .add(ModBlocks.BLURPLE_WOOL.get().asItem())
            .add(ModBlocks.CANARY_WOOL.get().asItem())
            .add(ModBlocks.CORAL_WOOL.get().asItem())
            .add(ModBlocks.ROSE_WOOL.get().asItem())
            .add(ModBlocks.SACRAMENTO_WOOL.get().asItem())
            .add(ModBlocks.SANGRIA_WOOL.get().asItem())
            .add(ModBlocks.SKY_WOOL.get().asItem())
            .add(ModBlocks.WASABI_WOOL.get().asItem());

        tag(ItemTags.WOOL_CARPETS)
            .add(ModBlocks.SACRAMENTO_CARPET.get().asItem());


    }
}
