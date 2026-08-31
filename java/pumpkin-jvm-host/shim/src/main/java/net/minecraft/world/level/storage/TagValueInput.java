package net.minecraft.world.level.storage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.DataResult.Error;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagType;
import net.minecraft.util.ProblemReporter;
import dev.pumpkin.shim.Unimplemented;

public class TagValueInput implements ValueInput {

    private TagValueInput(ProblemReporter problemReporter, ValueInputContextHelper context, CompoundTag input) {
    }

    public static ValueInput create(ProblemReporter problemReporter, HolderLookup.Provider holders, CompoundTag tag) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.create:(Lnet/minecraft/util/ProblemReporter;Lnet/minecraft/core/HolderLookup$Provider;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/world/level/storage/ValueInput;");
    }

    public static ValueInput.ValueInputList create(ProblemReporter problemReporter, HolderLookup.Provider holders, List<CompoundTag> tags) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.create:(Lnet/minecraft/util/ProblemReporter;Lnet/minecraft/core/HolderLookup$Provider;Ljava/util/List;)Lnet/minecraft/world/level/storage/ValueInput$ValueInputList;");
    }

    public <T> Optional<T> read(String name, Codec<T> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.read:(Ljava/lang/String;Lcom/mojang/serialization/Codec;)Ljava/util/Optional;");
    }

    public <T> Optional<T> read(MapCodec<T> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.read:(Lcom/mojang/serialization/MapCodec;)Ljava/util/Optional;");
    }

    public Optional<ValueInput> child(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.child:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public ValueInput childOrEmpty(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.childOrEmpty:(Ljava/lang/String;)Lnet/minecraft/world/level/storage/ValueInput;");
    }

    public ValueInput rawChildOrEmpty(String key) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.rawChildOrEmpty:(Ljava/lang/String;)Lnet/minecraft/world/level/storage/ValueInput;");
    }

    public Optional<ValueInput.ValueInputList> childrenList(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.childrenList:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public ValueInput.ValueInputList childrenListOrEmpty(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.childrenListOrEmpty:(Ljava/lang/String;)Lnet/minecraft/world/level/storage/ValueInput$ValueInputList;");
    }

    public <T> Optional<ValueInput.TypedInputList<T>> list(String name, Codec<T> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.list:(Ljava/lang/String;Lcom/mojang/serialization/Codec;)Ljava/util/Optional;");
    }

    public <T> ValueInput.TypedInputList<T> listOrEmpty(String name, Codec<T> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.listOrEmpty:(Ljava/lang/String;Lcom/mojang/serialization/Codec;)Lnet/minecraft/world/level/storage/ValueInput$TypedInputList;");
    }

    public boolean getBooleanOr(String name, boolean defaultValue) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.getBooleanOr:(Ljava/lang/String;Z)Z");
    }

    public byte getByteOr(String name, byte defaultValue) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.getByteOr:(Ljava/lang/String;B)B");
    }

    public int getShortOr(String name, short defaultValue) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.getShortOr:(Ljava/lang/String;S)I");
    }

    public Optional<Integer> getInt(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.getInt:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public int getIntOr(String name, int defaultValue) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.getIntOr:(Ljava/lang/String;I)I");
    }

    public long getLongOr(String name, long defaultValue) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.getLongOr:(Ljava/lang/String;J)J");
    }

    public Optional<Long> getLong(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.getLong:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public float getFloatOr(String name, float defaultValue) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.getFloatOr:(Ljava/lang/String;F)F");
    }

    public double getDoubleOr(String name, double defaultValue) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.getDoubleOr:(Ljava/lang/String;D)D");
    }

    public Optional<String> getString(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.getString:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public String getStringOr(String name, String defaultValue) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.getStringOr:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
    }

    public Optional<int[]> getIntArray(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.getIntArray:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public HolderLookup.Provider lookup() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.lookup:()Lnet/minecraft/core/HolderLookup$Provider;");
    }

    public java.util.Set<String> keySet() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput.keySet:()Ljava/util/Set;");
    }

    private static class CompoundListWrapper implements ValueInput.ValueInputList {

        public CompoundListWrapper(ProblemReporter problemReporter, ValueInputContextHelper context, List<CompoundTag> list) {
        }

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$CompoundListWrapper.isEmpty:()Z");
        }

        public Stream<ValueInput> stream() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$CompoundListWrapper.stream:()Ljava/util/stream/Stream;");
        }

        public Iterator<ValueInput> iterator() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$CompoundListWrapper.iterator:()Ljava/util/Iterator;");
        }

        protected CompoundListWrapper() {
        }
    }

    public record DecodeFromFieldFailedProblem(String name, Tag tag, Error<?> error) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$DecodeFromFieldFailedProblem.description:()Ljava/lang/String;");
        }
    }

    public record DecodeFromListFailedProblem(String name, int index, Tag tag, Error<?> error) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$DecodeFromListFailedProblem.description:()Ljava/lang/String;");
        }
    }

    public record DecodeFromMapFailedProblem(Error<?> error) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$DecodeFromMapFailedProblem.description:()Ljava/lang/String;");
        }
    }

    private static class ListWrapper implements ValueInput.ValueInputList {

        private ListWrapper(ProblemReporter problemReporter, String name, ValueInputContextHelper context, ListTag list) {
        }

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$ListWrapper.isEmpty:()Z");
        }

        public Stream<ValueInput> stream() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$ListWrapper.stream:()Ljava/util/stream/Stream;");
        }

        public Iterator<ValueInput> iterator() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$ListWrapper.iterator:()Ljava/util/Iterator;");
        }

        protected ListWrapper() {
        }
    }

    private static class TypedListWrapper<T> implements ValueInput.TypedInputList<T> {

        private TypedListWrapper(ProblemReporter problemReporter, String name, ValueInputContextHelper context, Codec<T> codec, ListTag list) {
        }

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$TypedListWrapper.isEmpty:()Z");
        }

        public Stream<T> stream() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$TypedListWrapper.stream:()Ljava/util/stream/Stream;");
        }

        public Iterator<T> iterator() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$TypedListWrapper.iterator:()Ljava/util/Iterator;");
        }

        protected TypedListWrapper() {
        }
    }

    public record UnexpectedListElementTypeProblem(String name, int index, TagType<?> expected, TagType<?> actual) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$UnexpectedListElementTypeProblem.description:()Ljava/lang/String;");
        }
    }

    public record UnexpectedNonNumberProblem(String name, TagType<?> actual) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$UnexpectedNonNumberProblem.description:()Ljava/lang/String;");
        }
    }

    public record UnexpectedTypeProblem(String name, TagType<?> expected, TagType<?> actual) implements ProblemReporter.Problem {

        public String description() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/TagValueInput$UnexpectedTypeProblem.description:()Ljava/lang/String;");
        }
    }

    public TagValueInput() {
    }
}
