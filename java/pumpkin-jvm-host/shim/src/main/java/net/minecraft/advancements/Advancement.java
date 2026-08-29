package net.minecraft.advancements;

import com.mojang.serialization.DataResult;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.extensions.IAdvancementBuilderExtension;
import dev.pumpkin.shim.Unimplemented;

public record Advancement(Optional<Identifier> parent, Optional<DisplayInfo> display, AdvancementRewards rewards, Map<String, Criterion<?>> criteria, AdvancementRequirements requirements, boolean sendsTelemetryEvent, Optional<Component> name) {

    public Advancement(Optional<Identifier> parent, Optional<DisplayInfo> display, AdvancementRewards rewards, Map<String, Criterion<?>> criteria, AdvancementRequirements requirements, boolean sendsTelemetryEvent) {
        this((Optional<Identifier>) null, (Optional<DisplayInfo>) null, (AdvancementRewards) null, (Map<String, Criterion<?>>) null, (AdvancementRequirements) null, (boolean) false, (Optional<Component>) null);
        throw Unimplemented.forMember("net/minecraft/advancements/Advancement.<init>:(Ljava/util/Optional;Ljava/util/Optional;Lnet/minecraft/advancements/AdvancementRewards;Ljava/util/Map;Lnet/minecraft/advancements/AdvancementRequirements;Z)V");
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

    public static class Builder implements IAdvancementBuilderExtension {

        public static Advancement.Builder advancement() {
            throw Unimplemented.forMember("net/minecraft/advancements/Advancement$Builder.advancement:()Lnet/minecraft/advancements/Advancement$Builder;");
        }

        public AdvancementHolder save(Consumer<AdvancementHolder> output, String name) {
            throw Unimplemented.forMember("net/minecraft/advancements/Advancement$Builder.save:(Ljava/util/function/Consumer;Ljava/lang/String;)Lnet/minecraft/advancements/AdvancementHolder;");
        }

        protected Builder() {
        }
    }
}
