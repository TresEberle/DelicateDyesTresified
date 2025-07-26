package tres.delicate_dyes_tresified.common.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import tres.delicate_dyes_tresified.client.render.item.ModShulkerBoxItemStackRenderer;

import java.util.function.Consumer;

public class ModShulkerBlockItem extends BlockItem {

	public ModShulkerBlockItem(Block pBlock, Item.Properties pProperties) {
		super(pBlock, pProperties);
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			final BlockEntityWithoutLevelRenderer myRenderer = new ModShulkerBoxItemStackRenderer();

			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return myRenderer;
			}
		});
	}
}
