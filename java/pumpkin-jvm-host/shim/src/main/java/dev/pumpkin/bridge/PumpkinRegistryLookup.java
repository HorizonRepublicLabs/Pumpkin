package dev.pumpkin.bridge;

import java.util.Map;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * What a registry answers when a mod looks one up.
 *
 * <p>A mod reaches its own registries two ways -- {@code HolderLookup.Provider} hands back
 * a {@code RegistryLookup}, {@code RegistryAccess} hands back a {@code Registry} -- and in
 * vanilla the second is a subtype of the first, so both are the same object. Here both are
 * proxies, and this is the one place that says what they answer, so the two can never
 * drift into disagreeing about what a registry contains.
 *
 * <p>Three questions are answered from what actually registered: which registry this is,
 * everything in it, and one lookup by value key or by tag. Everything else still refuses
 * by name.
 */
public final class PumpkinRegistryLookup {
    private PumpkinRegistryLookup() {
    }

    /** The dynamic members a registry proxy for {@code key} answers. */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Map<String, Object> answersFor(ResourceKey<?> key) {
        String registry = key.identifier().toString();
        return Map.of(
                "key", key,
                "listElements", (dev.pumpkin.shim.Stubs.Dynamic) args ->
                        DeferredHolder.pumpkinAllFor(registry).stream()
                                .map(holder -> Holder.Reference.pumpkinOf(
                                        (ResourceKey) holder.getKey(), holder.get())),
                "get", (dev.pumpkin.shim.Stubs.Dynamic) args -> get(registry, args));
    }

    private static Object get(String registry, Object[] args) {
        if (args == null || args.length != 1) {
            return Optional.empty();
        }
        if (args[0] instanceof net.minecraft.tags.TagKey<?> tagKey) {
            return PumpkinRegistryTags.holderSetFor(registry, tagKey);
        }
        if (args[0] instanceof ResourceKey<?> valueKey) {
            // By id rather than by scanning holders and asking each for its key: a holder
            // created without a registry cannot answer getKey, and one such entry made
            // the whole lookup throw instead of finding the entry beside it. The id is
            // what the registry is keyed on anyway.
            Object value = DeferredHolder.pumpkinResolve(registry,
                    valueKey.identifier().toString());
            if (value != null) {
                @SuppressWarnings({"unchecked", "rawtypes"})
                Holder.Reference<?> reference =
                        Holder.Reference.pumpkinOf((ResourceKey) valueKey, value);
                return Optional.of(reference);
            }
        }
        return Optional.empty();
    }
}
