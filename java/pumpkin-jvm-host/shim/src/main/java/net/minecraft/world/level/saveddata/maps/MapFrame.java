package net.minecraft.world.level.saveddata.maps;

import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public record MapFrame(BlockPos pos, int rotation, int entityId) {

    public String getId() {
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/maps/MapFrame.getId:()Ljava/lang/String;");
    }
}
