package net.minecraft.world.level.block;

import net.minecraft.sounds.SoundEvent;
import dev.pumpkin.shim.Unimplemented;

public class SoundType {

    public static final SoundType STONE = null;

    public static final SoundType METAL = null;

    public static final SoundType GLASS = null;

    public static final SoundType DEEPSLATE = null;

    public SoundType(float volume, float pitch, SoundEvent breakSound, SoundEvent stepSound, SoundEvent placeSound, SoundEvent hitSound, SoundEvent fallSound) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SoundType.<init>:(FFLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundEvent;)V");
    }

    public float getVolume() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SoundType.getVolume:()F");
    }

    public float getPitch() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SoundType.getPitch:()F");
    }

    public SoundType() {
    }
}
