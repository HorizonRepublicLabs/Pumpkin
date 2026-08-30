package dev.pumpkin.bridge;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Item tag membership, answered from the extracted mod datapacks and the vanilla tables.
 *
 * <p>A tag ingredient ({@code #c:ingots/gold}) needs to know which items wear the tag.
 * Mod and {@code c:} tags come from the datapack tag JSONs the mods ship, nested tag
 * references resolved recursively; tags no datapack defines fall back to the vanilla
 * tables on the Rust side, asked once through the host and remembered.
 */
public final class PumpkinTags {
    private PumpkinTags() {
    }

    private static final Map<String, Set<String>> MEMBERS = new ConcurrentHashMap<>();

    /** Whether the item wears the tag; {@code tag} has no leading {@code #}. */
    public static boolean contains(String tag, String itemId) {
        return members(tag, new HashSet<>()).contains(itemId);
    }

    private static Set<String> members(String tag, Set<String> visiting) {
        Set<String> cached = MEMBERS.get(tag);
        if (cached != null) {
            return cached;
        }
        if (!visiting.add(tag)) {
            // A tag cycle; an empty answer breaks it and the cache keeps it broken.
            return Set.of();
        }
        Set<String> values = new HashSet<>();
        boolean defined = false;
        Path root = PumpkinRecipes.pumpkinDatapacksDir();
        String namespace = tag.contains(":") ? tag.substring(0, tag.indexOf(':')) : "minecraft";
        String path = tag.contains(":") ? tag.substring(tag.indexOf(':') + 1) : tag;
        if (root != null && Files.isDirectory(root)) {
            try (var packs = Files.list(root)) {
                for (Path pack : packs
                        .filter(p -> p.getFileName().toString().startsWith("mod_")).toList()) {
                    Path file = pack.resolve("data").resolve(namespace).resolve("tags")
                            .resolve("item").resolve(path + ".json");
                    if (!Files.isRegularFile(file)) {
                        continue;
                    }
                    defined = true;
                    JsonObject json =
                            JsonParser.parseString(Files.readString(file)).getAsJsonObject();
                    for (JsonElement entry : json.getAsJsonArray("values")) {
                        String value = entry.isJsonObject()
                                ? entry.getAsJsonObject().get("id").getAsString()
                                : entry.getAsString();
                        if (value.startsWith("#")) {
                            values.addAll(members(value.substring(1), visiting));
                        } else {
                            values.add(value);
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("[pumpkin] reading tag " + tag + " failed: " + e);
            }
        }
        if (!defined) {
            // Nothing in the datapacks defines it: the vanilla tables might. Reached by
            // reflection because the host jar sits above this one in the build graph but
            // beside it on the runtime classpath. Bare names come back namespaced so
            // membership compares apples to apples.
            try {
                String vanilla = (String) Class.forName("dev.pumpkin.jvmhost.PumpkinHost")
                        .getMethod("itemTagValues", String.class).invoke(null, tag);
                for (String value : vanilla.split(",")) {
                    if (!value.isEmpty()) {
                        values.add(value.contains(":") ? value : "minecraft:" + value);
                    }
                }
            } catch (ReflectiveOperationException e) {
                System.err.println("[pumpkin] vanilla tag lookup for " + tag + " failed: " + e);
            }
        }
        Set<String> frozen = Set.copyOf(values);
        MEMBERS.put(tag, frozen);
        return frozen;
    }
}
