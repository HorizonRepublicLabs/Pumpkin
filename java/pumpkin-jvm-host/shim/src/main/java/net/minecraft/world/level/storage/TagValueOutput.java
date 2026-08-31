package net.minecraft.world.level.storage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.DataResult.Error;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.ProblemReporter;
import dev.pumpkin.shim.Unimplemented;

public class TagValueOutput implements ValueOutput {

    private TagValueOutput(ProblemReporter problemReporter, DynamicOps<Tag> ops, CompoundTag output) {
    }

    public static TagValueOutput createWithContext(ProblemReporter problemReporter, HolderLookup.Provider provider) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.createWithContext:(Lnet/minecraft/util/ProblemReporter;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/level/storage/TagValueOutput;");
    }

    public <T> void store(String name, Codec<T> codec, T value) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.store:(Ljava/lang/String;Lcom/mojang/serialization/Codec;Ljava/lang/Object;)V");
    }

    public <T> void storeNullable(String name, Codec<T> codec, T value) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.storeNullable:(Ljava/lang/String;Lcom/mojang/serialization/Codec;Ljava/lang/Object;)V");
    }

    public <T> void store(MapCodec<T> codec, T value) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.store:(Lcom/mojang/serialization/MapCodec;Ljava/lang/Object;)V");
    }

    public void store(CompoundTag tag) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.store:(Lnet/minecraft/nbt/CompoundTag;)V");
    }

    public void putBoolean(String name, boolean value) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.putBoolean:(Ljava/lang/String;Z)V");
    }

    public void putByte(String name, byte value) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.putByte:(Ljava/lang/String;B)V");
    }

    public void putShort(String name, short value) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.putShort:(Ljava/lang/String;S)V");
    }

    public void putInt(String name, int value) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.putInt:(Ljava/lang/String;I)V");
    }

    public void putLong(String name, long value) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.putLong:(Ljava/lang/String;J)V");
    }

    public void putFloat(String name, float value) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.putFloat:(Ljava/lang/String;F)V");
    }

    public void putDouble(String name, double value) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.putDouble:(Ljava/lang/String;D)V");
    }

    public void putString(String name, String value) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.putString:(Ljava/lang/String;Ljava/lang/String;)V");
    }

    public void putIntArray(String name, int[] value) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.putIntArray:(Ljava/lang/String;[I)V");
    }

    public ValueOutput child(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.child:(Ljava/lang/String;)Lnet/minecraft/world/level/storage/ValueOutput;");
    }

    public ValueOutput.ValueOutputList childrenList(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.childrenList:(Ljava/lang/String;)Lnet/minecraft/world/level/storage/ValueOutput$ValueOutputList;");
    }

    public <T> ValueOutput.TypedOutputList<T> list(String name, Codec<T> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.list:(Ljava/lang/String;Lcom/mojang/serialization/Codec;)Lnet/minecraft/world/level/storage/ValueOutput$TypedOutputList;");
    }

    public void discard(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.discard:(Ljava/lang/String;)V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.isEmpty:()Z");
    }

    public CompoundTag buildResult() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput.buildResult:()Lnet/minecraft/nbt/CompoundTag;");
    }

    public record EncodeToFieldFailedProblem(String name, Object value, Error<?> error) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput$EncodeToFieldFailedProblem.description:()Ljava/lang/String;");
        }
    }

    public record EncodeToListFailedProblem(String name, Object value, Error<?> error) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput$EncodeToListFailedProblem.description:()Ljava/lang/String;");
        }
    }

    public record EncodeToMapFailedProblem(Object value, Error<?> error) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput$EncodeToMapFailedProblem.description:()Ljava/lang/String;");
        }
    }

    private static class ListWrapper implements ValueOutput.ValueOutputList {

        private ListWrapper(String fieldName, ProblemReporter problemReporter, DynamicOps<Tag> ops, ListTag output) {
        }

        public ValueOutput addChild() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput$ListWrapper.addChild:()Lnet/minecraft/world/level/storage/ValueOutput;");
        }

        public void discardLast() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput$ListWrapper.discardLast:()V");
        }

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput$ListWrapper.isEmpty:()Z");
        }

        protected ListWrapper() {
        }
    }

    private static class TypedListWrapper<T> implements ValueOutput.TypedOutputList<T> {

        private TypedListWrapper(ProblemReporter problemReporter, String name, DynamicOps<Tag> ops, Codec<T> codec, ListTag output) {
        }

        public void add(T value) {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput$TypedListWrapper.add:(Ljava/lang/Object;)V");
        }

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueOutput$TypedListWrapper.isEmpty:()Z");
        }

        protected TypedListWrapper() {
        }
    }

    public TagValueOutput() {
    }
}
