package net.minecraft.data;

import java.nio.file.Path;
import dev.pumpkin.shim.Unimplemented;

public class PackOutput {

    public PackOutput(Path outputFolder) {
        throw Unimplemented.forMember("net/minecraft/data/PackOutput.<init>:(Ljava/nio/file/Path;)V");
    }

    public Path getOutputFolder() {
        throw Unimplemented.forMember("net/minecraft/data/PackOutput.getOutputFolder:()Ljava/nio/file/Path;");
    }

    public Path getOutputFolder(PackOutput.Target target) {
        throw Unimplemented.forMember("net/minecraft/data/PackOutput.getOutputFolder:(Lnet/minecraft/data/PackOutput$Target;)Ljava/nio/file/Path;");
    }

    public static class PathProvider {

        private PathProvider(PackOutput output, PackOutput.Target target, String kind) {
            throw Unimplemented.forMember("net/minecraft/data/PackOutput$PathProvider.<init>:(Lnet/minecraft/data/PackOutput;Lnet/minecraft/data/PackOutput$Target;Ljava/lang/String;)V");
        }

        protected PathProvider() {
        }
    }

    public enum Target {

        DATA_PACK, RESOURCE_PACK, REPORTS
    }

    protected PackOutput() {
    }
}
