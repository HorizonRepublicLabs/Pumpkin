package net.minecraft.world.level.material;

import dev.pumpkin.shim.Unimplemented;

public class MapColor {

    private static final MapColor[] MATERIAL_COLORS = null;

    public static final MapColor NONE = null;

    public static final MapColor GRASS = null;

    public static final MapColor METAL = null;

    public static final MapColor SNOW = null;

    public static final MapColor CLAY = null;

    public static final MapColor STONE = null;

    public static final MapColor WOOD = null;

    public static final MapColor COLOR_ORANGE = null;

    public static final MapColor COLOR_MAGENTA = null;

    public static final MapColor COLOR_LIGHT_BLUE = null;

    public static final MapColor COLOR_YELLOW = null;

    public static final MapColor COLOR_LIGHT_GREEN = null;

    public static final MapColor COLOR_GRAY = null;

    public static final MapColor COLOR_LIGHT_GRAY = null;

    public static final MapColor COLOR_CYAN = null;

    public static final MapColor COLOR_PURPLE = null;

    public static final MapColor COLOR_BLUE = null;

    public static final MapColor COLOR_BROWN = null;

    public static final MapColor COLOR_RED = null;

    public static final MapColor COLOR_BLACK = null;

    public static final MapColor DIAMOND = null;

    public static final MapColor NETHER = null;

    public static final MapColor TERRACOTTA_WHITE = null;

    public static final MapColor TERRACOTTA_PINK = null;

    public static final MapColor TERRACOTTA_CYAN = null;

    public static final MapColor TERRACOTTA_BROWN = null;

    public static final MapColor DEEPSLATE = null;

    public final int col = 0;

    private MapColor(int id, int col) {
    }

    public static MapColor byId(int id) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/MapColor.byId:(I)Lnet/minecraft/world/level/material/MapColor;");
    }

    public enum Brightness {

        LOW, NORMAL, HIGH, LOWEST;

        public static MapColor.Brightness byId(int id) {
            throw Unimplemented.forMember("net/minecraft/world/level/material/MapColor$Brightness.byId:(I)Lnet/minecraft/world/level/material/MapColor$Brightness;");
        }
    }

    public MapColor() {
    }
}
