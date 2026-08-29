package net.minecraft.advancements;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum AdvancementType implements StringRepresentable {

    TASK, CHALLENGE, GOAL;

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementType.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementType.getSerializedName:()Ljava/lang/String;");
    }
}
