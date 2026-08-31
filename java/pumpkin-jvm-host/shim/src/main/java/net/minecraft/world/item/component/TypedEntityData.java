package net.minecraft.world.item.component;

import java.util.function.Consumer;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import dev.pumpkin.shim.Unimplemented;

public final class TypedEntityData<IdType> implements TooltipProvider {

    private TypedEntityData(IdType type, CompoundTag data) {
    }

    public IdType type() {
        throw Unimplemented.forMember("net/minecraft/world/item/component/TypedEntityData.type:()Ljava/lang/Object;");
    }

    public boolean contains(String name) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/TypedEntityData.contains:(Ljava/lang/String;)Z");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/TypedEntityData.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/item/component/TypedEntityData.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/item/component/TypedEntityData.toString:()Ljava/lang/String;");
    }

    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/TypedEntityData.addToTooltip:(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    public TypedEntityData() {
    }
}
