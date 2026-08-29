package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

/**
 * The four members that only the mixin scanner can see, asserted one at a time.
 *
 * <p>Cucumber patches vanilla with three {@code @Inject}s and a {@code @Shadow}. A mixin
 * names its target as an <em>annotation string</em> -- {@code
 * "applyDamage(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V"}
 * -- not as a {@code Methodref}, so a constant-pool scan cannot see it, and neither can the
 * linkage check: nothing in the mod's bytecode calls these, so nothing fails to link when
 * they are pruned away. They would simply be absent, and the mixin would fail to apply at
 * runtime with an error naming a member the manifest never mentioned.
 *
 * <p>Four assertions rather than one loop over a list, deliberately. If {@link
 * MixinScanner} regresses on, say, the {@code @Shadow} case and not the {@code @Inject}
 * one, the failing test name has to say which -- a single "some mixin members are missing"
 * failure would send the reader back to the manifest to work out which of the four it was.
 */
class MixinMembersPresentTest {
    private static SortedSet<String> members;

    private static boolean manifestHas(String key) throws IOException {
        if (members == null) {
            // Relative to the generator project directory, which is Gradle's working
            // directory for its tests.
            Path manifest = Path.of("used-set.txt");
            assertTrue(Files.isRegularFile(manifest),
                    "the manifest is not at " + manifest.toAbsolutePath()
                            + "; this test assumes Gradle runs it from the generator project directory");
            try (Reader reader = Files.newBufferedReader(manifest, StandardCharsets.UTF_8)) {
                SortedSet<String> keys = new TreeSet<>();
                for (UsedSet.MemberRef ref : UsedSet.readFrom(reader).members()) {
                    keys.add(ref.key());
                }
                members = keys;
            }
        }
        return members.contains(key);
    }

    /** {@code ItemStackMixin}'s {@code @Inject} into the damage path. */
    @Test
    void itemStackApplyDamageSurvivedPruning() throws IOException {
        assertTrue(
                manifestHas("net/minecraft/world/item/ItemStack.applyDamage:"
                        + "(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V"),
                "ItemStack.applyDamage is not in the manifest; Cucumber's @Inject targets it and only"
                        + " MixinScanner can see that");
    }

    /** {@code ItemStackMixin}'s {@code @Inject} into the constructor. */
    @Test
    void itemStackConstructorSurvivedPruning() throws IOException {
        assertTrue(
                manifestHas("net/minecraft/world/item/ItemStack.<init>:"
                        + "(Lnet/minecraft/core/Holder;ILnet/minecraft/core/component/PatchedDataComponentMap;)V"),
                "ItemStack.<init>(Holder, int, PatchedDataComponentMap) is not in the manifest;"
                        + " Cucumber's @Inject targets it and only MixinScanner can see that");
    }

    /** {@code RecipeManagerMixin}'s {@code @Inject} into recipe reload. */
    @Test
    void recipeManagerPrepareSurvivedPruning() throws IOException {
        assertTrue(
                manifestHas("net/minecraft/world/item/crafting/RecipeManager.prepare:"
                        + "(Lnet/minecraft/server/packs/resources/ResourceManager;"
                        + "Lnet/minecraft/util/profiling/ProfilerFiller;)"
                        + "Lnet/minecraft/world/item/crafting/RecipeMap;"),
                "RecipeManager.prepare is not in the manifest; Cucumber's @Inject targets it and only"
                        + " MixinScanner can see that");
    }

    /** {@code ReloadableServerResourcesMixin}'s target. */
    @Test
    void reloadableServerResourcesUpdateComponentsSurvivedPruning() throws IOException {
        assertTrue(
                manifestHas("net/minecraft/server/ReloadableServerResources"
                        + ".updateComponentsAndStaticRegistryTags:()V"),
                "ReloadableServerResources.updateComponentsAndStaticRegistryTags is not in the manifest;"
                        + " Cucumber's mixin targets it and only MixinScanner can see that");
    }
}
