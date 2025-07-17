package tres.delicate_dyes_tresified.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import tres.delicate_dyes_tresified.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {

    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModBlocks.BLURPLE_WOOL.getId(), new FurnaceFuel(100), false)
                .add(ModBlocks.CANARY_WOOL.getId(), new FurnaceFuel(100), false)
                .add(ModBlocks.CORAL_WOOL.getId(), new FurnaceFuel(100), false)
                .add(ModBlocks.ROSE_WOOL.getId(), new FurnaceFuel(100), false)
                .add(ModBlocks.SACRAMENTO_WOOL.getId(), new FurnaceFuel(100), false)
                .add(ModBlocks.SANGRIA_WOOL.getId(), new FurnaceFuel(100), false)
                .add(ModBlocks.SKY_WOOL.getId(), new FurnaceFuel(100), false)
                .add(ModBlocks.WASABI_WOOL.getId(), new FurnaceFuel(100), false);
    }
}
