package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * The four members that only the mixin scanner can see, asserted one at a time, against
 * the <em>emitted source</em>.
 *
 * <p>Cucumber patches vanilla with three {@code @Inject}s and a {@code @Shadow}. A mixin
 * names its target as an <em>annotation string</em> -- {@code
 * "applyDamage(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V"}
 * -- not as a {@code Methodref}, so a constant-pool scan cannot see it, and neither can the
 * linkage check: nothing in the mod's bytecode calls these, so nothing fails to link when
 * they are pruned away. They would simply be absent, and the mixin would fail to apply at
 * runtime with an error naming a member the manifest never mentioned.
 *
 * <p>Which is why these assertions read {@code shim/src/main/java} and not {@code
 * used-set.txt}. They used to read the manifest, and the manifest is written by {@link
 * Main} <em>before</em> pruning runs -- so every one of these tests was green by
 * construction. A pruner regression that dropped {@code ItemStack.applyDamage} from the
 * emitted file left the manifest untouched and all four passing. Since bytecode scanning
 * cannot see these four members at all, this is their only protection in CI, and it has to
 * assert about the artifact that ships.
 *
 * <p>Four assertions rather than one loop over a list, deliberately. If {@link
 * MixinScanner} regresses on, say, the {@code @Shadow} case and not the {@code @Inject}
 * one, the failing test name has to say which -- a single "some mixin members are missing"
 * failure would send the reader back to the shim to work out which of the four it was.
 */
class MixinMembersPresentTest {
    /**
     * Relative to the generator project directory, which is Gradle's working directory for
     * its tests; the shim is its sibling.
     */
    private static final Path SHIM = Path.of("").toAbsolutePath().getParent().resolve("shim/src/main/java");

    /**
     * Not {@link com.github.javaparser.StaticJavaParser}, whose default language level
     * predates records: {@code RecipeManager.java} declares one, and the default
     * configuration rejects the file outright rather than the member.
     */
    private static final JavaParser PARSER =
            new JavaParser(new ParserConfiguration().setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_25));

    /** {@code ItemStackMixin}'s {@code @Inject} into the damage path. */
    @Test
    void itemStackApplyDamageIsInTheEmittedSource() throws IOException {
        assertEmitted("net/minecraft/world/item/ItemStack", "applyDamage",
                List.of("int", "LivingEntity", "Consumer"),
                "Cucumber's @Inject targets it and only MixinScanner can see that");
    }

    /**
     * {@code ItemStackMixin}'s {@code @Inject} into the constructor. The parameter list
     * matters: {@code ItemStack} declares six constructors and the mixin names this one.
     */
    @Test
    void itemStackConstructorIsInTheEmittedSource() throws IOException {
        assertEmitted("net/minecraft/world/item/ItemStack", "<init>",
                List.of("Holder", "int", "PatchedDataComponentMap"),
                "Cucumber's @Inject targets it and only MixinScanner can see that");
    }

    /** {@code RecipeManagerMixin}'s {@code @Inject} into recipe reload. */
    @Test
    void recipeManagerPrepareIsInTheEmittedSource() throws IOException {
        assertEmitted("net/minecraft/world/item/crafting/RecipeManager", "prepare",
                List.of("ResourceManager", "ProfilerFiller"),
                "Cucumber's @Inject targets it and only MixinScanner can see that");
    }

    /** {@code ReloadableServerResourcesMixin}'s target. */
    @Test
    void reloadableServerResourcesUpdateComponentsIsInTheEmittedSource() throws IOException {
        assertEmitted("net/minecraft/server/ReloadableServerResources", "updateComponentsAndStaticRegistryTags",
                List.of(), "Cucumber's mixin targets it and only MixinScanner can see that");
    }

    /**
     * Asserts that {@code internalName}'s emitted file declares {@code member} with exactly
     * {@code parameterTypes}.
     *
     * <p>Parameters are matched on their erased simple names -- {@code Consumer<Item>} is
     * {@code Consumer} -- which is coarser than a descriptor but is what distinguishes the
     * overloads that actually exist: {@code ItemStack} declares {@code applyDamage(int,
     * ServerPlayer, Consumer)} beside the {@code LivingEntity} one the mixin names, and
     * six constructors. Computing the real descriptor from source would mean reproducing
     * {@link Pruner}'s import resolution here, and the thing under test is presence, not
     * descriptors.
     */
    private static void assertEmitted(String internalName, String member, List<String> parameterTypes, String why)
            throws IOException {
        Path file = SHIM.resolve(internalName + ".java");
        assertTrue(Files.isRegularFile(file),
                file + " does not exist. " + internalName + " is a mixin target: " + why);

        String simpleName = internalName.substring(internalName.lastIndexOf('/') + 1);
        CompilationUnit cu = PARSER.parse(file).getResult()
                .orElseThrow(() -> new AssertionError(file + " does not parse"));
        TypeDeclaration<?> type = cu.getPrimaryType()
                .orElseThrow(() -> new AssertionError(file + " declares no primary type"));

        List<String> declared = new ArrayList<>();
        for (CallableDeclaration<?> callable : type.getMembers().stream()
                .filter(CallableDeclaration.class::isInstance)
                .map(m -> (CallableDeclaration<?>) m)
                .toList()) {
            String name = callable.isConstructorDeclaration() ? "<init>" : callable.getNameAsString();
            if (!name.equals(member)) {
                continue;
            }
            List<String> actual = new ArrayList<>();
            for (Parameter p : callable.getParameters()) {
                actual.add(erasedSimpleName(p.getType()));
            }
            if (actual.equals(parameterTypes)) {
                return;
            }
            declared.add(actual.toString());
        }
        fail((member.equals("<init>") ? simpleName : simpleName + "." + member) + parameterTypes
                + " is not declared in " + file + " -- " + why
                + ". The pruner dropped it, or its signature changed. Overloads of that name found there: "
                + (declared.isEmpty() ? "none" : declared));
    }

    /** {@code Consumer<Item>} to {@code Consumer}; {@code int[]} to {@code int[]}. */
    private static String erasedSimpleName(Type type) {
        if (type.isArrayType()) {
            return erasedSimpleName(type.asArrayType().getComponentType()) + "[]";
        }
        if (type instanceof ClassOrInterfaceType declared) {
            return declared.getNameAsString();
        }
        return type.asString();
    }
}
