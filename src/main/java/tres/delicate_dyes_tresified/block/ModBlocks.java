package tres.delicate_dyes_tresified.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import tres.delicate_dyes_tresified.DelicateDyesTresified;
import tres.delicate_dyes_tresified.item.ModItems;
import tres.delicate_dyes_tresified.item.custom.ColoredDyeItem;

import java.util.function.Supplier;

public class ModBlocks {
	public static final DeferredRegister.Blocks BLOCKS =
			DeferredRegister.createBlocks(DelicateDyesTresified.MOD_ID);

	public static final DeferredBlock<Block> BLURPLE_WOOL = registerBlock("blurple_wool",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).ignitedByLava()));

	public static final DeferredBlock<Block> CANARY_WOOL = registerBlock("canary_wool",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).ignitedByLava()));

	public static final DeferredBlock<Block> CORAL_WOOL = registerBlock("coral_wool",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).ignitedByLava()));

	public static final DeferredBlock<Block> ROSE_WOOL = registerBlock("rose_wool",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).ignitedByLava()));


	public static final DeferredBlock<Block> SACRAMENTO_WOOL = registerBlock("sacramento_wool",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).ignitedByLava()));

	public static final DeferredBlock<WoolCarpetBlock> SACRAMENTO_CARPET = registerBlock("sacramento_carpet",
			() -> new WoolCarpetBlock(DyeColor.PURPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_CARPET )));

	public static final DeferredBlock<Block> SACRAMENTO_TERRACOTTA = registerBlock("sacramento_terracotta",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_TERRACOTTA)));

	public static final DeferredBlock<Block> SACRAMENTO_CONCRETE = registerBlock("sacramento_concrete",
			() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_CONCRETE)));

	public static final DeferredBlock<ConcretePowderBlock> SACRAMENTO_CONCRETE_POWDER = registerBlock("sacramento_concrete_powder",
			() -> new ConcretePowderBlock(SACRAMENTO_CONCRETE.get(),BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_CONCRETE_POWDER)));

	public static final DeferredBlock<GlazedTerracottaBlock> SACRAMENTO_GLAZED_TERRACOTTA = registerBlock("sacramento_glazed_terracotta",
			() -> new GlazedTerracottaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_GLAZED_TERRACOTTA)));

	public static final DeferredBlock<StainedGlassBlock> SACRAMENTO_STAINED_GLASS = registerBlock("sacramento_stained_glass",
			() -> new StainedGlassBlock(DyeColor.PURPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_STAINED_GLASS)));

	public static final DeferredBlock<StainedGlassPaneBlock> SACRAMENTO_STAINED_GLASS_PANE = registerBlock("sacramento_stained_glass_pane",
			() -> new StainedGlassPaneBlock(DyeColor.PURPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_STAINED_GLASS)));

	public static final DeferredBlock<ShulkerBoxBlock> SACRAMENTO_SHULKER_BOX= registerBlock("sacramento_shulker_box",
			() -> new ShulkerBoxBlock(DyeColor.PURPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.SHULKER_BOX)));

	public static final DeferredBlock<Block> SANGRIA_WOOL = registerBlock("sangria_wool",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).ignitedByLava()));

	public static final DeferredBlock<Block> SKY_WOOL = registerBlock("sky_wool",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).ignitedByLava()));

	public static final DeferredBlock<Block> WASABI_WOOL = registerBlock("wasabi_wool",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).ignitedByLava()));




	private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
		DeferredBlock<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
		ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	public static void register(IEventBus eventBus){
		BLOCKS.register(eventBus);
	}
}
