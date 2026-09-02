package dev.pumpkin.bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Tags over a mod's own registries, answered from the mod's datapack.
 *
 * <p>Vanilla tags name items and blocks, and those had a home already. A mod may also tag
 * things in a registry it created: Mekanism keeps its upgrade support lists that way, and
 * every machine asks {@code #mekanism:upgrade_support/default_machine} at load to learn
 * which upgrades it accepts. Answering "no such tag" made every machine log that it could
 * not find its supported upgrades and then support none of them.
 *
 * <p>The files live where the datapack convention puts them --
 * {@code data/<namespace>/tags/<registry namespace>/<registry path>/<tag>.json} -- so the
 * same walk that resolves item tags resolves these, nested {@code #references} included.
 * What comes back is matched against what actually registered under that registry, so a
 * tag naming something absent contributes nothing rather than a hole in the list.
 */
public final class PumpkinRegistryTags {
    private PumpkinRegistryTags() {
    }

    /**
     * The holders wearing {@code tag} in {@code registry}, or empty when nothing does.
     *
     * @param registry the registry's namespaced id, {@code mekanism:upgrade}
     */
    public static <T> Optional<HolderSet.Named<T>> holderSetFor(String registry, TagKey<T> tag) {
        if (tag == null || tag.location() == null) {
            return Optional.empty();
        }
        String tagId = tag.location().toString();
        java.util.Set<String> members = PumpkinTags.kindMembers(tagDirectory(registry), tagId);
        if (members.isEmpty()) {
            return Optional.empty();
        }
        List<Holder<T>> holders = new ArrayList<>();
        for (String id : members) {
            Object value = DeferredHolder.pumpkinResolve(registry, id);
            if (value == null) {
                // The tag names something this registry never got. Skipping it keeps the
                // rest of the tag usable; a mod asking for upgrades it does not have
                // registered wants the ones it does.
                continue;
            }
            @SuppressWarnings({"unchecked", "rawtypes"})
            Holder<T> reference = (Holder<T>) Holder.Reference.pumpkinOf(
                    (net.minecraft.resources.ResourceKey) net.minecraft.resources.ResourceKey
                            .create((net.minecraft.resources.ResourceKey) tag.registry(),
                                    net.minecraft.resources.Identifier.parse(id)),
                    value);
            holders.add(reference);
        }
        if (holders.isEmpty()) {
            // The tag is defined but nothing it names registered here. Empty rather than
            // a set of nothing, so the caller takes its own "no such tag" path instead of
            // concluding the tag exists and is genuinely empty.
            return Optional.empty();
        }
        return Optional.of(HolderSet.Named.pumpkinOf(tag, List.copyOf(holders)));
    }

    /** Whether the entry {@code id} of {@code registry} wears {@code tag}. */
    public static boolean wears(String registry, String tagId, String id) {
        return PumpkinTags.kindMembers(tagDirectory(registry), tagId).contains(id);
    }

    /**
     * Where a registry's tags live under {@code data/<namespace>/tags/}.
     *
     * <p>Vanilla registries drop their namespace ({@code minecraft:item} is {@code item});
     * everything else keeps it as a directory, which is what `NeoForge` writes.
     */
    private static String tagDirectory(String registry) {
        if (registry.startsWith("minecraft:")) {
            return registry.substring("minecraft:".length());
        }
        return registry.replace(':', '/');
    }
}
