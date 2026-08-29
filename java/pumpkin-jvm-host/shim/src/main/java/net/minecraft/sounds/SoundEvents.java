package net.minecraft.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class SoundEvents {

    public static final Holder<SoundEvent> ARMOR_EQUIP_GOLD = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final SoundEvent ARROW_SHOOT = null;

    public static final SoundEvent AXE_STRIP = null;

    public static final SoundEvent AXE_SCRAPE = null;

    public static final SoundEvent AXE_WAX_OFF = null;

    public static final SoundEvent BUCKET_FILL = null;

    public static final SoundEvent CROP_BREAK = null;

    public static final SoundEvent EXPERIENCE_ORB_PICKUP = null;

    public static final SoundEvent HOE_TILL = null;

    public static final SoundEvent ITEM_PICKUP = null;

    public static final SoundEvent PLAYER_ATTACK_SWEEP = null;

    public static final SoundEvent SAND_BREAK = null;

    public static final SoundEvent SHOVEL_FLATTEN = null;

    private static SoundEvent register(String id) {
        throw Unimplemented.forMember("net/minecraft/sounds/SoundEvents.register:(Ljava/lang/String;)Lnet/minecraft/sounds/SoundEvent;");
    }

    private static SoundEvent register(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/sounds/SoundEvents.register:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/sounds/SoundEvent;");
    }

    private static SoundEvent register(Identifier id, Identifier soundId) {
        throw Unimplemented.forMember("net/minecraft/sounds/SoundEvents.register:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvents() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/sounds/SoundEvents");
        }
    }
}
