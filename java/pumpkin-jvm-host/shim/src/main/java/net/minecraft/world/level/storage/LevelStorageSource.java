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
        }

        public LevelStorageSource parent() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess.parent:()Lnet/minecraft/world/level/storage/LevelStorageSource;");
        }

        public void close() throws IOException {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess.close:()V");
        }

        public LevelStorageAccess() {
        }
    }

    public LevelStorageSource() {
    }
}
