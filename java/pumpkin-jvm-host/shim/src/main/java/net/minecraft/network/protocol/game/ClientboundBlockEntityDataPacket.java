package net.minecraft.network.protocol.game;

import java.util.function.BiFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundBlockEntityDataPacket implements Packet<ClientGamePacketListener> {

    // Pumpkin divergence: an inert packet. Syncing a mod entity's data to clients is the
    // sync slice; the packet is built and dropped so the mark-dirty path survives.
    public static ClientboundBlockEntityDataPacket create(BlockEntity blockEntity, BiFunction<BlockEntity, RegistryAccess, CompoundTag> updateTagSaver) {
        return new ClientboundBlockEntityDataPacket();
    }

    // Pumpkin divergence: an inert packet. Syncing a mod entity's data to clients is the
    // sync slice; the packet is built and dropped so the mark-dirty path survives.
    public static ClientboundBlockEntityDataPacket create(BlockEntity blockEntity) {
        return new ClientboundBlockEntityDataPacket();
    }

    private ClientboundBlockEntityDataPacket(BlockPos pos, BlockEntityType<?> type, CompoundTag tag) {
    }

    public PacketType<ClientboundBlockEntityDataPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockEntityDataPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockEntityDataPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockEntityDataPacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public BlockEntityType<?> getType() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockEntityDataPacket.getType:()Lnet/minecraft/world/level/block/entity/BlockEntityType;");
    }

    public ClientboundBlockEntityDataPacket() {
    }
}
