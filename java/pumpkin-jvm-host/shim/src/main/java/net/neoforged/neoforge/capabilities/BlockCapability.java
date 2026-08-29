package net.neoforged.neoforge.capabilities;

import java.util.List;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public final class BlockCapability<T, C extends Object> extends BaseCapability<T, C> {

    public static <T, C extends Object> BlockCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapability.create:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/BlockCapability;");
    }

    public static synchronized List<BlockCapability<?, ?>> getAll() {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapability.getAll:()Ljava/util/List;");
    }

    private BlockCapability(Identifier name, Class<T> typeClass, Class<C> contextClass) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapability.<init>:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;Ljava/lang/Class;)V");
    }

    public BlockCapability() {
    }
}
