package tres.delicate_dyes_tresified.core.util;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

//@EventBusSubscriber(
//		modid = "delicate_dyes_tresified"
//)
//public class EventHandler {
//	@SubscribeEvent
//	public static void onPlayerInteract(PlayerInteractEvent.EntityInteract event) {
//		Item var2 = event.getItemStack().getItem();
//		if (var2 instanceof DyeColorUtil dyeItem) {
//			Entity colorId = event.getTarget();
//			if (colorId instanceof Wolf wolf) {
//				if (wolf.isTame() && wolf.isOwnedBy(event.getEntity())) {
//					Integer colorId = (Integer)wolf.getEntityData().get(Wolf.DATA_COLLAR_COLOR);
//					if (!event.getLevel().isClientSide && colorId != dyeItem.getDyeColor().getId()) {
//						wolf.getEntityData().set(Wolf.DATA_COLLAR_COLOR, dyeItem.getDyeColor().getId());
//						if (!event.getEntity().hasInfiniteMaterials()) {
//							event.getItemStack().shrink(1);
//						}
//					}
//
//					event.setCanceled(true);
//					event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide));
//				}
//			}
//
//			colorId = event.getTarget();
//			if (colorId instanceof Cat cat) {
//				if (cat.isTame() && cat.isOwnedBy(event.getEntity())) {
//					Integer colorId = (Integer)cat.getEntityData().get(Cat.DATA_COLLAR_COLOR);
//					if (!event.getLevel().isClientSide && colorId != dyeItem.getDyeColor().getId()) {
//						cat.getEntityData().set(Cat.DATA_COLLAR_COLOR, dyeItem.getDyeColor().getId());
//						if (!event.getEntity().hasInfiniteMaterials()) {
//							event.getItemStack().shrink(1);
//						}
//					}
//
//					event.setCanceled(true);
//					event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide));
//				}
//			}
//		}
//
//	}
//}
