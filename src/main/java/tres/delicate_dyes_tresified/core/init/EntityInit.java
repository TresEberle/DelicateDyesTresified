package tres.delicate_dyes_tresified.core.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tres.delicate_dyes_tresified.common.entity.ModSheep;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import java.util.HashMap;
import java.util.Map;

public class EntityInit {
	public static final DeferredRegister<EntityType<?>> ENTITIES;
	public static final DeferredHolder<EntityType<?>, EntityType<ModSheep>> SHEEP;
	public static final Map<String, ResourceKey<LootTable>> SHEEP_LOOT;

	public static void register() {
		for(DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
			SHEEP_LOOT.put(color.getTranslationKey(), ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath("dyenamics", "entities/sheep/" + color.getTranslationKey())));
		}

	}

	static {
		ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, "delicate_dyes_tresified");
		SHEEP = ENTITIES.register("sheep", () -> EntityType.Builder.of(ModSheep::new, MobCategory.CREATURE).sized(0.9F, 1.3F).eyeHeight(1.235F).passengerAttachments(new float[]{1.2375F}).clientTrackingRange(10).build("sheep"));
		SHEEP_LOOT = new HashMap();
	}
}
