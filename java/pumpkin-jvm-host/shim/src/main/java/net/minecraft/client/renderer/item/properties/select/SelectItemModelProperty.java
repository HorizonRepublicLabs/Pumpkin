package net.minecraft.client.renderer.item.properties.select;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public interface SelectItemModelProperty<T> {

    T get(ItemStack itemStack, ClientLevel level, LivingEntity owner, int seed, ItemDisplayContext displayContext);

    Codec<T> valueCodec();

    SelectItemModelProperty.Type<? extends SelectItemModelProperty<T>, T> type();

    record Type<P extends SelectItemModelProperty<T>, T>(MapCodec<SelectItemModel.UnbakedSwitch<P, T>> switchCodec) {

        public static <P extends SelectItemModelProperty<T>, T> SelectItemModelProperty.Type<P, T> create(MapCodec<P> propertyMapCodec, Codec<T> valueCodec) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/properties/select/SelectItemModelProperty$Type.create:(Lcom/mojang/serialization/MapCodec;Lcom/mojang/serialization/Codec;)Lnet/minecraft/client/renderer/item/properties/select/SelectItemModelProperty$Type;");
        }
    }
}
