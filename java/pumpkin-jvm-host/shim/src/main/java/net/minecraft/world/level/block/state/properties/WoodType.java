package net.minecraft.world.level.block.state.properties;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import dev.pumpkin.shim.Unimplemented;

public record WoodType(String name, BlockSetType setType, SoundType soundType, SoundType hangingSignSoundType, SoundEvent fenceGateClose, SoundEvent fenceGateOpen) {

    public WoodType(String name, BlockSetType setType) {
        this((String) null, (BlockSetType) null, (SoundType) null, (SoundType) null, (SoundEvent) null, (SoundEvent) null);
    }

    public static WoodType register(WoodType type) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/WoodType.register:(Lnet/minecraft/world/level/block/state/properties/WoodType;)Lnet/minecraft/world/level/block/state/properties/WoodType;");
    }
}
