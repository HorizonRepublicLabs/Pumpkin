package net.minecraft.network;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.TypedDataComponent;
import dev.pumpkin.shim.Unimplemented;

public record HashedPatchMap(Map<DataComponentType<?>, Integer> addedComponents, Set<DataComponentType<?>> removedComponents) {

    public static HashedPatchMap create(DataComponentPatch patch, HashedPatchMap.HashGenerator hasher) {
        throw Unimplemented.forMember("net/minecraft/network/HashedPatchMap.create:(Lnet/minecraft/core/component/DataComponentPatch;Lnet/minecraft/network/HashedPatchMap$HashGenerator;)Lnet/minecraft/network/HashedPatchMap;");
    }

    public boolean matches(DataComponentPatch patch, HashedPatchMap.HashGenerator hasher) {
        throw Unimplemented.forMember("net/minecraft/network/HashedPatchMap.matches:(Lnet/minecraft/core/component/DataComponentPatch;Lnet/minecraft/network/HashedPatchMap$HashGenerator;)Z");
    }

    public interface HashGenerator extends Function<TypedDataComponent<?>, Integer> {
    }
}
