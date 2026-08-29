package net.minecraft.core.component;

import java.util.function.UnaryOperator;
import net.minecraft.util.Unit;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import dev.pumpkin.shim.Unimplemented;

public class DataComponents {

    public static final DataComponentType<CustomData> CUSTOM_DATA = null;

    public static final DataComponentType<Integer> DAMAGE = null;

    public static final DataComponentType<Unit> UNBREAKABLE = null;

    public static final DataComponentType<ItemEnchantments> ENCHANTMENTS = null;

    public static final DataComponentType<Tool> TOOL = null;

    private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builder) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponents.register:(Ljava/lang/String;Ljava/util/function/UnaryOperator;)Lnet/minecraft/core/component/DataComponentType;");
    }

    protected DataComponents() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponents");
        }
    }
}
