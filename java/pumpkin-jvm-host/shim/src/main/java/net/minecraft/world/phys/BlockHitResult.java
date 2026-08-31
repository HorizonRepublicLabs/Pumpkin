package net.minecraft.world.phys;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import dev.pumpkin.shim.Unimplemented;

public class BlockHitResult extends HitResult {

    private final boolean miss = false;

    public static BlockHitResult miss(Vec3 location, Direction direction, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/BlockHitResult.miss:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/BlockHitResult;");
    }

    public BlockHitResult(Vec3 location, Direction direction, BlockPos pos, boolean inside) {
    }

    public BlockHitResult(Vec3 location, Direction direction, BlockPos pos, boolean inside, boolean worldBorderHit) {
    }

    private BlockHitResult(boolean miss, Vec3 location, Direction direction, BlockPos blockPos, boolean inside, boolean worldBorderHit) {
    }

    public BlockHitResult withPosition(BlockPos blockPos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/BlockHitResult.withPosition:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/BlockHitResult;");
    }

    public BlockPos getBlockPos() {
        throw Unimplemented.forMember("net/minecraft/world/phys/BlockHitResult.getBlockPos:()Lnet/minecraft/core/BlockPos;");
    }

    public Direction getDirection() {
        throw Unimplemented.forMember("net/minecraft/world/phys/BlockHitResult.getDirection:()Lnet/minecraft/core/Direction;");
    }

    public HitResult.Type getType() {
        throw Unimplemented.forMember("net/minecraft/world/phys/BlockHitResult.getType:()Lnet/minecraft/world/phys/HitResult$Type;");
    }

    public boolean isInside() {
        throw Unimplemented.forMember("net/minecraft/world/phys/BlockHitResult.isInside:()Z");
    }

    public BlockHitResult() {
    }
}
