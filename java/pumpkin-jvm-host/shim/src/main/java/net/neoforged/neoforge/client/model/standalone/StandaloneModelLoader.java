package net.neoforged.neoforge.client.model.standalone;

import java.util.Map;
import dev.pumpkin.shim.Unimplemented;

public final class StandaloneModelLoader {

    public record LoadedModels(Map<StandaloneModelKey<?>, UnbakedStandaloneModel<?>> models) {
    }

    public record BakedModels(Map<StandaloneModelKey<?>, ?> models) {

        public <T> T get(StandaloneModelKey<T> key) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/standalone/StandaloneModelLoader$BakedModels.get:(Lnet/neoforged/neoforge/client/model/standalone/StandaloneModelKey;)Ljava/lang/Object;");
        }
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/standalone/StandaloneModelLoader");
        }
    }
}
