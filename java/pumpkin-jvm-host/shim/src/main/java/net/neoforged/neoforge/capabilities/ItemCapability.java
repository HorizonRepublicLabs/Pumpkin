package net.neoforged.neoforge.capabilities;

import java.util.List;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public final class ItemCapability<T, C extends Object> extends BaseCapability<T, C> {

    public static <T, C extends Object> ItemCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/ItemCapability.create:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/ItemCapability;");
    }

    public static synchronized List<ItemCapability<?, ?>> getAll() {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/ItemCapability.getAll:()Ljava/util/List;");
    }

    private ItemCapability(Identifier name, Class<T> typeClass, Class<C> contextClass) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/ItemCapability.<init>:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;Ljava/lang/Class;)V");
    }

    public T getCapability(ItemStack stack, C context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/ItemCapability.getCapability:(Lnet/minecraft/world/item/ItemStack;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    protected ItemCapability() {
    }
}
