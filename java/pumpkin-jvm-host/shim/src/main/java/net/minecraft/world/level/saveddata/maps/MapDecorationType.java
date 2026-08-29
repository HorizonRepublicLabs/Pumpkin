package net.minecraft.world.level.saveddata.maps;

import net.minecraft.resources.Identifier;

public record MapDecorationType(Identifier assetId, boolean showOnItemFrame, int mapColor, boolean explorationMapElement, boolean trackCount) {
}
