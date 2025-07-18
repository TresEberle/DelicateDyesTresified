package tres.delicate_dyes_tresified.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import tres.delicate_dyes_tresified.DelicateDyesTresified;
import tres.delicate_dyes_tresified.color.ModColor;
import tres.delicate_dyes_tresified.item.custom.ColoredDyeItem;

public class ModItems {
	public static final DeferredRegister.Items ITEMS =
			DeferredRegister.createItems(DelicateDyesTresified.MOD_ID);

	public static final DeferredItem<Item> BLURPLE_DYE =
			ITEMS.register("blurple_dye", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> CANARY_DYE =
			ITEMS.register("canary_dye", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> CORAL_DYE =
			ITEMS.register("coral_dye", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> ROSE_DYE =
			ITEMS.register("rose_dye", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> SACRAMENTO_DYE =
			ITEMS.register("sacramento_dye", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> SANGRIA_DYE =
			ITEMS.register("sangria_dye", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> SKY_DYE =
			ITEMS.register("sky_dye", () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> WASABI_DYE =
			ITEMS.register("wasabi_dye", () -> new Item(new Item.Properties()));

	public static void register(IEventBus eventBus){
		ITEMS.register(eventBus);
	}
}
