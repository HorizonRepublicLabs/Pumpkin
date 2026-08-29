package net.minecraft.world.timeline;

import net.minecraft.util.KeyframeTrack;
import net.minecraft.world.attribute.modifier.AttributeModifier;

public record AttributeTrack<Value, Argument>(AttributeModifier<Value, Argument> modifier, KeyframeTrack<Argument> argumentTrack) {
}
