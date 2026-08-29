package net.minecraft.core.component;

import java.util.function.UnaryOperator;
import net.minecraft.util.Unit;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class DataComponents {

    public static final DataComponentType<CustomData> CUSTOM_DATA = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Integer> DAMAGE = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Unit> UNBREAKABLE = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<ItemEnchantments> ENCHANTMENTS = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Tool> TOOL = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builder) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponents.register:(Ljava/lang/String;Ljava/util/function/UnaryOperator;)Lnet/minecraft/core/component/DataComponentType;");
    }

    public DataComponents() {
    }
}
