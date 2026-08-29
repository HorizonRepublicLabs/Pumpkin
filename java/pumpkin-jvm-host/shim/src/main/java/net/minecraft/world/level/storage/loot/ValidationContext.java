package net.minecraft.world.level.storage.loot;

import java.util.Optional;
import java.util.Set;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.context.ContextKey;
import net.minecraft.util.context.ContextKeySet;
import dev.pumpkin.shim.Unimplemented;

public class ValidationContext {

    public ValidationContext(ProblemReporter reporter, ContextKeySet contextKeySet, HolderGetter.Provider resolver) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/ValidationContext.<init>:(Lnet/minecraft/util/ProblemReporter;Lnet/minecraft/util/context/ContextKeySet;Lnet/minecraft/core/HolderGetter$Provider;)V");
    }

    public ValidationContext(ProblemReporter reporter, ContextKeySet contextKeySet) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/ValidationContext.<init>:(Lnet/minecraft/util/ProblemReporter;Lnet/minecraft/util/context/ContextKeySet;)V");
    }

    private ValidationContext(ProblemReporter reporter, ContextKeySet contextKeySet, Optional<HolderGetter.Provider> resolver, Set<ResourceKey<?>> visitedElements) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/ValidationContext.<init>:(Lnet/minecraft/util/ProblemReporter;Lnet/minecraft/util/context/ContextKeySet;Ljava/util/Optional;Ljava/util/Set;)V");
    }

    public ValidationContext forChild(ProblemReporter.PathElement subContext) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/ValidationContext.forChild:(Lnet/minecraft/util/ProblemReporter$PathElement;)Lnet/minecraft/world/level/storage/loot/ValidationContext;");
    }

    public record MissingReferenceProblem(ResourceKey<?> referenced) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/ValidationContext$MissingReferenceProblem.description:()Ljava/lang/String;");
        }
    }

    public record ParametersNotProvidedProblem(Set<ContextKey<?>> notProvided) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/ValidationContext$ParametersNotProvidedProblem.description:()Ljava/lang/String;");
        }
    }

    public record RecursiveReferenceProblem(ResourceKey<?> referenced) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/ValidationContext$RecursiveReferenceProblem.description:()Ljava/lang/String;");
        }
    }

    public record ReferenceNotAllowedProblem(ResourceKey<?> referenced) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/ValidationContext$ReferenceNotAllowedProblem.description:()Ljava/lang/String;");
        }
    }

    public ValidationContext() {
    }
}
