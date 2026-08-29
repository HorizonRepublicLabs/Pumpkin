package net.neoforged.neoforge.transfer.resource;

import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.core.TypedInstance;
import dev.pumpkin.shim.Unimplemented;

public interface RegisteredResource<T> extends Resource, TypedInstance<T> {

    T value();

    default boolean is(Predicate<Holder<T>> predicate) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/resource/RegisteredResource.is:(Ljava/util/function/Predicate;)Z");
    }
}
