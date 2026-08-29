package net.minecraft.world.level.saveddata.maps;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import dev.pumpkin.shim.Unimplemented;

public record MapBanner(BlockPos pos, DyeColor color, Optional<Component> name) {

    public String getId() {
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/maps/MapBanner.getId:()Ljava/lang/String;");
    }
}
