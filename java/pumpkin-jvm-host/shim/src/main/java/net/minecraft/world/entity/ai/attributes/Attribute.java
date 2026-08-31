package net.minecraft.world.entity.ai.attributes;

import net.neoforged.neoforge.common.extensions.IAttributeExtension;
import dev.pumpkin.shim.Unimplemented;

public class Attribute implements IAttributeExtension {

    // Pumpkin divergence: real fields -- an attribute is its id and default.
    private String pumpkinDescriptionId;

    private double pumpkinDefaultValue;

    protected Attribute(String descriptionId, double defaultValue) {
        this.pumpkinDescriptionId = descriptionId;
        this.pumpkinDefaultValue = defaultValue;
    }

    public double getDefaultValue() {
        return pumpkinDefaultValue;
    }

    public net.minecraft.network.chat.TextColor getMergedStyle(boolean isPositive) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/Attribute.getMergedStyle:(Z)Lnet/minecraft/network/chat/TextColor;");
    }

    public enum Sentiment {

        POSITIVE, NEUTRAL, NEGATIVE
    }

    public Attribute() {
    }
}
