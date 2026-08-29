package net.neoforged.neoforge.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class BlockSnapshot {

    private BlockSnapshot(ResourceKey<Level> dim, LevelAccessor level, BlockPos pos, BlockState state, CompoundTag nbt, int flags) {
    }

    public static BlockSnapshot create(ResourceKey<Level> dim, LevelAccessor level, BlockPos pos, int flag) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/BlockSnapshot.create:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;I)Lnet/neoforged/neoforge/common/util/BlockSnapshot;");
    }

    public static BlockSnapshot create(ResourceKey<Level> dim, LevelAccessor level, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/BlockSnapshot.create:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;)Lnet/neoforged/neoforge/common/util/BlockSnapshot;");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/BlockSnapshot.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public LevelAccessor getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/BlockSnapshot.getLevel:()Lnet/minecraft/world/level/LevelAccessor;");
    }

    public boolean restore(int flags) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/BlockSnapshot.restore:(I)Z");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/BlockSnapshot.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/BlockSnapshot.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/BlockSnapshot.toString:()Ljava/lang/String;");
    }

    public BlockSnapshot() {
    }
}
