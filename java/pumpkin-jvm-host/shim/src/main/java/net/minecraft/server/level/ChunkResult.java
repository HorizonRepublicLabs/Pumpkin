package net.minecraft.server.level;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import dev.pumpkin.shim.Unimplemented;

public interface ChunkResult<T> {

    boolean isSuccess();

    T orElse(T orElse);

    static <R> R orElse(ChunkResult<? extends R> chunkResult, R orElse) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult.orElse:(Lnet/minecraft/server/level/ChunkResult;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    String getError();

    ChunkResult<T> ifSuccess(Consumer<T> consumer);

    <R> ChunkResult<R> map(Function<T, R> map);

    <E extends Throwable> T orElseThrow(Supplier<E> exceptionSupplier) throws E;

    record Fail<T>(Supplier<String> error) implements ChunkResult<T> {

        public boolean isSuccess() {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult$Fail.isSuccess:()Z");
        }

        public T orElse(T orElse) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult$Fail.orElse:(Ljava/lang/Object;)Ljava/lang/Object;");
        }

        public String getError() {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult$Fail.getError:()Ljava/lang/String;");
        }

        public ChunkResult<T> ifSuccess(Consumer<T> consumer) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult$Fail.ifSuccess:(Ljava/util/function/Consumer;)Lnet/minecraft/server/level/ChunkResult;");
        }

        public <R> ChunkResult<R> map(Function<T, R> map) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult$Fail.map:(Ljava/util/function/Function;)Lnet/minecraft/server/level/ChunkResult;");
        }

        public <E extends Throwable> T orElseThrow(Supplier<E> exceptionSupplier) throws E {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult$Fail.orElseThrow:(Ljava/util/function/Supplier;)Ljava/lang/Object;");
        }
    }

    record Success<T>(T value) implements ChunkResult<T> {

        public boolean isSuccess() {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult$Success.isSuccess:()Z");
        }

        public T orElse(T orElse) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult$Success.orElse:(Ljava/lang/Object;)Ljava/lang/Object;");
        }

        public String getError() {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult$Success.getError:()Ljava/lang/String;");
        }

        public ChunkResult<T> ifSuccess(Consumer<T> consumer) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult$Success.ifSuccess:(Ljava/util/function/Consumer;)Lnet/minecraft/server/level/ChunkResult;");
        }

        public <R> ChunkResult<R> map(Function<T, R> map) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult$Success.map:(Ljava/util/function/Function;)Lnet/minecraft/server/level/ChunkResult;");
        }

        public <E extends Throwable> T orElseThrow(Supplier<E> exceptionSupplier) throws E {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkResult$Success.orElseThrow:(Ljava/util/function/Supplier;)Ljava/lang/Object;");
        }
    }
}
