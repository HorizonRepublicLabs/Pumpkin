package net.minecraft.world.level.chunk.storage;

import com.mojang.datafixers.DataFixer;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.ChunkPos;
import dev.pumpkin.shim.Unimplemented;

public class SimpleRegionStorage implements AutoCloseable {

    public SimpleRegionStorage(RegionStorageInfo info, Path folder, DataFixer fixerUpper, boolean syncWrites, DataFixTypes dataFixType) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/storage/SimpleRegionStorage.<init>:(Lnet/minecraft/world/level/chunk/storage/RegionStorageInfo;Ljava/nio/file/Path;Lcom/mojang/datafixers/DataFixer;ZLnet/minecraft/util/datafix/DataFixTypes;)V");
    }

    public CompletableFuture<Optional<CompoundTag>> read(ChunkPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/storage/SimpleRegionStorage.read:(Lnet/minecraft/world/level/ChunkPos;)Ljava/util/concurrent/CompletableFuture;");
    }

    public CompletableFuture<Void> write(ChunkPos pos, CompoundTag value) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/storage/SimpleRegionStorage.write:(Lnet/minecraft/world/level/ChunkPos;Lnet/minecraft/nbt/CompoundTag;)Ljava/util/concurrent/CompletableFuture;");
    }

    public CompletableFuture<Void> write(ChunkPos pos, Supplier<CompoundTag> supplier) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/storage/SimpleRegionStorage.write:(Lnet/minecraft/world/level/ChunkPos;Ljava/util/function/Supplier;)Ljava/util/concurrent/CompletableFuture;");
    }

    public void close() throws IOException {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/storage/SimpleRegionStorage.close:()V");
    }

    protected SimpleRegionStorage() {
    }
}
