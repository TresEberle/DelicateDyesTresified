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

	public static final DeferredItem<Item> SACRAMENTO_DYE =
			ITEMS.register("sacramento_dye", () -> new ColoredDyeItem(new Item.Properties()));

	public static final DeferredItem<Item> CANARY_DYE =
			ITEMS.register("canary_dye", () -> new Item(new Item.Properties()));

	public static void register(IEventBus eventBus){
		ITEMS.register(eventBus);
	}
}
