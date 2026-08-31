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

    // Pumpkin divergence: vanilla body -- the lowercase constant name.
    public String getSerializedName() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }

    private enum Type {

        BASE_BLOCK, MOB_HEAD, CUSTOM
    }
}
