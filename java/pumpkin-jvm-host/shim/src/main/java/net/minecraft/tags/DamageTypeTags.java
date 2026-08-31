package net.minecraft.tags;

import net.minecraft.world.damagesource.DamageType;
import dev.pumpkin.shim.Unimplemented;

public interface DamageTypeTags {

    TagKey<DamageType> BYPASSES_ARMOR = null;

    TagKey<DamageType> BYPASSES_SHIELD = null;

    TagKey<DamageType> IS_FIRE = null;

    private static TagKey<DamageType> create(String name) {
        throw Unimplemented.forMember("net/minecraft/tags/DamageTypeTags.create:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
    }
}
