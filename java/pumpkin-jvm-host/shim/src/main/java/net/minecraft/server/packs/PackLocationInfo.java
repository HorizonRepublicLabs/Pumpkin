package net.minecraft.server.packs;

import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.repository.KnownPack;
import net.minecraft.server.packs.repository.PackSource;

public record PackLocationInfo(String id, Component title, PackSource source, Optional<KnownPack> knownPackInfo) {
}
