package net.minecraft.world.attribute;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public interface EnvironmentAttributeReader {

    <Value> Value getDimensionValue(EnvironmentAttribute<Value> attribute);

    default <Value> Value getValue(EnvironmentAttribute<Value> attribute, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeReader.getValue:(Lnet/minecraft/world/attribute/EnvironmentAttribute;Lnet/minecraft/core/BlockPos;)Ljava/lang/Object;");
    }

    default <Value> Value getValue(EnvironmentAttribute<Value> attribute, Vec3 pos) {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeReader.getValue:(Lnet/minecraft/world/attribute/EnvironmentAttribute;Lnet/minecraft/world/phys/Vec3;)Ljava/lang/Object;");
    }

    <Value> Value getValue(EnvironmentAttribute<Value> attribute, Vec3 pos, SpatialAttributeInterpolator biomeInterpolator);

    default <Value> Value getValue(LootContext context, EnvironmentAttribute<Value> attribute) {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeReader.getValue:(Lnet/minecraft/world/level/storage/loot/LootContext;Lnet/minecraft/world/attribute/EnvironmentAttribute;)Ljava/lang/Object;");
    }
}
