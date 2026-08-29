package net.minecraft.world.level.chunk;

import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraft.core.IdMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.BitStorage;
import dev.pumpkin.shim.Unimplemented;

public class PalettedContainer<T> implements PaletteResize<T>, PalettedContainerRO<T> {

    public void release() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.release:()V");
    }

    private PalettedContainer(Strategy<T> strategy, Configuration dataConfiguration, BitStorage storage, Palette<T> palette) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.<init>:(Lnet/minecraft/world/level/chunk/Strategy;Lnet/minecraft/world/level/chunk/Configuration;Lnet/minecraft/util/BitStorage;Lnet/minecraft/world/level/chunk/Palette;)V");
    }

    private PalettedContainer(PalettedContainer<T> source) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.<init>:(Lnet/minecraft/world/level/chunk/PalettedContainer;)V");
    }

    public PalettedContainer(T initialValue, Strategy<T> strategy) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.<init>:(Ljava/lang/Object;Lnet/minecraft/world/level/chunk/Strategy;)V");
    }

    public int onResize(int bits, T lastAddedValue) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.onResize:(ILjava/lang/Object;)I");
    }

    private T getAndSet(int index, T value) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.getAndSet:(ILjava/lang/Object;)Ljava/lang/Object;");
    }

    private void set(int index, T value) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.set:(ILjava/lang/Object;)V");
    }

    public T get(int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.get:(III)Ljava/lang/Object;");
    }

    protected T get(int index) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.get:(I)Ljava/lang/Object;");
    }

    public void getAll(Consumer<T> consumer) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.getAll:(Ljava/util/function/Consumer;)V");
    }

    public void read(FriendlyByteBuf buffer) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.read:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public void write(FriendlyByteBuf buffer) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PalettedContainerRO.PackedData<T> pack(Strategy<T> strategy) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.pack:(Lnet/minecraft/world/level/chunk/Strategy;)Lnet/minecraft/world/level/chunk/PalettedContainerRO$PackedData;");
    }

    public int getSerializedSize() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.getSerializedSize:()I");
    }

    public int bitsPerEntry() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.bitsPerEntry:()I");
    }

    public boolean maybeHas(Predicate<T> predicate) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.maybeHas:(Ljava/util/function/Predicate;)Z");
    }

    public void forEachInPalette(Consumer<T> consumer) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.forEachInPalette:(Ljava/util/function/Consumer;)V");
    }

    public PalettedContainer<T> copy() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.copy:()Lnet/minecraft/world/level/chunk/PalettedContainer;");
    }

    public PalettedContainer<T> recreate() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.recreate:()Lnet/minecraft/world/level/chunk/PalettedContainer;");
    }

    public void count(PalettedContainer.CountConsumer<T> output) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer.count:(Lnet/minecraft/world/level/chunk/PalettedContainer$CountConsumer;)V");
    }

    public interface CountConsumer<T> {

        void accept(final T entry, final int count);
    }

    private record Data<T>(Configuration configuration, BitStorage storage, Palette<T> palette) {

        public int getSerializedSize(IdMap<T> globalMap) {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer$Data.getSerializedSize:(Lnet/minecraft/core/IdMap;)I");
        }

        public void write(FriendlyByteBuf buffer, IdMap<T> globalMap) {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer$Data.write:(Lnet/minecraft/network/FriendlyByteBuf;Lnet/minecraft/core/IdMap;)V");
        }

        public PalettedContainer.Data<T> copy() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainer$Data.copy:()Lnet/minecraft/world/level/chunk/PalettedContainer$Data;");
        }
    }

    protected PalettedContainer() {
    }
}
