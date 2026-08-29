package net.neoforged.neoforge.transfer.resource;

import java.util.function.Supplier;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import dev.pumpkin.shim.Unimplemented;

public interface DataComponentHolderResource<T> extends RegisteredResource<T>, DataComponentHolder {

    boolean isComponentsPatchEmpty();

    DataComponentHolderResource<T> withMergedPatch(DataComponentPatch patch);

    <D> DataComponentHolderResource<T> with(DataComponentType<D> type, D data);

    DataComponentHolderResource<T> without(DataComponentType<?> type);

    DataComponentPatch getComponentsPatch();

    default <D> DataComponentHolderResource<T> with(Supplier<? extends DataComponentType<D>> type, D data) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/resource/DataComponentHolderResource.with:(Ljava/util/function/Supplier;Ljava/lang/Object;)Lnet/neoforged/neoforge/transfer/resource/DataComponentHolderResource;");
    }

    default DataComponentHolderResource<T> without(Supplier<? extends DataComponentType<?>> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/resource/DataComponentHolderResource.without:(Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/transfer/resource/DataComponentHolderResource;");
    }
}
