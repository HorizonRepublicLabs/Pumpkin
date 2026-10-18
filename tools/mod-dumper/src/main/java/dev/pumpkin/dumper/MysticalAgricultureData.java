package dev.pumpkin.dumper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * What MysticalAgriculture decides in code about each crop, which its behaviour plugin needs:
 * tier, the farmland that suits it, the chance of a second drop, the crux block it needs
 * beneath, the biomes it grows in. Read through the mod's public API by reflection, so the
 * dumper neither depends on the mod nor fails without it.
 */
final class MysticalAgricultureData {
    private static final String API = "com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI";
    private static final String ESSENCE_FARMLAND = "com.blakebr0.mysticalagriculture.api.farmland.IEssenceFarmland";

    private MysticalAgricultureData() {
    }

    /** {@code null} when MysticalAgriculture is not loaded. */
    static JsonObject dump() {
        Class<?> api;
        try {
            api = Class.forName(API);
        } catch (ClassNotFoundException e) {
            return null;
        }
        try {
            var registry = api.getMethod("getCropRegistry").invoke(null);
            var crops = (List<?>) registry.getClass().getMethod("getCrops").invoke(registry);

            var out = new JsonObject();
            var cropsJson = new JsonArray();
            for (Object crop : crops) {
                cropsJson.add(crop(crop));
            }
            out.add("crops", cropsJson);

            var farmland = new JsonArray();
            var marker = Class.forName(ESSENCE_FARMLAND);
            for (var block : BuiltInRegistries.BLOCK) {
                if (marker.isInstance(block)) {
                    farmland.add(BuiltInRegistries.BLOCK.getKey(block).toString());
                }
            }
            out.add("essence_farmland", farmland);
            return out;
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("cannot read MysticalAgriculture's crop registry", e);
        }
    }

    private static JsonObject crop(Object crop) throws ReflectiveOperationException {
        var tier = call(crop, "getTier");
        var out = new JsonObject();
        out.addProperty("name", (String) call(crop, "getName"));
        out.addProperty("enabled", (Boolean) call(crop, "isEnabled"));
        out.addProperty("tier", (Integer) call(tier, "getValue"));
        out.addProperty("block", blockId(call(crop, "getCropBlock")));
        out.addProperty("essence", itemId(call(crop, "getEssenceItem")));
        out.addProperty("seeds", itemId(call(crop, "getSeedsItem")));
        out.addProperty("crux", blockId(call(crop, "getCruxBlock")));
        out.addProperty("effective_farmland", blockId(call(tier, "getFarmlandBlock")));
        out.addProperty("respects_effective_farmland", (Boolean) call(crop, "respectsEffectiveFarmland"));
        out.addProperty("has_secondary_drop", (Boolean) call(tier, "hasSecondarySeedDrop"));
        out.addProperty("fertilizable", (Boolean) call(tier, "isFertilizable"));
        // Crop.getSecondaryChance's base: the crop's own when set, else its tier's.
        double base = (Double) call(crop, "getBaseSecondaryChance");
        out.addProperty("base_secondary_chance", base > -1 ? base : (Double) call(tier, "getBaseSecondaryChance"));
        var biomes = new JsonArray();
        for (Object biome : (Collection<?>) call(crop, "getRequiredBiomes")) {
            biomes.add(biome.toString());
        }
        out.add("required_biomes", biomes);
        return out;
    }

    private static Object call(Object target, String method) throws ReflectiveOperationException {
        Method m = target.getClass().getMethod(method);
        return m.invoke(target);
    }

    private static String blockId(Object block) {
        if (!(block instanceof Block b)) {
            return null;
        }
        Identifier id = BuiltInRegistries.BLOCK.getKey(b);
        return id.toString();
    }

    private static String itemId(Object item) {
        return item instanceof Item i ? BuiltInRegistries.ITEM.getKey(i).toString() : null;
    }
}
