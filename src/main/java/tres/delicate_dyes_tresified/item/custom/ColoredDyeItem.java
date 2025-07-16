package tres.delicate_dyes_tresified.item.custom;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.FastColor;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Contract;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public enum ColoredDyeItem implements StringRepresentable {
    SACRAMENTO(0, "sacramento", 16383998, MapColor.COLOR_GREEN, 15790320, 16777215);


    private static final IntFunction<ColoredDyeItem> BY_ID = ByIdMap.continuous(ColoredDyeItem::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    private static final Int2ObjectOpenHashMap<ColoredDyeItem> BY_FIREWORK_COLOR = new Int2ObjectOpenHashMap((Map) Arrays.stream(values()).collect(Collectors.toMap((p_41064_) -> p_41064_.fireworkColor, (p_41056_) -> p_41056_)));
    public static final StringRepresentable.EnumCodec<ColoredDyeItem> CODEC = StringRepresentable.fromEnum(ColoredDyeItem::values);
    public static final StreamCodec<ByteBuf, ColoredDyeItem> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, ColoredDyeItem::getId);
    private final int id;
    private final String name;
    private final MapColor mapColor;
    private final int textureDiffuseColor;
    private final int fireworkColor;
    private final TagKey<Item> dyesTag;
    private final TagKey<Item> dyedTag;
    private final int textColor;

    private ColoredDyeItem(int id, String name, int textureDefuseColor, MapColor mapColor, int fireworkColor, int textColor) {
        this.id = id;
        this.name = name;
        this.mapColor = mapColor;
        this.textColor = textColor;
        this.dyesTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "dyes/" + name));
        this.dyedTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "dyed/" + name));
        this.textureDiffuseColor = FastColor.ARGB32.opaque(textureDefuseColor);
        this.fireworkColor = fireworkColor;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getTextureDiffuseColor() {
        return this.textureDiffuseColor;
    }

    public MapColor getMapColor() {
        return this.mapColor;
    }

    public int getFireworkColor() {
        return this.fireworkColor;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public static ColoredDyeItem byId(int colorId) {
        return (ColoredDyeItem)BY_ID.apply(colorId);
    }

    @Nullable
    @Contract("_,!null->!null;_,null->_")
    public static ColoredDyeItem byName(String translationKey, @Nullable ColoredDyeItem fallback) {
        ColoredDyeItem ColoredDyeItem = (ColoredDyeItem)CODEC.byName(translationKey);
        return ColoredDyeItem != null ? ColoredDyeItem : fallback;
    }

    @Nullable
    public static ColoredDyeItem byFireworkColor(int fireworkColor) {
        return (ColoredDyeItem)BY_FIREWORK_COLOR.get(fireworkColor);
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }

    public TagKey<Item> getTag() {
        return this.dyesTag;
    }

    public TagKey<Item> getDyedTag() {
        return this.dyedTag;
    }

    @Nullable
    public static ColoredDyeItem getColor(ItemStack stack) {
        if (stack.getItem() instanceof DyeItem) {
            //return ((DyeItem)stack.getItem()).getColoredDyeItem();
            return null;
        } else {
//            for(int x = 0; x < BLACK.getId(); ++x) {
//                ColoredDyeItem color = byId(x);
//                if (stack.is(color.getTag())) {
//                    return color;
//                }
//            }

            return null;
        }
    }
}


//    @Override
//    public @NotNull InteractionResult useOn(UseOnContext context){
//        Level level = context.getLevel();
//        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
//
//        if(DYE_MAP.containsKey(clickedBlock)) {
//            if(!level.isClientSide()) {
//                level.setBlockAndUpdate(context.getClickedPos(), DYE_MAP.get(clickedBlock).defaultBlockState());
//
//                context.getItemInHand().shrink(1);
//
//                level.playSound(null, context.getClickedPos(), SoundEvents.DYE_USE, SoundSource.BLOCKS);
//            }
//        }
//
//        return InteractionResult.SUCCESS;
//    }






