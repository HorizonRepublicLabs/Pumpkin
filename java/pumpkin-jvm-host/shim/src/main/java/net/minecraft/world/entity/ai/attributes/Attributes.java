package net.minecraft.world.entity.ai.attributes;

import net.minecraft.core.Holder;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class Attributes {

    // Attribute's constructor is protected; this is the smallest door to it.
    private static final class PumpkinAttribute extends Attribute {
        PumpkinAttribute(String descriptionId, double defaultValue) {
            super(descriptionId, defaultValue);
        }
    }


    // Pumpkin divergence: the holder answers value() with a real attribute --
    // vanilla's own id and default, read from NeoForge's source. Mekanism sizes
    // its gear config around these defaults at class-initialisation.
    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> ARMOR = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.armor", 0.0)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> ARMOR_TOUGHNESS = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.armor_toughness", 0.0)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> ATTACK_DAMAGE = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.attack_damage", 2.0)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> ATTACK_SPEED = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.attack_speed", 4.0)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> BLOCK_INTERACTION_RANGE = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.block_interaction_range", 4.5)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> FALL_DAMAGE_MULTIPLIER = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.fall_damage_multiplier", 1.0)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> KNOCKBACK_RESISTANCE = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.knockback_resistance", 0.0)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> MAX_HEALTH = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.max_health", 20.0)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> MOVEMENT_EFFICIENCY = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.movement_efficiency", 0.0)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> MOVEMENT_SPEED = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.movement_speed", 0.7)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> SAFE_FALL_DISTANCE = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.safe_fall_distance", 3.0)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> SNEAKING_SPEED = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.sneaking_speed", 0.3)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> STEP_HEIGHT = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.step_height", 0.6)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> SUBMERGED_MINING_SPEED = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.submerged_mining_speed", 0.2)));

    @SuppressWarnings("unchecked")
    public static final Holder<Attribute> WATER_MOVEMENT_EFFICIENCY = Stubs.of(Holder.class,
            "net/minecraft/core/Holder", java.util.Map.of("value",
                    new PumpkinAttribute("attribute.name.water_movement_efficiency", 0.0)));

    private static Holder<Attribute> register(String name, Attribute attribute) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/Attributes.register:(Ljava/lang/String;Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/core/Holder;");
    }

    public Attributes() {
    }
}
