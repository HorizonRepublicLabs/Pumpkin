package net.minecraft.world.item.equipment.trim;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public record TrimPattern(Identifier assetId, Component description, boolean decal) {
}
