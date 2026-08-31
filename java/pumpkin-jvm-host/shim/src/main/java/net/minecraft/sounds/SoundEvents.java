package net.minecraft.sounds;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class SoundEvents {

    public static final Holder<SoundEvent> ARMOR_EQUIP_DIAMOND = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<SoundEvent> ARMOR_EQUIP_ELYTRA = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<SoundEvent> ARMOR_EQUIP_GENERIC = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<SoundEvent> ARMOR_EQUIP_GOLD = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<SoundEvent> ARMOR_EQUIP_IRON = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    // Pumpkin divergence: real objects, deliberately non-vanilla names. Vanilla's
    // sound ids are not derivable from field names, and a guessed name would be
    // plausibly wrong -- silent, if something ever plays it. "pumpkin:unmapped_*"
    // is visibly not a real sound, so a mod that plays one fails loudly instead.
    public static final SoundEvent ARROW_SHOOT = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_arrow_shoot"));

    public static final SoundEvent AXE_STRIP = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_axe_strip"));

    public static final SoundEvent AXE_SCRAPE = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_axe_scrape"));

    public static final SoundEvent AXE_WAX_OFF = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_axe_wax_off"));

    public static final SoundEvent BARREL_CLOSE = null;

    public static final SoundEvent BARREL_OPEN = null;

    public static final SoundEvent BUCKET_EMPTY = null;

    public static final SoundEvent BUCKET_FILL = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_bucket_fill"));

    public static final SoundEvent CHEST_CLOSE = null;

    public static final SoundEvent CHEST_OPEN = null;

    public static final SoundEvent COW_MILK = null;

    public static final SoundEvent CROP_BREAK = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_crop_break"));

    public static final SoundEvent ENDERMAN_TELEPORT = null;

    public static final SoundEvent EXPERIENCE_ORB_PICKUP = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_experience_orb_pickup"));

    public static final SoundEvent FENCE_GATE_CLOSE = null;

    public static final SoundEvent FENCE_GATE_OPEN = null;

    public static final SoundEvent FIRE_EXTINGUISH = null;

    public static final SoundEvent FOX_TELEPORT = null;

    public static final Holder.Reference<SoundEvent> GENERIC_EXPLODE = null;

    public static final SoundEvent GOAT_MILK = null;

    public static final SoundEvent GOAT_SCREAMING_MILK = null;

    public static final SoundEvent HOE_TILL = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_hoe_till"));

    public static final SoundEvent ITEM_PICKUP = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_item_pickup"));

    public static final SoundEvent MOOSHROOM_MILK = null;

    public static final SoundEvent PLAYER_ATTACK_SWEEP = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_player_attack_sweep"));

    public static final SoundEvent PLAYER_BURP = null;

    public static final SoundEvent PLAYER_TELEPORT = null;

    public static final SoundEvent SAND_BREAK = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_sand_break"));

    public static final Holder.Reference<SoundEvent> SHIELD_BLOCK = null;

    public static final Holder.Reference<SoundEvent> SHIELD_BREAK = null;

    public static final SoundEvent SHOVEL_FLATTEN = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_shovel_flatten"));

    public static final SoundEvent SHULKER_TELEPORT = null;

    public static final SoundEvent STONE_PRESSURE_PLATE_CLICK_OFF = null;

    public static final SoundEvent STONE_PRESSURE_PLATE_CLICK_ON = null;

    public static final SoundEvent TNT_PRIMED = null;

    public static final Holder.Reference<SoundEvent> UI_BUTTON_CLICK = null;

    private static SoundEvent register(String id) {
        throw Unimplemented.forMember("net/minecraft/sounds/SoundEvents.register:(Ljava/lang/String;)Lnet/minecraft/sounds/SoundEvent;");
    }

    private static SoundEvent register(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/sounds/SoundEvents.register:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/sounds/SoundEvent;");
    }

    private static SoundEvent register(Identifier id, Identifier soundId) {
        throw Unimplemented.forMember("net/minecraft/sounds/SoundEvents.register:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/sounds/SoundEvent;");
    }

    public SoundEvents() {
    }
}
