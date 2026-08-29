package net.minecraft.world.effect;

import java.util.Optional;
import net.minecraft.core.Holder;
import dev.pumpkin.shim.Unimplemented;

public class MobEffectInstance implements Comparable<MobEffectInstance> {

    public MobEffectInstance(Holder<MobEffect> effect) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.<init>:(Lnet/minecraft/core/Holder;)V");
    }

    public MobEffectInstance(Holder<MobEffect> effect, int duration) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.<init>:(Lnet/minecraft/core/Holder;I)V");
    }

    public MobEffectInstance(Holder<MobEffect> effect, int duration, int amplifier) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.<init>:(Lnet/minecraft/core/Holder;II)V");
    }

    public MobEffectInstance(Holder<MobEffect> effect, int duration, int amplifier, boolean ambient, boolean visible) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.<init>:(Lnet/minecraft/core/Holder;IIZZ)V");
    }

    public MobEffectInstance(Holder<MobEffect> effect, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.<init>:(Lnet/minecraft/core/Holder;IIZZZ)V");
    }

    public MobEffectInstance(Holder<MobEffect> effect, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon, MobEffectInstance hiddenEffect) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.<init>:(Lnet/minecraft/core/Holder;IIZZZLnet/minecraft/world/effect/MobEffectInstance;)V");
    }

    public MobEffectInstance(MobEffectInstance copy) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.<init>:(Lnet/minecraft/world/effect/MobEffectInstance;)V");
    }

    private MobEffectInstance(Holder<MobEffect> effect, MobEffectInstance.Details details) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.<init>:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/effect/MobEffectInstance$Details;)V");
    }

    public boolean update(MobEffectInstance takeOver) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.update:(Lnet/minecraft/world/effect/MobEffectInstance;)Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.toString:()Ljava/lang/String;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.hashCode:()I");
    }

    public int compareTo(MobEffectInstance o) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.compareTo:(Lnet/minecraft/world/effect/MobEffectInstance;)I");
    }

    public boolean is(Holder<MobEffect> effect) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.is:(Lnet/minecraft/core/Holder;)Z");
    }

    private static class BlendState {

        protected BlendState() {
        }
    }

    private record Details(int amplifier, int duration, boolean ambient, boolean showParticles, boolean showIcon, Optional<MobEffectInstance.Details> hiddenEffect) {

        private static MobEffectInstance.Details create(int amplifier, int duration, boolean ambient, boolean showParticles, Optional<Boolean> showIcon, Optional<MobEffectInstance.Details> hiddenEffect) {
            throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance$Details.create:(IIZZLjava/util/Optional;Ljava/util/Optional;)Lnet/minecraft/world/effect/MobEffectInstance$Details;");
        }
    }

    protected MobEffectInstance() {
    }
}
