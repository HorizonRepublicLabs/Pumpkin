package net.minecraft.world.entity.ai.attributes;

import net.minecraft.core.Holder;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class Attributes {

    public static final Holder<Attribute> ATTACK_DAMAGE = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<Attribute> BLOCK_INTERACTION_RANGE = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<Attribute> MAX_HEALTH = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<Attribute> STEP_HEIGHT = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    private static Holder<Attribute> register(String name, Attribute attribute) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/Attributes.register:(Ljava/lang/String;Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/core/Holder;");
    }

    public Attributes() {
    }
}
