package net.minecraft.data;

import java.nio.file.Path;
import dev.pumpkin.shim.Unimplemented;

public class PackOutput {

    public PackOutput(Path outputFolder) {
    }

    public Path getOutputFolder() {
        throw Unimplemented.forMember("net/minecraft/data/PackOutput.getOutputFolder:()Ljava/nio/file/Path;");
    }

    public Path getOutputFolder(PackOutput.Target target) {
        throw Unimplemented.forMember("net/minecraft/data/PackOutput.getOutputFolder:(Lnet/minecraft/data/PackOutput$Target;)Ljava/nio/file/Path;");
    }

    public static class PathProvider {

        private PathProvider(PackOutput output, PackOutput.Target target, String kind) {
        }

        public PathProvider() {
        }
    }

    public enum Target {

        DATA_PACK, RESOURCE_PACK, REPORTS
    }

    public PackOutput() {
    }
}
