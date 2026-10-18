package dev.pumpkin.dumper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * What MysticalAgriculture decides in code about each crop, which its behaviour plugin needs:
 * tier, the farmland that suits it, the chance of a second drop, the crux block it needs
 * beneath, the biomes it grows in, and its seed's infusion recipe. Read through the mod's
 * public API by reflection, so the dumper neither depends on the mod nor fails without it.
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

        // DynamicRecipeManager.makeSeedRecipe, built in code when a server loads recipes: the
        // type's crafting seed on the altar, and the material and the tier's essence
        // alternating around it. Left out when the material resolves to nothing, as the mod
        // then has no recipe either.
        var recipeConfig = call(crop, "getRecipeConfig");
        var craftingSeed = itemId(call(call(crop, "getType"), "getCraftingSeedItem"));
        var material = material(call(crop, "getLazyIngredient"));
        if ((Boolean) call(recipeConfig, "isSeedInfusionRecipeEnabled") && craftingSeed != null
                && !material.isEmpty()) {
            var recipe = new JsonObject();
            recipe.addProperty("input", craftingSeed);
            var items = new JsonArray();
            material.forEach(items::add);
            recipe.add("material", items);
            recipe.addProperty("essence", itemId(call(tier, "getEssenceItem")));
            out.add("seed_infusion", recipe);
        }
        return out;
    }

    /** The items a crop's crafting material stands for, with tags resolved. */
    private static Set<String> material(Object lazy) throws ReflectiveOperationException {
        var id = (String) call(lazy, "getId");
        // A filled soul jar, say: matching it needs the item's components, which a list of
        // item ids cannot carry, so the recipe is left out rather than matching any jar.
        var components = (net.minecraft.core.component.DataComponentMap) call(lazy, "getComponents");
        if (id == null || (components != null && !components.isEmpty())) {
            return Collections.emptySet();
        }
        if ((Boolean) call(lazy, "isTag")) {
            return tagItems(id, new LinkedHashSet<>());
        }
        return registeredItem(id) ? Set.of(id) : Collections.emptySet();
    }

    /**
     * An item tag's members, read from every {@code data/<ns>/tags/item/<path>.json} on the
     * classpath. A data run binds no tags, and the common {@code c:} tags live in NeoForge's
     * own jar rather than the mod's, so the converter cannot see them either.
     */
    private static Set<String> tagItems(String tag, Set<String> visiting) {
        var items = new LinkedHashSet<String>();
        if (!visiting.add(tag)) {
            return items;
        }
        var id = Identifier.parse(tag);
        var path = "data/" + id.getNamespace() + "/tags/item/" + id.getPath() + ".json";
        try {
            var loader = MysticalAgricultureData.class.getClassLoader();
            for (var url : Collections.list(loader.getResources(path))) {
                try (var reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
                    for (JsonElement value : JsonParser.parseReader(reader).getAsJsonObject().getAsJsonArray("values")) {
                        var entry = value.isJsonObject() ? value.getAsJsonObject().get("id").getAsString() : value.getAsString();
                        if (entry.startsWith("#")) {
                            items.addAll(tagItems(entry.substring(1), visiting));
                        } else if (registeredItem(entry)) {
                            items.add(Identifier.parse(entry).toString());
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("cannot read item tag " + tag, e);
        }
        return items;
    }

    private static boolean registeredItem(String id) {
        return BuiltInRegistries.ITEM.containsKey(Identifier.parse(id));
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
