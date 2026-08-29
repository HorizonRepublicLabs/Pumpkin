package net.minecraft.network.chat;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.component.ResolvableProfile;

public interface FontDescription {

    record AtlasSprite(Identifier atlasId, Identifier spriteId) implements FontDescription {
    }

    record PlayerSprite(ResolvableProfile profile, boolean hat) implements FontDescription {
    }

    record Resource(Identifier id) implements FontDescription {
    }
}
