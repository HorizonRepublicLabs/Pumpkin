package net.minecraft.advancements;

import com.mojang.serialization.DataResult;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.core.HolderGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ProblemReporter;
import net.neoforged.neoforge.common.extensions.IAdvancementBuilderExtension;
import dev.pumpkin.shim.Unimplemented;

public record Advancement(Optional<Identifier> parent, Optional<DisplayInfo> display, AdvancementRewards rewards, Map<String, Criterion<?>> criteria, AdvancementRequirements requirements, boolean sendsTelemetryEvent, Optional<Component> name) {

    public Advancement(Optional<Identifier> parent, Optional<DisplayInfo> display, AdvancementRewards rewards, Map<String, Criterion<?>> criteria, AdvancementRequirements requirements, boolean sendsTelemetryEvent) {
        this((Optional<Identifier>) null, (Optional<DisplayInfo>) null, (AdvancementRewards) null, (Map<String, Criterion<?>>) null, (AdvancementRequirements) null, (boolean) false, (Optional<Component>) null);
    }

    private static DataResult<Advancement> validate(Advancement advancement) {
        throw Unimplemented.forMember("net/minecraft/advancements/Advancement.validate:(Lnet/minecraft/advancements/Advancement;)Lcom/mojang/serialization/DataResult;");
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/advancements/Advancement.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    private static Advancement read(RegistryFriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/advancements/Advancement.read:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)Lnet/minecraft/advancements/Advancement;");
    }

    public void validate(ProblemReporter reporter, HolderGetter.Provider lootData) {
        throw Unimplemented.forMember("net/minecraft/advancements/Advancement.validate:(Lnet/minecraft/util/ProblemReporter;Lnet/minecraft/core/HolderGetter$Provider;)V");
    }

    public static class Builder implements IAdvancementBuilderExtension {

        private AdvancementRewards rewards;

        private Optional<AdvancementRequirements> requirements;

        public static Advancement.Builder advancement() {
            throw Unimplemented.forMember("net/minecraft/advancements/Advancement$Builder.advancement:()Lnet/minecraft/advancements/Advancement$Builder;");
        }

        public Advancement.Builder rewards(AdvancementRewards.Builder rewards) {
            throw Unimplemented.forMember("net/minecraft/advancements/Advancement$Builder.rewards:(Lnet/minecraft/advancements/AdvancementRewards$Builder;)Lnet/minecraft/advancements/Advancement$Builder;");
        }

        public Advancement.Builder rewards(AdvancementRewards rewards) {
            throw Unimplemented.forMember("net/minecraft/advancements/Advancement$Builder.rewards:(Lnet/minecraft/advancements/AdvancementRewards;)Lnet/minecraft/advancements/Advancement$Builder;");
        }

        public Advancement.Builder addCriterion(String name, Criterion<?> criterion) {
            throw Unimplemented.forMember("net/minecraft/advancements/Advancement$Builder.addCriterion:(Ljava/lang/String;Lnet/minecraft/advancements/triggers/Criterion;)Lnet/minecraft/advancements/Advancement$Builder;");
        }

        public Advancement.Builder requirements(AdvancementRequirements.Strategy strategy) {
            throw Unimplemented.forMember("net/minecraft/advancements/Advancement$Builder.requirements:(Lnet/minecraft/advancements/AdvancementRequirements$Strategy;)Lnet/minecraft/advancements/Advancement$Builder;");
        }

        public Advancement.Builder requirements(AdvancementRequirements requirements) {
            throw Unimplemented.forMember("net/minecraft/advancements/Advancement$Builder.requirements:(Lnet/minecraft/advancements/AdvancementRequirements;)Lnet/minecraft/advancements/Advancement$Builder;");
        }

        public AdvancementHolder build(Identifier id) {
            throw Unimplemented.forMember("net/minecraft/advancements/Advancement$Builder.build:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/advancements/AdvancementHolder;");
        }

        public AdvancementHolder save(Consumer<AdvancementHolder> output, String name) {
            throw Unimplemented.forMember("net/minecraft/advancements/Advancement$Builder.save:(Ljava/util/function/Consumer;Ljava/lang/String;)Lnet/minecraft/advancements/AdvancementHolder;");
        }

        public Builder() {
        }
    }
}
