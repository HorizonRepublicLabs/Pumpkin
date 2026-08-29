package net.neoforged.neoforge.client.model.standalone;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.client.resources.model.ModelBaker;
import dev.pumpkin.shim.Unimplemented;

public final class StandaloneModelLoader {

    protected StandaloneModelLoader() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/standalone/StandaloneModelLoader.<init>:()V");
    }

    public static CompletableFuture<LoadedModels> load(Executor executor) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/standalone/StandaloneModelLoader.load:(Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;");
    }

    public static CompletableFuture<BakedModels> bake(LoadedModels standaloneModels, ModelBaker baker, Executor executor) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/standalone/StandaloneModelLoader.bake:(Lnet/neoforged/neoforge/client/model/standalone/StandaloneModelLoader$LoadedModels;Lnet/minecraft/client/resources/model/ModelBaker;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;");
    }

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
