package net.minecraft.world.entity.ai.attributes;

import net.minecraft.core.Holder;
import dev.pumpkin.shim.Unimplemented;

public class Attributes {

    public static final Holder<Attribute> ATTACK_DAMAGE = null;

    public static final Holder<Attribute> BLOCK_INTERACTION_RANGE = null;

    public static final Holder<Attribute> MAX_HEALTH = null;

    public static final Holder<Attribute> STEP_HEIGHT = null;

    private static Holder<Attribute> register(String name, Attribute attribute) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/Attributes.register:(Ljava/lang/String;Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/core/Holder;");
    }

    protected Attributes() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/Attributes");
        }
    }
}
