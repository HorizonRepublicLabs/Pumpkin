package net.minecraft.world.level.block.state.properties;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import dev.pumpkin.shim.Unimplemented;

public record BlockSetType(String name, boolean canOpenByHand, boolean canOpenByWindCharge, boolean canButtonBeActivatedByArrows, BlockSetType.PressurePlateSensitivity pressurePlateSensitivity, SoundType soundType, SoundEvent doorClose, SoundEvent doorOpen, SoundEvent trapdoorClose, SoundEvent trapdoorOpen, SoundEvent pressurePlateClickOff, SoundEvent pressurePlateClickOn, SoundEvent buttonClickOff, SoundEvent buttonClickOn) {

    public BlockSetType(String name) {
        this((String) null, (boolean) false, (boolean) false, (boolean) false, (BlockSetType.PressurePlateSensitivity) null, (SoundType) null, (SoundEvent) null, (SoundEvent) null, (SoundEvent) null, (SoundEvent) null, (SoundEvent) null, (SoundEvent) null, (SoundEvent) null, (SoundEvent) null);
    }

    public static BlockSetType register(BlockSetType type) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BlockSetType.register:(Lnet/minecraft/world/level/block/state/properties/BlockSetType;)Lnet/minecraft/world/level/block/state/properties/BlockSetType;");
    }

    public enum PressurePlateSensitivity {

        EVERYTHING, MOBS
    }
}
