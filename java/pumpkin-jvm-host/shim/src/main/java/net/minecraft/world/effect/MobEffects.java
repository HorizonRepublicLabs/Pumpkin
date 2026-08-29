package net.minecraft.world.effect;

import net.minecraft.core.Holder;
import dev.pumpkin.shim.Unimplemented;

public class MobEffects {

    public static final Holder<MobEffect> SLOWNESS = null;

    public static final Holder<MobEffect> HASTE = null;

    public static final Holder<MobEffect> MINING_FATIGUE = null;

    public static final Holder<MobEffect> JUMP_BOOST = null;

    public static final Holder<MobEffect> NAUSEA = null;

    public static final Holder<MobEffect> FIRE_RESISTANCE = null;

    public static final Holder<MobEffect> WATER_BREATHING = null;

    public static final Holder<MobEffect> BLINDNESS = null;

    public static final Holder<MobEffect> NIGHT_VISION = null;

    public static final Holder<MobEffect> HUNGER = null;

    public static final Holder<MobEffect> WEAKNESS = null;

    public static final Holder<MobEffect> POISON = null;

    public static final Holder<MobEffect> WITHER = null;

    public static final Holder<MobEffect> ABSORPTION = null;

    public static final Holder<MobEffect> LUCK = null;

    public static final Holder<MobEffect> SLOW_FALLING = null;

    private static Holder<MobEffect> register(String name, MobEffect mobEffect) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffects.register:(Ljava/lang/String;Lnet/minecraft/world/effect/MobEffect;)Lnet/minecraft/core/Holder;");
    }

    protected MobEffects() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/world/effect/MobEffects");
        }
    }
}
