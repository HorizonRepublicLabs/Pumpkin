package net.neoforged.neoforge.registries.datamaps;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import dev.pumpkin.shim.Unimplemented;

public interface DataMapValueRemover<R, T> {

    Optional<T> remove(T value, Registry<R> registry, Either<TagKey<R>, ResourceKey<R>> source, R object);

    class Default<T, R> implements DataMapValueRemover<R, T> {

        public static <T, R> Codec<Default<T, R>> codec() {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapValueRemover$Default.codec:()Lcom/mojang/serialization/Codec;");
        }

        protected Default() {
        }

        public Optional<T> remove(T value, Registry<R> registry, Either<TagKey<R>, ResourceKey<R>> source, R object) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapValueRemover$Default.remove:(Ljava/lang/Object;Lnet/minecraft/core/Registry;Lcom/mojang/datafixers/util/Either;Ljava/lang/Object;)Ljava/util/Optional;");
        }
    }
}
