package net.minecraft.world.level.storage;

import com.mojang.datafixers.DataFixer;
import java.nio.file.Path;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import dev.pumpkin.shim.Unimplemented;

public class SavedDataStorage implements AutoCloseable {

    public SavedDataStorage(Path dataFolder, DataFixer fixerUpper, HolderLookup.Provider registries) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/SavedDataStorage.<init>:(Ljava/nio/file/Path;Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/core/HolderLookup$Provider;)V");
    }

    public SavedDataStorage(net.minecraft.server.level.ServerLevel level, Path dataFolder, DataFixer fixerUpper, HolderLookup.Provider registries) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/SavedDataStorage.<init>:(Lnet/minecraft/server/level/ServerLevel;Ljava/nio/file/Path;Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/core/HolderLookup$Provider;)V");
    }

    public <T extends SavedData> T get(SavedDataType<T> type) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/SavedDataStorage.get:(Lnet/minecraft/world/level/saveddata/SavedDataType;)Lnet/minecraft/world/level/saveddata/SavedData;");
    }

    public <T extends SavedData> void set(SavedDataType<T> type, T data) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/SavedDataStorage.set:(Lnet/minecraft/world/level/saveddata/SavedDataType;Lnet/minecraft/world/level/saveddata/SavedData;)V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/SavedDataStorage.close:()V");
    }

    protected SavedDataStorage() {
    }
}
