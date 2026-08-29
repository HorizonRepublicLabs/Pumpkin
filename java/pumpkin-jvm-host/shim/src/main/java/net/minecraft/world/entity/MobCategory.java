package net.minecraft.world.entity;

import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Unimplemented;

public enum MobCategory implements StringRepresentable, IExtensibleEnum {

    MONSTER,
    CREATURE,
    AMBIENT,
    AXOLOTLS,
    UNDERGROUND_WATER_CREATURE,
    WATER_CREATURE,
    WATER_AMBIENT,
    MISC;

    private final boolean isFriendly = false;

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/MobCategory.getName:()Ljava/lang/String;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/MobCategory.getSerializedName:()Ljava/lang/String;");
    }

    public boolean isFriendly() {
        throw Unimplemented.forMember("net/minecraft/world/entity/MobCategory.isFriendly:()Z");
    }
}
