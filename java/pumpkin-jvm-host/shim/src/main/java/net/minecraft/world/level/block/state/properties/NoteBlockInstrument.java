package net.minecraft.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum NoteBlockInstrument implements StringRepresentable {

    HARP,
    BASEDRUM,
    SNARE,
    HAT,
    BASS,
    FLUTE,
    BELL,
    GUITAR,
    CHIME,
    XYLOPHONE,
    IRON_XYLOPHONE,
    COW_BELL,
    DIDGERIDOO,
    BIT,
    BANJO,
    PLING,
    TRUMPET,
    TRUMPET_EXPOSED,
    TRUMPET_OXIDIZED,
    TRUMPET_WEATHERED,
    ZOMBIE,
    SKELETON,
    CREEPER,
    DRAGON,
    WITHER_SKELETON,
    PIGLIN,
    CUSTOM_HEAD;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/NoteBlockInstrument.getSerializedName:()Ljava/lang/String;");
    }

    private enum Type {

        BASE_BLOCK, MOB_HEAD, CUSTOM
    }
}
