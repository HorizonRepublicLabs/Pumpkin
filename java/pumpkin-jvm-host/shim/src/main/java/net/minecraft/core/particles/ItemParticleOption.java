package net.minecraft.core.particles;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import dev.pumpkin.shim.Unimplemented;

public class ItemParticleOption implements ParticleOptions {

    public ItemParticleOption(ParticleType<ItemParticleOption> type, Item item) {
        throw Unimplemented.forMember("net/minecraft/core/particles/ItemParticleOption.<init>:(Lnet/minecraft/core/particles/ParticleType;Lnet/minecraft/world/item/Item;)V");
    }

    public ItemParticleOption(ParticleType<ItemParticleOption> type, ItemStackTemplate itemStack) {
        throw Unimplemented.forMember("net/minecraft/core/particles/ItemParticleOption.<init>:(Lnet/minecraft/core/particles/ParticleType;Lnet/minecraft/world/item/ItemStackTemplate;)V");
    }

    public ParticleType<ItemParticleOption> getType() {
        throw Unimplemented.forMember("net/minecraft/core/particles/ItemParticleOption.getType:()Lnet/minecraft/core/particles/ParticleType;");
    }

    public ItemParticleOption() {
    }
}
