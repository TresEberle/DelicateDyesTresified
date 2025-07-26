package tres.delicate_dyes_tresified.data;

import com.google.common.collect.Maps;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.PackOutput.PathProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Stream;

public class LootDataProvider implements DataProvider {
	private final PathProvider pathProvider;
	private final List<LootTableProvider.SubProviderEntry> subProviders;
	private final CompletableFuture<HolderLookup.Provider> registries;

	public LootDataProvider(PackOutput output, List<LootTableProvider.SubProviderEntry> providers, CompletableFuture<HolderLookup.Provider> registries) {
		this.pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "loot_table");
		this.subProviders = providers;
		this.registries = registries;
	}

	@Override
	public String getName() {
		return "Delicate Dyes Tresified Loot Datagen";
	}

	@Override
	public CompletableFuture<?> run(CachedOutput pOutput) {
		return this.registries.thenCompose((provider) -> this.run(pOutput, provider));
	}

	private CompletableFuture<?> run(CachedOutput pOutput, HolderLookup.Provider pProvider) {
		Map<ResourceLocation, LootTable> map = Maps.newHashMap();
		this.subProviders.forEach((providerEntry) ->
				((LootTableSubProvider) providerEntry.provider().apply(pProvider))
						.generate((resourceKey, builder) -> {
							builder.setRandomSequence(resourceKey.location());
							if (map.put(resourceKey.location(), builder.setParamSet(providerEntry.paramSet()).build()) != null) {
								throw new IllegalStateException("Duplicate loot table " + resourceKey.location());
							}
						})
		);
		return CompletableFuture.allOf(
				map.entrySet().stream()
						.map((entry) -> DataProvider.saveStable(
								pOutput,
								pProvider,
								LootTable.DIRECT_CODEC,
								entry.getValue(),
								this.pathProvider.json(entry.getKey())
						))
						.toArray(CompletableFuture[]::new)
		);
	}

	// === Block Loot Provider ===
	public static class BlockProvider extends BlockLootSubProvider {
		private static final Map<Block, Function<Block, LootTable.Builder>> functionTable = new HashMap<>();
		private final List<Block> knownBlocks = new ArrayList<>();

		public BlockProvider(HolderLookup.Provider registries) {
			super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
		}

		@Override
		protected void generate() {
			for (DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
				this.dropBanner((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("banner")).get());
				this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("bed")).get(), (Function)((block) -> this.createSinglePropConditionTable((Block) block, BedBlock.PART, BedPart.HEAD)));
				this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("candle")).get(), (Function)((block) -> this.createCandleDrops((Block) block)));
				this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("candle_cake")).get(), createCandleCakeDrops((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("candle_cake")).get()));
				this.dropSelf((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("carpet")).get());
				this.dropSelf((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("concrete")).get());
				this.dropSelf((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("concrete_powder")).get());
				this.dropSelf((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("glazed_terracotta")).get());
				this.add((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("shulker_box")).get(), (Function)((block) -> this.createShulkerBoxDrop((Block) block)));
				this.dropWhenSilkTouch((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass")).get());
				this.dropWhenSilkTouch((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("stained_glass_pane")).get());
				this.dropSelf((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("terracotta")).get());
				this.dropOtherBanner((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("wall_banner")).get(), (Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("banner")).get());
				this.dropSelf((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("wool")).get());
			}
		}

		protected void add(Block block, LootTable.Builder builder) {
			super.add(block, builder);
			this.knownBlocks.add(block);
		}

		protected void add(Block block, Function<Block, LootTable.Builder> builderFunction) {
			this.add(block, builderFunction.apply(block));
		}

		protected Iterable<Block> getKnownBlocks() {
			return this.knownBlocks;
		}

		public void dropSelf(@NotNull Block block) {
			Function<Block, LootTable.Builder> func = functionTable.getOrDefault(block, BlockProvider::genOptionalBlockDrop);
			this.add(block, func.apply(block));
		}

		public void dropOther(@NotNull Block block, @NotNull Block otherBlock) {
			Function<Block, LootTable.Builder> func = functionTable.getOrDefault(block, BlockProvider::genOptionalBlockDrop);
			this.add(block, func.apply(otherBlock));
		}

		public void dropBanner(@NotNull Block block) {
			Function<Block, LootTable.Builder> func = functionTable.getOrDefault(block, super::createBannerDrop);
			this.add(block, func.apply(block));
		}

		public void dropOtherBanner(@NotNull Block block, @NotNull Block otherBlock) {
			Function<Block, LootTable.Builder> func = functionTable.getOrDefault(block, super::createBannerDrop);
			this.add(block, func.apply(otherBlock));
		}

		public void dropNothing(@NotNull Block block) {
			Function<Block, LootTable.Builder> func = functionTable.getOrDefault(block, BlockProvider::genBlankBlockDrop);
			this.add(block, func.apply(block));
		}

		protected static LootTable.Builder genOptionalBlockDrop(Block block) {
			return LootTable.lootTable().withPool(
					LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1.0F))
							.add(LootItem.lootTableItem(block).when(ExplosionCondition.survivesExplosion()))
			);
		}

		protected static LootTable.Builder genBlankBlockDrop(Block block) {
			return LootTable.lootTable();
		}
	}

	// === Entity Loot Provider ===
	public static class EntityLootProvider extends EntityLootSubProvider {
		private final List<EntityType<?>> knownEntities = new ArrayList<>();

		public EntityLootProvider(HolderLookup.Provider registries) {
			super(FeatureFlags.REGISTRY.allFlags(), registries);
		}

		@Override
		public void generate() {
			for (DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
				;
			}
		}

		private void addSheep(DyeColorUtil color) {
			Block item = (Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("wool")).get();
			LootTable.Builder loot = LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(item)));
			this.add(EntityType.SHEEP, loot);
		}

		protected void add(EntityType<?> type, LootTable.Builder builder) {
			super.add(type, builder);
			this.knownEntities.add(type);
		}

		protected Stream<EntityType<?>> getKnownEntityTypes() {
			return this.knownEntities.stream();
		}
	}
}