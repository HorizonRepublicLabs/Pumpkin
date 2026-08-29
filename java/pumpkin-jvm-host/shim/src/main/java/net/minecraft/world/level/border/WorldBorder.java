package net.minecraft.world.level.border;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public class WorldBorder extends SavedData {

    public WorldBorder() {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.<init>:()V");
    }

    public WorldBorder(WorldBorder.Settings settings) {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.<init>:(Lnet/minecraft/world/level/border/WorldBorder$Settings;)V");
    }

    public VoxelShape getCollisionShape() {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.getCollisionShape:()Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public BorderStatus getStatus() {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.getStatus:()Lnet/minecraft/world/level/border/BorderStatus;");
    }

    public double getMinX(float deltaPartialTick) {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.getMinX:(F)D");
    }

    public double getMinZ(float deltaPartialTick) {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.getMinZ:(F)D");
    }

    public double getMaxX(float deltaPartialTick) {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.getMaxX:(F)D");
    }

    public double getMaxZ(float deltaPartialTick) {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.getMaxZ:(F)D");
    }

    public double getSize() {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.getSize:()D");
    }

    public long getLerpTime() {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.getLerpTime:()J");
    }

    public double getLerpTarget() {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.getLerpTarget:()D");
    }

    public double getLerpSpeed() {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.getLerpSpeed:()D");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder.tick:()V");
    }

    private interface BorderExtent {

        double getMinX(final float deltaPartialTick);

        double getMaxX(final float deltaPartialTick);

        double getMinZ(final float deltaPartialTick);

        double getMaxZ(final float deltaPartialTick);

        double getSize();

        double getLerpSpeed();

        long getLerpTime();

        double getLerpTarget();

        BorderStatus getStatus();

        void onAbsoluteMaxSizeChange();

        void onCenterChange();

        WorldBorder.BorderExtent update();

        VoxelShape getCollisionShape();
    }

    private class MovingBorderExtent implements WorldBorder.BorderExtent {

        private MovingBorderExtent(double from, double to, long duration, long gameTime) {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.<init>:(DDJJ)V");
        }

        public double getMinX(float deltaPartialTick) {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.getMinX:(F)D");
        }

        public double getMinZ(float deltaPartialTick) {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.getMinZ:(F)D");
        }

        public double getMaxX(float deltaPartialTick) {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.getMaxX:(F)D");
        }

        public double getMaxZ(float deltaPartialTick) {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.getMaxZ:(F)D");
        }

        public double getSize() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.getSize:()D");
        }

        public double getLerpSpeed() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.getLerpSpeed:()D");
        }

        public long getLerpTime() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.getLerpTime:()J");
        }

        public double getLerpTarget() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.getLerpTarget:()D");
        }

        public BorderStatus getStatus() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.getStatus:()Lnet/minecraft/world/level/border/BorderStatus;");
        }

        public void onCenterChange() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.onCenterChange:()V");
        }

        public void onAbsoluteMaxSizeChange() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.onAbsoluteMaxSizeChange:()V");
        }

        public WorldBorder.BorderExtent update() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.update:()Lnet/minecraft/world/level/border/WorldBorder$BorderExtent;");
        }

        public VoxelShape getCollisionShape() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$MovingBorderExtent.getCollisionShape:()Lnet/minecraft/world/phys/shapes/VoxelShape;");
        }

        protected MovingBorderExtent() {
        }
    }

    public record Settings(double centerX, double centerZ, double damagePerBlock, double safeZone, int warningBlocks, int warningTime, double size, long lerpTime, double lerpTarget) {

        public Settings(WorldBorder worldBorder) {
            this((double) 0.0, (double) 0.0, (double) 0.0, (double) 0.0, (int) 0, (int) 0, (double) 0.0, (long) 0L, (double) 0.0);
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$Settings.<init>:(Lnet/minecraft/world/level/border/WorldBorder;)V");
        }
    }

    private class StaticBorderExtent implements WorldBorder.BorderExtent {

        public StaticBorderExtent(double size) {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.<init>:(D)V");
        }

        public double getMinX(float deltaPartialTick) {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.getMinX:(F)D");
        }

        public double getMaxX(float deltaPartialTick) {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.getMaxX:(F)D");
        }

        public double getMinZ(float deltaPartialTick) {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.getMinZ:(F)D");
        }

        public double getMaxZ(float deltaPartialTick) {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.getMaxZ:(F)D");
        }

        public double getSize() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.getSize:()D");
        }

        public BorderStatus getStatus() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.getStatus:()Lnet/minecraft/world/level/border/BorderStatus;");
        }

        public double getLerpSpeed() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.getLerpSpeed:()D");
        }

        public long getLerpTime() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.getLerpTime:()J");
        }

        public double getLerpTarget() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.getLerpTarget:()D");
        }

        public void onAbsoluteMaxSizeChange() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.onAbsoluteMaxSizeChange:()V");
        }

        public void onCenterChange() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.onCenterChange:()V");
        }

        public WorldBorder.BorderExtent update() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.update:()Lnet/minecraft/world/level/border/WorldBorder$BorderExtent;");
        }

        public VoxelShape getCollisionShape() {
            throw Unimplemented.forMember("net/minecraft/world/level/border/WorldBorder$StaticBorderExtent.getCollisionShape:()Lnet/minecraft/world/phys/shapes/VoxelShape;");
        }

        protected StaticBorderExtent() {
        }
    }
}
