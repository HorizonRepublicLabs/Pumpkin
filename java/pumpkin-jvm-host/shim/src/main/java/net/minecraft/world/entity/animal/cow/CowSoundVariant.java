package net.minecraft.world.entity.animal.cow;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import dev.pumpkin.shim.Unimplemented;

public record CowSoundVariant(Holder<SoundEvent> ambientSound, Holder<SoundEvent> hurtSound, Holder<SoundEvent> deathSound, Holder<SoundEvent> stepSound) {

    private static Codec<CowSoundVariant> codec() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/CowSoundVariant.codec:()Lcom/mojang/serialization/Codec;");
    }
}
