package net.minecraft.client.color.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public record Constant(int value) implements ItemTintSource {

    public int calculate(ItemStack itemStack, ClientLevel level, LivingEntity owner) {
        throw Unimplemented.forMember("net/minecraft/client/color/item/Constant.calculate:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/world/entity/LivingEntity;)I");
    }

    public MapCodec<Constant> type() {
        throw Unimplemented.forMember("net/minecraft/client/color/item/Constant.type:()Lcom/mojang/serialization/MapCodec;");
    }
}
