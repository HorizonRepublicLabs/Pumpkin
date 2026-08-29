package net.neoforged.neoforge.registries.datamaps;

import dev.pumpkin.shim.Unimplemented;

public interface IWithData<R> {

    default <T> T getData(DataMapType<R, T> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/IWithData.getData:(Lnet/neoforged/neoforge/registries/datamaps/DataMapType;)Ljava/lang/Object;");
    }
}
