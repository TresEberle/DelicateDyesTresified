package tres.delicate_dyes_tresified.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;
import tres.delicate_dyes_tresified.block.ModBlocks;
import tres.delicate_dyes_tresified.color.ModColor;

import java.util.Map;

public class ColoredDyeItem extends Item {
    private static final Map<Block, Block> DYE_MAP =
            Map.of(
                    Blocks.WHITE_WOOL, ModBlocks.SACRAMENTO_WOOL.get(),
                    Blocks.WHITE_CARPET, ModBlocks.SACRAMENTO_CARPET.get()
            );

    public ColoredDyeItem(Properties properties) {
        super(properties);
    }

//    @Override
//    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
//        if (target instanceof Sheep sheep && sheep.isAlive() && !sheep.isSheared() && sheep.getColor() != this.dyeColor) {
//            sheep.level().playSound(player, sheep, SoundEvents.DYE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
//            if (!player.level().isClientSide) {
//                sheep.setColor();
//                stack.shrink(1);
//            }
//
//            return InteractionResult.sidedSuccess(player.level().isClientSide);
//        }
//
//        return InteractionResult.PASS;
//    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context){
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(DYE_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), DYE_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().shrink(1);

                level.playSound(null, context.getClickedPos(), SoundEvents.DYE_USE, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }



}


