package tres.delicate_dyes_tresified.core.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tres.delicate_dyes_tresified.common.block.*;
import tres.delicate_dyes_tresified.common.item.ModBannerItem;
import tres.delicate_dyes_tresified.common.item.ModBedBlockItem;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;
import tres.delicate_dyes_tresified.common.item.ModShulkerBlockItem;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS;
	public static final Map<String, Map<String, DeferredHolder<Block, Block>>> DYED_BLOCKS;
	public static final Map<String, Map<String, DeferredHolder<Block, Block>>> VANILLA_EXTENSIONS = new HashMap<>();

	public static void register() {
		for(DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
			registerDyeBlocks(color);
		}
		for(net.minecraft.world.item.DyeColor color : net.minecraft.world.item.DyeColor.values()) {
			registerVanillaDyeBlocks(color);
		}

	}

	public static synchronized void registerDyeBlocks(DyeColorUtil color) {
		String colorName = color.getSerializedName();
		int light = color.getLightValue();
		MapColor mapColor = color.getMapColor();
		DyeColor analogue = color.getAnalogue();
		Map<String, DeferredHolder<Block, Block>> blocks = new HashMap();
		DYED_BLOCKS.put(colorName, blocks);

		registerBlockAndItem(colorName, "terracotta", blocks, BlockItem::new, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_TERRACOTTA).mapColor(mapColor).lightLevel((state) -> light)));
		registerBlockAndItem(colorName, "terracotta_slab", blocks, BlockItem::new, () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_TERRACOTTA).mapColor(mapColor).lightLevel((state) -> light)));
		registerBlockAndItem(colorName, "glazed_terracotta", blocks, BlockItem::new, () -> new GlazedTerracottaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_GLAZED_TERRACOTTA).mapColor(analogue).lightLevel((state) -> light)));
		DeferredHolder<Block, Block> concrete = registerBlockAndItem(colorName, "concrete", blocks, BlockItem::new, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE).mapColor(analogue).lightLevel((state) -> light)));
		registerBlockAndItem(colorName, "concrete_powder", blocks, BlockItem::new, () -> new ConcretePowderBlock((Block)concrete.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE_POWDER).mapColor(analogue).lightLevel((state) -> light)));
		registerBlockAndItem(colorName, "wool", blocks, BlockItem::new, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(mapColor).lightLevel((state) -> light)));
		registerBlockAndItem(colorName, "carpet", blocks, BlockItem::new, () -> new ModCarpetBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CARPET).mapColor(mapColor).lightLevel((state) -> light)));
		registerBlockAndItem(colorName, "stained_glass", blocks, BlockItem::new, () -> new ModStainedGlassBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(mapColor).lightLevel((state) -> light)));
		registerBlockAndItem(colorName, "stained_glass_pane", blocks, BlockItem::new, () -> new ModStainedGlassPane(color, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS_PANE).mapColor(mapColor).lightLevel((state) -> light)));
		DeferredHolder<Block, Block> candle = registerBlockAndItem(colorName, "candle", blocks, BlockItem::new, () -> new CandleBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CANDLE).mapColor(mapColor).lightLevel((state) -> {
			int candleLight = CandleBlock.LIGHT_EMISSION.applyAsInt(state);
			return Math.max(light, candleLight);
		})));
		registerBlockAndItem(colorName, "candle_cake", blocks, (BlockItemSupplier)null, () -> new CandleCakeBlock((Block)candle.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.CANDLE_CAKE).lightLevel((state) -> light > 0 ? light : ((Boolean)state.getValue(BlockStateProperties.LIT) ? 3 : 0))));
		registerBedAndItem(colorName, "bed", blocks, ModBedBlockItem::new, () -> new ModBedBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_BED).mapColor((state) -> state.getValue(BedBlock.PART) == BedPart.FOOT ? mapColor : MapColor.WOOL).lightLevel((state) -> light)));
		registerShulkerBoxAndItem(colorName, "shulker_box", blocks, ModShulkerBlockItem::new, () -> new ModShulkerBoxBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.SHULKER_BOX).lightLevel((state) -> light).mapColor(mapColor)));
		DeferredHolder<Block, Block> wallBanner = registerBlockAndItem(colorName, "wall_banner", blocks, (BlockItemSupplier)null, () -> new ModWallBannerBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_BANNER).lightLevel((state) -> light)));
		registerBannerBlockAndItem(colorName, "banner", blocks, () -> new ModBannerBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_BANNER).lightLevel((state) -> light)), wallBanner);
	}

	public static synchronized void registerVanillaDyeBlocks(DyeColor color) {
		// Get the lowercase name of the color (e.g., "white", "orange")
		String colorName = color.getName();

		// Create a new map for the vanilla color's blocks
		Map<String, DeferredHolder<Block, Block>> blocks = new HashMap<>();
		VANILLA_EXTENSIONS.put(colorName, blocks);

		// Build the resource location for the colored terracotta block
		// This correctly gets the specific block instance from the game's registry
		ResourceLocation terracottaId = ResourceLocation.fromNamespaceAndPath("minecraft", colorName + "_terracotta");
		Block vanillaTerracotta = BuiltInRegistries.BLOCK.get(terracottaId);

		// A fallback check in case the block doesn't exist for some reason
		if (vanillaTerracotta == null) {
			System.err.println("Could not find vanilla terracotta block for color: " + colorName);
			return;
		}

		// Register the slab block for the vanilla terracotta
		registerBlockAndItem(colorName, "terracotta_slab", blocks, BlockItem::new,
				() -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(vanillaTerracotta)));

	}

	public static synchronized DeferredHolder<Block, Block> registerBedAndItem(String color, String nameSuffix, Map<String, DeferredHolder<Block, Block>> blockMap, BlockItemSupplier<?> itemSupplier, Supplier<Block> supplier) {
		return registerBlockAndItem(color, nameSuffix, blockMap, itemSupplier, supplier, (new Item.Properties()).stacksTo(1));
	}

	public static synchronized DeferredHolder<Block, Block> registerShulkerBoxAndItem(String color, String nameSuffix, Map<String, DeferredHolder<Block, Block>> blockMap, BlockItemSupplier<?> itemSupplier, Supplier<Block> supplier) {
		return registerBlockAndItem(color, nameSuffix, blockMap, itemSupplier, supplier, (new Item.Properties()).stacksTo(1));
	}

	public static synchronized DeferredHolder<Block, Block> registerBlockAndItem(String color, String nameSuffix, Map<String, DeferredHolder<Block, Block>> blockMap, BlockItemSupplier<?> itemSupplier, Supplier<Block> supplier) {
		return registerBlockAndItem(color, nameSuffix, blockMap, itemSupplier, supplier, new Item.Properties());
	}

	public static synchronized DeferredHolder<Block, Block> registerBlockAndItem(String color, String nameSuffix, Map<String, DeferredHolder<Block, Block>> blockMap, BlockItemSupplier<?> itemSupplier, Supplier<Block> supplier, Item.Properties itemProperties) {
		String name = color + "_" + nameSuffix;
		DeferredHolder<Block, Block> block = BLOCKS.register(name, supplier);
		if (itemSupplier != null) {
			ItemInit.ITEMS.register(name, () -> itemSupplier.create((Block)block.get(), itemProperties));
		}

		blockMap.put(nameSuffix, block);
		return block;
	}

	public static synchronized DeferredHolder<Block, Block> registerBannerBlockAndItem(String color, String nameSuffix, Map<String, DeferredHolder<Block, Block>> blockMap, Supplier<Block> banner, DeferredHolder<Block, Block> wallBanner) {
		String name = color + "_" + nameSuffix;
		DeferredHolder<Block, Block> block = BLOCKS.register(name, banner);
		ItemInit.ITEMS.register(name, () -> new ModBannerItem((Block)block.get(), (Block)wallBanner.get(), (new Item.Properties()).stacksTo(16).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));
		blockMap.put(nameSuffix, block);
		return block;
	}

	static {
		BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, "delicate_dyes_tresified");
		DYED_BLOCKS = new HashMap();
	}

	@FunctionalInterface
	public interface BlockItemSupplier<T extends BlockItem> {
		T create(Block var1, Item.Properties var2);
	}
}