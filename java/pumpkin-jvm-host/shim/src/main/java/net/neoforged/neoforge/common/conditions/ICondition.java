package net.neoforged.neoforge.common.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.RegistryAccess;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import dev.pumpkin.shim.Unimplemented;

public interface ICondition {

    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while

    // composing at class-init; null there is an NPE naming nothing. This survives

    // composition and throws on first real serialisation, naming the field.

    Codec<ICondition> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/neoforged/neoforge/common/conditions/ICondition.CODEC");

    boolean test(IContext context);

    MapCodec<? extends ICondition> codec();

    interface IContext {

        <T> boolean isTagLoaded(TagKey<T> key);

        default RegistryAccess registryAccess() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/conditions/ICondition$IContext.registryAccess:()Lnet/minecraft/core/RegistryAccess;");
        }

        default FeatureFlagSet enabledFeatures() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/conditions/ICondition$IContext.enabledFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
        }
    }
}
