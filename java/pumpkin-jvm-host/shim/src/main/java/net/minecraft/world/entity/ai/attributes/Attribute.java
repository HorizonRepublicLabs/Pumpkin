package net.minecraft.world.entity.ai.attributes;

import net.neoforged.neoforge.common.extensions.IAttributeExtension;
import dev.pumpkin.shim.Unimplemented;

public class Attribute implements IAttributeExtension {

    protected Attribute(String descriptionId, double defaultValue) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/Attribute.<init>:(Ljava/lang/String;D)V");
    }

    public net.minecraft.network.chat.TextColor getMergedStyle(boolean isPositive) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/Attribute.getMergedStyle:(Z)Lnet/minecraft/network/chat/TextColor;");
    }

    public enum Sentiment {

        POSITIVE, NEUTRAL, NEGATIVE
    }

    protected Attribute() {
    }
}
