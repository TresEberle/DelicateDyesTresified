package tres.delicate_dyes_tresified.common.item;

import com.google.common.collect.Maps;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import tres.delicate_dyes_tresified.common.entity.ModSheep;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import java.util.Map;

public class ModDyeItem extends Item {
	private static final Map<DyeColorUtil, ModDyeItem> COLOR_DYE_ITEM_MAP = Maps.newEnumMap(DyeColorUtil.class);
	private final DyeColorUtil dyeColor;

	public ModDyeItem(DyeColorUtil dyeColorIn, Item.Properties builder) {
		super(builder);
		this.dyeColor = dyeColorIn;
		COLOR_DYE_ITEM_MAP.put(dyeColorIn, this);
	}

	public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
		if (target instanceof ModSheep dyenamicSheep) {
			if (dyenamicSheep.isAlive() && !dyenamicSheep.isSheared() && dyenamicSheep.getDyenamicColor() != this.dyeColor) {
				if (!playerIn.level().isClientSide) {
					dyenamicSheep.setColor(this.dyeColor);
					stack.shrink(1);
				}

				return InteractionResult.sidedSuccess(playerIn.level().isClientSide);
			}
		} else if (target instanceof Sheep sheep) {
			if (sheep.isAlive() && !sheep.isSheared() && sheep.getColor().getId() != this.dyeColor.getId()) {
				if (!playerIn.level().isClientSide) {
					ModSheep.convertToDyenamics(sheep, this.dyeColor);
					stack.shrink(1);
				}

				return InteractionResult.sidedSuccess(playerIn.level().isClientSide);
			}
		}

		return InteractionResult.PASS;
	}

	public DyeColorUtil getDyeColor() {
		return this.dyeColor;
	}

	public static ModDyeItem getItem(DyeColorUtil color) {
		return (ModDyeItem)COLOR_DYE_ITEM_MAP.get(color);
	}
}
