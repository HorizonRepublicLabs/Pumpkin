package net.minecraft.core.component.predicates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import java.util.Map;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public interface DataComponentPredicate {

    // Pumpkin divergence: a throwing codec, not null -- DFU composes through it
    // at class-init; it throws by name on first real use.
    Codec<Map<DataComponentPredicate.Type<?>, DataComponentPredicate>> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/component/predicates/DataComponentPredicate.CODEC");

    boolean matches(DataComponentGetter components);

    final class AnyValueType extends DataComponentPredicate.TypeBase<AnyValue> {

        public AnyValueType(AnyValue predicate) {
        }

        public DataComponentType<?> componentType() {
            throw Unimplemented.forMember("net/minecraft/core/component/predicates/DataComponentPredicate$AnyValueType.componentType:()Lnet/minecraft/core/component/DataComponentType;");
        }

        public static DataComponentPredicate.AnyValueType create(DataComponentType<?> componentType) {
            throw Unimplemented.forMember("net/minecraft/core/component/predicates/DataComponentPredicate$AnyValueType.create:(Lnet/minecraft/core/component/DataComponentType;)Lnet/minecraft/core/component/predicates/DataComponentPredicate$AnyValueType;");
        }

        protected AnyValueType() {
        }
    }

    final class ConcreteType<T extends DataComponentPredicate> extends DataComponentPredicate.TypeBase<T> {

        public ConcreteType(Codec<T> codec) {
        }

        protected ConcreteType() {
        }
    }

    record Single<T extends DataComponentPredicate>(DataComponentPredicate.Type<T> type, T predicate) {
    }

    interface Type<T extends DataComponentPredicate> {

        Codec<T> codec();

        MapCodec<DataComponentPredicate.Single<T>> wrappedCodec();

        StreamCodec<RegistryFriendlyByteBuf, DataComponentPredicate.Single<T>> singleStreamCodec();
    }

    abstract class TypeBase<T extends DataComponentPredicate> implements DataComponentPredicate.Type<T> {

        public TypeBase(Codec<T> codec) {
        }

        public Codec<T> codec() {
            throw Unimplemented.forMember("net/minecraft/core/component/predicates/DataComponentPredicate$TypeBase.codec:()Lcom/mojang/serialization/Codec;");
        }

        public MapCodec<DataComponentPredicate.Single<T>> wrappedCodec() {
            throw Unimplemented.forMember("net/minecraft/core/component/predicates/DataComponentPredicate$TypeBase.wrappedCodec:()Lcom/mojang/serialization/MapCodec;");
        }

        public StreamCodec<RegistryFriendlyByteBuf, DataComponentPredicate.Single<T>> singleStreamCodec() {
            throw Unimplemented.forMember("net/minecraft/core/component/predicates/DataComponentPredicate$TypeBase.singleStreamCodec:()Lnet/minecraft/network/codec/StreamCodec;");
        }

        protected TypeBase() {
        }
    }
}
