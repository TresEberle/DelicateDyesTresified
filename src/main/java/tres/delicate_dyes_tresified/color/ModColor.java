package tres.delicate_dyes_tresified.color;

import net.minecraft.world.level.material.MapColor;

import java.util.*;

public class ModColor {
    private static final List<ModColor> VALUES = new ArrayList<>();

    public static final ModColor SACRAMENTO = new ModColor("sacramento", 0x437a6a, MapColor.COLOR_GREEN);

    private final String name;
    private final int hex;
    private final MapColor mapColor;

    private ModColor(String name, int hex, MapColor mapColor) {
        this.name = name;
        this.hex = hex;
        this.mapColor = mapColor;
        VALUES.add(this);
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return hex;
    }

    public MapColor getMapColor() {
        return mapColor;
    }

    public static List<ModColor> values() {
        return VALUES;
    }

    public static Optional<ModColor> byName(String name) {
        return VALUES.stream().filter(d -> d.name.equals(name)).findFirst();
    }
}
