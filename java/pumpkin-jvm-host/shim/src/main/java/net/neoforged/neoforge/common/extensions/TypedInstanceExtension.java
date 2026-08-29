package net.neoforged.neoforge.common.extensions;

import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.IWithData;
import dev.pumpkin.shim.Unimplemented;

public interface TypedInstanceExtension<T> extends IWithData<T> {

    default <D> D getData(DataMapType<T, D> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/TypedInstanceExtension.getData:(Lnet/neoforged/neoforge/registries/datamaps/DataMapType;)Ljava/lang/Object;");
    }
}
