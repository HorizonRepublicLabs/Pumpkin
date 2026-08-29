package net.minecraft.core.particles;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import dev.pumpkin.shim.Unimplemented;

public class ItemParticleOption implements ParticleOptions {

    public ItemParticleOption(ParticleType<ItemParticleOption> type, Item item) {
    }

    public ItemParticleOption(ParticleType<ItemParticleOption> type, ItemStackTemplate itemStack) {
    }

    public ParticleType<ItemParticleOption> getType() {
        throw Unimplemented.forMember("net/minecraft/core/particles/ItemParticleOption.getType:()Lnet/minecraft/core/particles/ParticleType;");
    }

    public ItemParticleOption() {
    }
}
