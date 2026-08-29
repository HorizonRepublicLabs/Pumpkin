package net.minecraft.data;

import java.io.IOException;
import java.nio.file.Path;
import net.minecraft.WorldVersion;
import dev.pumpkin.shim.Unimplemented;

public abstract class DataGenerator {

    public DataGenerator(Path output) {
    }

    public abstract void run() throws IOException;

    public PackOutput getPackOutput() {
        throw Unimplemented.forMember("net/minecraft/data/DataGenerator.getPackOutput:()Lnet/minecraft/data/PackOutput;");
    }

    public PackOutput getPackOutput(String path) {
        throw Unimplemented.forMember("net/minecraft/data/DataGenerator.getPackOutput:(Ljava/lang/String;)Lnet/minecraft/data/PackOutput;");
    }

    public static class Cached extends DataGenerator {

        public Cached(Path output, WorldVersion version, boolean alwaysGenerate) {
        }

        public void run() throws IOException {
            throw Unimplemented.forMember("net/minecraft/data/DataGenerator$Cached.run:()V");
        }

        public Cached() {
        }
    }

    public class PackGenerator {

        private PackGenerator(boolean toRun, String providerPrefix, PackOutput output) {
        }

        public PackGenerator() {
        }
    }

    public static class Uncached extends DataGenerator {

        public Uncached(Path output) {
        }

        public void run() throws IOException {
            throw Unimplemented.forMember("net/minecraft/data/DataGenerator$Uncached.run:()V");
        }

        public Uncached() {
        }
    }

    public DataGenerator() {
    }
}
