package net.minecraft.world.phys;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import dev.pumpkin.shim.Unimplemented;

public class BlockHitResult extends HitResult {

    public BlockHitResult(Vec3 location, Direction direction, BlockPos pos, boolean inside) {
        throw Unimplemented.forMember("net/minecraft/world/phys/BlockHitResult.<init>:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;Z)V");
    }

    public BlockHitResult(Vec3 location, Direction direction, BlockPos pos, boolean inside, boolean worldBorderHit) {
        throw Unimplemented.forMember("net/minecraft/world/phys/BlockHitResult.<init>:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;ZZ)V");
    }

    private BlockHitResult(boolean miss, Vec3 location, Direction direction, BlockPos blockPos, boolean inside, boolean worldBorderHit) {
        throw Unimplemented.forMember("net/minecraft/world/phys/BlockHitResult.<init>:(ZLnet/minecraft/world/phys/Vec3;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;ZZ)V");
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

    public BlockHitResult() {
    }
}
