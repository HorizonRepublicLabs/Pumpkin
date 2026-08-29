package net.minecraft.world.level.saveddata.maps;

import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;

public record MapDecoration(Holder<MapDecorationType> type, byte x, byte y, byte rot, Optional<Component> name) {
}
