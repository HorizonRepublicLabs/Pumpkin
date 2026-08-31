package net.minecraft.world.effect;

import java.util.Optional;
import net.minecraft.core.Holder;
import dev.pumpkin.shim.Unimplemented;

public class MobEffectInstance implements Comparable<MobEffectInstance> {

    private MobEffectInstance hiddenEffect;

    public MobEffectInstance(Holder<MobEffect> effect) {
    }

    public MobEffectInstance(Holder<MobEffect> effect, int duration) {
    }

    public MobEffectInstance(Holder<MobEffect> effect, int duration, int amplifier) {
    }

    public MobEffectInstance(Holder<MobEffect> effect, int duration, int amplifier, boolean ambient, boolean visible) {
    }

    public MobEffectInstance(Holder<MobEffect> effect, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {
    }

    public MobEffectInstance(Holder<MobEffect> effect, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon, MobEffectInstance hiddenEffect) {
    }

    public MobEffectInstance(MobEffectInstance copy) {
    }

    private MobEffectInstance(Holder<MobEffect> effect, MobEffectInstance.Details details) {
    }

    private void setDetailsFrom(MobEffectInstance copy) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.setDetailsFrom:(Lnet/minecraft/world/effect/MobEffectInstance;)V");
    }

    public boolean update(MobEffectInstance takeOver) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.update:(Lnet/minecraft/world/effect/MobEffectInstance;)Z");
    }

    public Holder<MobEffect> getEffect() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.getEffect:()Lnet/minecraft/core/Holder;");
    }

    public int getDuration() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.getDuration:()I");
    }

    public int getAmplifier() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.getAmplifier:()I");
    }

    private void tickDownDuration() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffectInstance.tickDownDuration:()V");
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

    public MobEffectInstance() {
    }
}
