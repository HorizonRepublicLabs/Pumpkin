package net.minecraft.world.level.chunk;

import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public interface Configuration {

    boolean alwaysRepack();

    int bitsInMemory();

    int bitsInStorage();

    <T> Palette<T> createPalette(Strategy<T> strategy, List<T> paletteEntries);

    record Global(int bitsInMemory, int bitsInStorage) implements Configuration {

        public boolean alwaysRepack() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/Configuration$Global.alwaysRepack:()Z");
        }

        public <T> Palette<T> createPalette(Strategy<T> strategy, List<T> paletteEntries) {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/Configuration$Global.createPalette:(Lnet/minecraft/world/level/chunk/Strategy;Ljava/util/List;)Lnet/minecraft/world/level/chunk/Palette;");
        }
    }

    record Simple(Palette.Factory factory, int bits) implements Configuration {

        public boolean alwaysRepack() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/Configuration$Simple.alwaysRepack:()Z");
        }

        public <T> Palette<T> createPalette(Strategy<T> strategy, List<T> paletteEntries) {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/Configuration$Simple.createPalette:(Lnet/minecraft/world/level/chunk/Strategy;Ljava/util/List;)Lnet/minecraft/world/level/chunk/Palette;");
        }

        public int bitsInMemory() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/Configuration$Simple.bitsInMemory:()I");
        }

        public int bitsInStorage() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/Configuration$Simple.bitsInStorage:()I");
        }
    }
}
