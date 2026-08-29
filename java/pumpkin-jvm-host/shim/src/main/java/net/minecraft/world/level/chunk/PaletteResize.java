package net.minecraft.world.level.chunk;

public interface PaletteResize<T> {

    int onResize(int bits, T lastAddedValue);
}
