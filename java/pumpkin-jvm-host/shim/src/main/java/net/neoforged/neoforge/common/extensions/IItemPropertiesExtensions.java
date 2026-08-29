package net.neoforged.neoforge.common.extensions;

import java.util.function.Supplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.Item;
import dev.pumpkin.shim.Unimplemented;

public interface IItemPropertiesExtensions {

    default <T> Item.Properties component(Supplier<? extends DataComponentType<T>> componentType, T value) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemPropertiesExtensions.component:(Ljava/util/function/Supplier;Ljava/lang/Object;)Lnet/minecraft/world/item/Item$Properties;");
    }
}
