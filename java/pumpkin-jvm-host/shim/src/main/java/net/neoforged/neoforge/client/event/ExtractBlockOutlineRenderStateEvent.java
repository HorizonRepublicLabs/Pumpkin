package net.neoforged.neoforge.client.event;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.extract.LevelExtractor;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.client.CustomBlockOutlineRenderer;
import dev.pumpkin.shim.Unimplemented;

public final class ExtractBlockOutlineRenderStateEvent extends Event implements ICancellableEvent {

    public ExtractBlockOutlineRenderStateEvent(LevelExtractor levelExtractor, ClientLevel level, BlockPos pos, BlockState state, BlockHitResult hitResult, CollisionContext collisionContext, boolean inTranslucentPass, boolean highContrast, Camera camera, LevelRenderState levelRenderState) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ExtractBlockOutlineRenderStateEvent.<init>:(Lnet/minecraft/client/renderer/extract/LevelExtractor;Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/phys/BlockHitResult;Lnet/minecraft/world/phys/shapes/CollisionContext;ZZLnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/state/level/LevelRenderState;)V");
    }

    public void addCustomRenderer(CustomBlockOutlineRenderer renderer) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ExtractBlockOutlineRenderStateEvent.addCustomRenderer:(Lnet/neoforged/neoforge/client/CustomBlockOutlineRenderer;)V");
    }

    public ClientLevel getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ExtractBlockOutlineRenderStateEvent.getLevel:()Lnet/minecraft/client/multiplayer/ClientLevel;");
    }

    public BlockPos getBlockPos() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ExtractBlockOutlineRenderStateEvent.getBlockPos:()Lnet/minecraft/core/BlockPos;");
    }

    public BlockHitResult getHitResult() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ExtractBlockOutlineRenderStateEvent.getHitResult:()Lnet/minecraft/world/phys/BlockHitResult;");
    }

    public Camera getCamera() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ExtractBlockOutlineRenderStateEvent.getCamera:()Lnet/minecraft/client/Camera;");
    }

    public ExtractBlockOutlineRenderStateEvent() {
    }
}
