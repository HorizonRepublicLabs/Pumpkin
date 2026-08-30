package dev.pumpkin.bridge;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Mod recipes, decoded by the mod's own serializers.
 *
 * <p>The server's own crafting reads vanilla-typed recipes; a machine's custom type --
 * {@code mysticalagriculture:infusion} -- is meaningful only to the mod that registered
 * it. So the recipes stay in the mod's world: the JSON comes from the extracted mod
 * datapacks, the codec that decodes it is the one the mod registered with its serializer,
 * and matching runs the recipe object's own {@code matches}. This class only ferries
 * bytes between them and caches the result.
 */
public final class PumpkinRecipes {
    private PumpkinRecipes() {
    }

    private static volatile Path datapacksDir;

    /** Where the extracted mod datapacks live; told once by the host, which knows. */
    public static void setDatapacksDir(String dir) {
        datapacksDir = Path.of(dir);
    }

    private static final Manager MANAGER = new Manager();

    /** Recipes per type name, decoded once on first ask. */
    private static final Map<String, List<RecipeHolder<?>>> BY_TYPE = new ConcurrentHashMap<>();

    static RecipeManager manager() {
        return MANAGER;
    }

    private static final class Manager extends RecipeManager {
        private Manager() {
            super(null);
        }

        @Override
        public <I extends RecipeInput, T extends Recipe<I>> Optional<RecipeHolder<T>> getRecipeFor(
                RecipeType<T> type, I input, Level level) {
            String typeName = DeferredHolder.pumpkinResolveName("minecraft:recipe_type", type);
            if (typeName == null) {
                return Optional.empty();
            }
            for (RecipeHolder<?> holder : BY_TYPE.computeIfAbsent(typeName, PumpkinRecipes::load)) {
                @SuppressWarnings("unchecked")
                RecipeHolder<T> cast = (RecipeHolder<T>) holder;
                if (cast.value().matches(input, level)) {
                    return Optional.of(cast);
                }
            }
            return Optional.empty();
        }
    }

    /** Every recipe JSON of one type, decoded through the type's registered serializer. */
    private static List<RecipeHolder<?>> load(String typeName) {
        List<RecipeHolder<?>> recipes = new ArrayList<>();
        Path root = datapacksDir;
        if (root == null || !Files.isDirectory(root)) {
            return recipes;
        }
        Object serializerObject =
                DeferredHolder.pumpkinResolve("minecraft:recipe_serializer", typeName);
        if (!(serializerObject instanceof RecipeSerializer<?> serializer)) {
            System.err.println("[pumpkin] " + typeName
                    + ": no registered serializer; its recipes stay undecoded.");
            return recipes;
        }

        int failed = 0;
        String failure = null;
        try (var packs = Files.list(root)) {
            for (Path pack : packs.filter(p -> p.getFileName().toString().startsWith("mod_"))
                    .toList()) {
                Path data = pack.resolve("data");
                if (!Files.isDirectory(data)) {
                    continue;
                }
                try (var namespaces = Files.list(data)) {
                    for (Path namespace : namespaces.toList()) {
                        Path recipeDir = namespace.resolve("recipe");
                        if (!Files.isDirectory(recipeDir)) {
                            continue;
                        }
                        try (var walk = Files.walk(recipeDir)) {
                            for (Path file : walk
                                    .filter(f -> f.toString().endsWith(".json")).toList()) {
                                JsonObject json = JsonParser
                                        .parseString(Files.readString(file)).getAsJsonObject();
                                String declared = json.has("type")
                                        ? json.get("type").getAsString() : "";
                                if (!declared.equals(typeName)) {
                                    continue;
                                }
                                String id = namespace.getFileName() + ":"
                                        + recipeDir.relativize(file).toString()
                                                .replace(".json", "");
                                try {
                                    DataResult<? extends Recipe<?>> decoded = serializer.codec()
                                            .codec().parse(JsonOps.INSTANCE, json);
                                    if (decoded.result().isPresent()) {
                                        recipes.add(new RecipeHolder<>(
                                                net.minecraft.resources.ResourceKey.create(
                                                        net.minecraft.resources.ResourceKey
                                                                .createRegistryKey(Identifier
                                                                        .parse("minecraft:recipe")),
                                                        Identifier.parse(id)),
                                                decoded.result().get()));
                                    } else {
                                        failed++;
                                        if (failure == null) {
                                            failure = id + ": " + decoded.error()
                                                    .map(Object::toString).orElse("unknown");
                                        }
                                    }
                                } catch (RuntimeException decodeStop) {
                                    // A codec whose dependency is still a throwing stub
                                    // stops with a member key; one bad recipe must not
                                    // take the rest of the type down with it.
                                    failed++;
                                    if (failure == null) {
                                        failure = id + ": " + decodeStop.getMessage();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("[pumpkin] " + typeName + ": reading recipes failed: " + e);
        }
        System.err.println("[pumpkin] " + typeName + ": " + recipes.size()
                + " recipe(s) decoded" + (failed > 0
                        ? ", " + failed + " failed (first: " + failure + ")" : "") + ".");
        return recipes;
    }
}
