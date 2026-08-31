package net.minecraft.world.item.enchantment;

import java.util.function.UnaryOperator;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.util.Unit;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public interface EnchantmentEffectComponents {

    DataComponentType<Unit> PREVENT_ARMOR_CHANGE = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builder) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/EnchantmentEffectComponents.register:(Ljava/lang/String;Ljava/util/function/UnaryOperator;)Lnet/minecraft/core/component/DataComponentType;");
    }
}
