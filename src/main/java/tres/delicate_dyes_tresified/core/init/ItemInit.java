package tres.delicate_dyes_tresified.core.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tres.delicate_dyes_tresified.common.item.ModDyeItem;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import java.util.HashMap;
import java.util.Map;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS;
	public static final Map<String, DeferredHolder<Item, Item>> DYE_ITEMS;

	public static synchronized void register() {
		for(DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
			String colorName = color.getSerializedName();
			DYE_ITEMS.put(colorName + "_dye", ITEMS.register(colorName + "_dye", () -> new ModDyeItem(color, new Item.Properties())));
		}

	}

	static {
		ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, "delicate_dyes_tresified");
		DYE_ITEMS = new HashMap();
	}
}
