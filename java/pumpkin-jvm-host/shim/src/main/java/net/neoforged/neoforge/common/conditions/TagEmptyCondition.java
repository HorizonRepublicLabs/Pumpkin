package net.neoforged.neoforge.common.conditions;

import com.mojang.serialization.MapCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import dev.pumpkin.shim.Unimplemented;

public record TagEmptyCondition<T>(TagKey<T> tag) implements ICondition {

    private TagEmptyCondition(Identifier registryType, Identifier tagName) {
        this((TagKey<T>) null);
        throw Unimplemented.forMember("net/neoforged/neoforge/common/conditions/TagEmptyCondition.<init>:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;)V");
    }

    public boolean test(ICondition.IContext context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/conditions/TagEmptyCondition.test:(Lnet/neoforged/neoforge/common/conditions/ICondition$IContext;)Z");
    }

    public MapCodec<? extends ICondition> codec() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/conditions/TagEmptyCondition.codec:()Lcom/mojang/serialization/MapCodec;");
    }
}
