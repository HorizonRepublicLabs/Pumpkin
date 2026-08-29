package net.minecraft.world.effect;

import net.minecraft.core.Holder;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class MobEffects {

    public static final Holder<MobEffect> SLOWNESS = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> HASTE = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> MINING_FATIGUE = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> JUMP_BOOST = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> NAUSEA = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> FIRE_RESISTANCE = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> WATER_BREATHING = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> BLINDNESS = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> NIGHT_VISION = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> HUNGER = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> WEAKNESS = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> POISON = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> WITHER = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> ABSORPTION = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> LUCK = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<MobEffect> SLOW_FALLING = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    private static Holder<MobEffect> register(String name, MobEffect mobEffect) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffects.register:(Ljava/lang/String;Lnet/minecraft/world/effect/MobEffect;)Lnet/minecraft/core/Holder;");
    }

    protected MobEffects() {
    }
}
