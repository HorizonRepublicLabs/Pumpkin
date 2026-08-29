package net.minecraft.world.level.storage;

import com.mojang.datafixers.DataFixer;
import java.util.Optional;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.players.NameAndId;
import dev.pumpkin.shim.Unimplemented;

public class PlayerDataStorage {

    public PlayerDataStorage(LevelStorageSource.LevelStorageAccess levelAccess, DataFixer fixerUpper) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PlayerDataStorage.<init>:(Lnet/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess;Lcom/mojang/datafixers/DataFixer;)V");
    }

    private Optional<CompoundTag> load(NameAndId nameAndId, String suffix) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PlayerDataStorage.load:(Lnet/minecraft/server/players/NameAndId;Ljava/lang/String;)Ljava/util/Optional;");
    }

    public Optional<CompoundTag> load(NameAndId nameAndId) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PlayerDataStorage.load:(Lnet/minecraft/server/players/NameAndId;)Ljava/util/Optional;");
    }

    public PlayerDataStorage() {
    }
}
