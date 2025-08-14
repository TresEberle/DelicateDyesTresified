package tres.delicate_dyes_tresified.core.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.FastColor;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.MapColor;
import tres.delicate_dyes_tresified.common.item.ModDyeItem;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public enum DyeColorUtil implements StringRepresentable {
	WHITE(0, "white", 16383998, DyeColor.WHITE, MapColor.SNOW, 15790320, 16777215, 0),
	ORANGE(1, "orange", 16351261, DyeColor.ORANGE, MapColor.COLOR_ORANGE, 15435844, 16738335, 0),
	MAGENTA(2, "magenta", 13061821, DyeColor.MAGENTA, MapColor.COLOR_MAGENTA, 12801229, 16711935, 0),
	LIGHT_BLUE(3, "light_blue", 3847130, DyeColor.LIGHT_BLUE, MapColor.COLOR_LIGHT_BLUE, 6719955, 10141901, 0),
	YELLOW(4, "yellow", 16701501, DyeColor.YELLOW, MapColor.COLOR_YELLOW, 14602026, 16776960, 0),
	LIME(5, "lime", 8439583, DyeColor.LIME, MapColor.COLOR_LIGHT_GREEN, 4312372, 12582656, 0),
	PINK(6, "pink", 15961002, DyeColor.PINK, MapColor.COLOR_PINK, 14188952, 16738740, 0),
	GRAY(7, "gray", 4673362, DyeColor.GRAY, MapColor.COLOR_GRAY, 4408131, 8421504, 0),
	LIGHT_GRAY(8, "light_gray", 10329495, DyeColor.LIGHT_GRAY, MapColor.COLOR_LIGHT_GRAY, 11250603, 13882323, 0),
	CYAN(9, "cyan", 1481884, DyeColor.CYAN, MapColor.COLOR_CYAN, 2651799, 65535, 0),
	PURPLE(10, "purple", 8991416, DyeColor.PURPLE, MapColor.COLOR_PURPLE, 8073150, 10494192, 0),
	BLUE(11, "blue", 3949738, DyeColor.BLUE, MapColor.COLOR_BLUE, 2437522, 255, 0),
	BROWN(12, "brown", 8606770, DyeColor.BROWN, MapColor.COLOR_BROWN, 5320730, 9127187, 0),
	GREEN(13, "green", 6192150, DyeColor.GREEN, MapColor.COLOR_GREEN, 3887386, 65280, 0),
	RED(14, "red", 11546150, DyeColor.RED, MapColor.COLOR_RED, 11743532, 16711680, 0),
	BLACK(15, "black", 1908001, DyeColor.BLACK, MapColor.COLOR_BLACK, 1973019, 0, 0),
	BLURPLE(16, "blurple", 6701227, DyeColor.PURPLE, MapColor.COLOR_PURPLE, 6701227, 6701227, 0),
	CANARY(17, "canary", 16774761, DyeColor.PURPLE, MapColor.COLOR_YELLOW, 16774761, 16774761, 0),
	CORAL(18, "coral", 16737637, DyeColor.PURPLE, MapColor.COLOR_PINK, 16737637, 16737637, 0),
	ROSE(19, "rose", 13445980, DyeColor.PURPLE, MapColor.COLOR_RED, 13445980, 13445980,0),
	SACRAMENTO(20, "sacramento", 4422250, DyeColor.PURPLE, MapColor.COLOR_GREEN, 4422250, 4422250, 0),
	SANGRIA(21, "sangria", 8527198, DyeColor.PURPLE, MapColor.TERRACOTTA_PURPLE, 8527198, 8527198, 0),
	SKY(22, "sky", 8518911, DyeColor.PURPLE, MapColor.COLOR_LIGHT_BLUE, 8518911, 8518911, 0),
	WASABI(23, "wasabi", 12386150, DyeColor.PURPLE, MapColor.COLOR_GREEN, 12386150, 12386150, 0),
	MAROON(24, "maroon", 8070931, DyeColor.PURPLE, MapColor.NETHER, 8070931, 8070931,0),
	CANDY(25, "candy", 14236995, DyeColor.PURPLE, MapColor.COLOR_RED, 14236995, 14236995,0),
	PEACH(26, "peach", 14645080, DyeColor.PURPLE, MapColor.COLOR_ORANGE, 14645080, 14645080,0),
	INDIGO(27, "indigo", 3350103, DyeColor.PURPLE, MapColor.TERRACOTTA_PURPLE, 3350103, 3350103,0),
	NAVY(28, "navy", 1391972, DyeColor.PURPLE, MapColor.TERRACOTTA_BLUE, 1391972, 1391972,0),
	SLATE(29, "slate", 5004934, DyeColor.PURPLE, MapColor.COLOR_GRAY, 5004934, 5004934,0),
	OLIVE(30, "olive", 9211690, DyeColor.PURPLE, MapColor.TERRACOTTA_GREEN, 9211690, 9211690,0),
	AMBER(31, "amber", 14135040, DyeColor.PURPLE, MapColor.COLOR_ORANGE, 14135040, 14135040,0),
	BEIGE(32, "beige", 14800291, DyeColor.PURPLE, MapColor.TERRACOTTA_GRAY, 14800291, 14800291,0),
	TEAL(33, "teal", 3111783, DyeColor.PURPLE, MapColor.GRASS, 3111783, 3111783,0),
	MINT(34, "mint", 3722877, DyeColor.PURPLE, MapColor.COLOR_LIGHT_GREEN, 3722877, 3722877,0),
	AQUA(35, "aqua", 6222028, DyeColor.PURPLE, MapColor.COLOR_LIGHT_BLUE, 6222028, 6222028,0),
	VERDANT(36, "verdant", 2447124, DyeColor.PURPLE, MapColor.COLOR_GREEN, 2447124, 2447124,0),
	FOREST(37, "forest", 3318566, DyeColor.PURPLE, MapColor.GRASS, 3318566, 3318566,0),
	GINGER(38, "ginger", 13590817, DyeColor.PURPLE, MapColor.COLOR_ORANGE, 13590817, 13590817,0),
	TAN(39, "tan", 16030813, DyeColor.PURPLE, MapColor.SAND, 16030813, 16030813,0);
	private static final DyeColorUtil[] VALUES = (DyeColorUtil[]) Arrays.stream(values()).sorted(Comparator.comparingInt(DyeColorUtil::getId)).toArray((x$0) -> new DyeColorUtil[x$0]);
	private static final IntFunction<DyeColorUtil> BY_ID = ByIdMap.continuous(DyeColorUtil::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
	private static final Map<Integer, DyeColorUtil> BY_FIREWORK_COLOR = (Map)Arrays.stream(values()).collect(Collectors.toMap((color) -> color.fireworkColor, (color) -> color));
	public static final StringRepresentable.EnumCodec<DyeColorUtil> CODEC = StringRepresentable.fromEnum(DyeColorUtil::values);
	public static final StreamCodec<ByteBuf, DyeColorUtil> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, DyeColorUtil::getId);
	private final int id;
	private final String translationKey;
	private final DyeColor analogue;
	private final MapColor mapColor;
	private final int colorValue;
	private final int lightValue;
	private final int fireworkColor;
	private final TagKey<Item> tag;
	private final int textColor;

	private DyeColorUtil(int idIn, String translationKeyIn, int colorValueIn, DyeColor analogueIn, MapColor mapColorIn, int fireworkColorIn, int textColorIn, int lightValueIn) {
		this.id = idIn;
		this.translationKey = translationKeyIn;
		this.colorValue = FastColor.ARGB32.opaque(colorValueIn);
		this.analogue = analogueIn;
		this.mapColor = mapColorIn;
		this.textColor = textColorIn;
		this.lightValue = lightValueIn;
		this.tag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("delicate_dyes_tresified", "dyes/" + translationKeyIn));
		this.fireworkColor = fireworkColorIn;
	}

	public int getId() {
		return this.id;
	}

	public String getTranslationKey() {
		return this.translationKey;
	}

	public int getColorComponentValue() {
		return this.colorValue;
	}

	public DyeColor getAnalogue() {
		return this.analogue;
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

	public int getLightValue() {
		return this.lightValue;
	}

	public DyeColor getVanillaColor() {
		return DyeColor.byId(this.id % 16);
	}

	public static DyeColorUtil[] dyenamicValues() {
		DyeColorUtil[] colors = values();
		return (DyeColorUtil[])Arrays.copyOfRange(colors, 16, colors.length);
	}

	public static DyeColorUtil[] vanillaValues() {
		DyeColorUtil[] colors = values();
		return (DyeColorUtil[])Arrays.copyOfRange(colors, 0, 15);
	}

	public static DyeColorUtil byId(int colorId) {
		if (colorId < 0 || colorId >= VALUES.length) {
			colorId = 0;
		}

		return VALUES[colorId];
	}

	public static DyeColorUtil byTranslationKey(String translationKeyIn, DyeColorUtil fallback) {
		for(DyeColorUtil DyeColorUtil : values()) {
			if (DyeColorUtil.translationKey.equals(translationKeyIn)) {
				return DyeColorUtil;
			}
		}

		return fallback;
	}

	@Nullable
	public static DyeColorUtil byFireworkColor(int fireworkColorIn) {
		return (DyeColorUtil)BY_FIREWORK_COLOR.get(fireworkColorIn);
	}

	public String toString() {
		return this.getTranslationKey();
	}

	public int getColorValue() {
		return this.colorValue;
	}

	public TagKey<Item> getTag() {
		return this.tag;
	}

	@Nullable
	public static DyeColorUtil getColor(ItemStack stack) {
		return getColor(stack.getItem());
	}

	@Nullable
	public static DyeColorUtil getColor(Item item) {
		if (item instanceof ModDyeItem) {
			return ((ModDyeItem)item).getDyeColor();
		} else {
			for(DyeColorUtil color : VALUES) {
				if (item.builtInRegistryHolder().is(color.getTag())) {
					return color;
				}
			}

			return null;
		}
	}

	@Nullable
	public static DyeColorUtil getColor(DyeColor colorIn) {
		for(DyeColorUtil color : VALUES) {
			if (color.analogue.equals(colorIn)) {
				return color;
			}
		}

		return null;
	}

	public String getSerializedName() {
		return this.toString();
	}
}