package net.neoforged.neoforge.common.world.chunk;

import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public record TicketController(Identifier id, LoadingValidationCallback callback) {

    public TicketController(Identifier id) {
        this((Identifier) null, (LoadingValidationCallback) null);
    }

    public boolean forceChunk(ServerLevel level, BlockPos owner, int chunkX, int chunkZ, boolean add, boolean forceNaturalSpawning) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/TicketController.forceChunk:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;IIZZ)Z");
    }

    public boolean forceChunk(ServerLevel level, Entity owner, int chunkX, int chunkZ, boolean add, boolean forceNaturalSpawning) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/TicketController.forceChunk:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;IIZZ)Z");
    }

    public boolean forceChunk(ServerLevel level, UUID owner, int chunkX, int chunkZ, boolean add, boolean ticking) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/chunk/TicketController.forceChunk:(Lnet/minecraft/server/level/ServerLevel;Ljava/util/UUID;IIZZ)Z");
    }
}
