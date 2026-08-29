package net.minecraft.world.level.storage;

import com.mojang.datafixers.DataFixer;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import net.minecraft.world.level.validation.DirectoryValidator;
import dev.pumpkin.shim.Unimplemented;

public class LevelStorageSource {

    public LevelStorageSource(Path baseDir, Path backupDir, DirectoryValidator worldDirValidator, DataFixer fixerUpper) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/LevelStorageSource.<init>:(Ljava/nio/file/Path;Ljava/nio/file/Path;Lnet/minecraft/world/level/validation/DirectoryValidator;Lcom/mojang/datafixers/DataFixer;)V");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/LevelStorageSource.getName:()Ljava/lang/String;");
    }

    public record LevelCandidates(List<LevelStorageSource.LevelDirectory> levels) implements Iterable<LevelStorageSource.LevelDirectory> {

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/LevelStorageSource$LevelCandidates.isEmpty:()Z");
        }

        public Iterator<LevelStorageSource.LevelDirectory> iterator() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/LevelStorageSource$LevelCandidates.iterator:()Ljava/util/Iterator;");
        }
    }

    public record LevelDirectory(Path path) {
    }

    public class LevelStorageAccess implements AutoCloseable {

        private LevelStorageAccess(String levelId, Path path) throws IOException {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess.<init>:(Ljava/lang/String;Ljava/nio/file/Path;)V");
        }

        public LevelStorageSource parent() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess.parent:()Lnet/minecraft/world/level/storage/LevelStorageSource;");
        }

        public void close() throws IOException {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess.close:()V");
        }

        protected LevelStorageAccess() {
        }
    }

    protected LevelStorageSource() {
    }
}
