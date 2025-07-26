package tres.delicate_dyes_tresified.core.init;

import com.mojang.datafixers.types.Type;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tres.delicate_dyes_tresified.common.blockentity.ModBannerBlockEntity;
import tres.delicate_dyes_tresified.common.blockentity.ModBedBlockEntity;
import tres.delicate_dyes_tresified.common.blockentity.ModShulkerBoxBlockEntity;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public class BlockEntityInit {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES;
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModBedBlockEntity>> BED;
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModShulkerBoxBlockEntity>> SHULKER_BOX;
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModBannerBlockEntity>> BANNER;

	static {
		BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, "delicate_dyes_tresified");
		BED = BLOCK_ENTITY_TYPES.register("bed", () -> BlockEntityType.Builder.of(ModBedBlockEntity::new, (Block[]) Arrays.stream(DyeColorUtil.dyenamicValues()).map((DyeColorUtil) -> (Block)((DeferredHolder)((Map) BlockInit.DYED_BLOCKS.get(DyeColorUtil.getSerializedName())).get("bed")).get()).toList().toArray(new Block[0])).build((Type)null));
		SHULKER_BOX = BLOCK_ENTITY_TYPES.register("shulker_box", () -> BlockEntityType.Builder.of(ModShulkerBoxBlockEntity::new, (Block[])Arrays.stream(DyeColorUtil.dyenamicValues()).map((DyeColorUtil) -> (Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(DyeColorUtil.getSerializedName())).get("shulker_box")).get()).toList().toArray(new Block[0])).build((Type)null));
		BANNER = BLOCK_ENTITY_TYPES.register("banner", () -> BlockEntityType.Builder.of(ModBannerBlockEntity::new, (Block[])Arrays.stream(DyeColorUtil.dyenamicValues()).flatMap((DyeColorUtil) -> Stream.of((Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(DyeColorUtil.getSerializedName())).get("banner")).get(), (Block)((DeferredHolder)((Map)BlockInit.DYED_BLOCKS.get(DyeColorUtil.getSerializedName())).get("wall_banner")).get())).toList().toArray(new Block[0])).build((Type)null));
	}
}
