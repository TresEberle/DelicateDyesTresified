package tres.delicate_dyes_tresified.common.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import tres.delicate_dyes_tresified.client.render.item.ModBannerItemStackRenderer;

import java.util.function.Consumer;

public class ModBannerItem extends BannerItem {
	public ModBannerItem(Block banner, Block wallBanner, Item.Properties properties) {
		super(banner, wallBanner, properties);
	}

	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			final BlockEntityWithoutLevelRenderer myRenderer = new ModBannerItemStackRenderer();

			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return this.myRenderer;
			}
		});
	}
}
