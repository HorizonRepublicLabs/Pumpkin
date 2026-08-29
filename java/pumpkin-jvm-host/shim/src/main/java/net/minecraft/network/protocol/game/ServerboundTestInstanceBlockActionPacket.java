package net.minecraft.network.protocol.game;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.gametest.framework.GameTestInstance;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.TestInstanceBlockEntity;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundTestInstanceBlockActionPacket(BlockPos pos, ServerboundTestInstanceBlockActionPacket.Action action, TestInstanceBlockEntity.Data data) implements Packet<ServerGamePacketListener> {

    public ServerboundTestInstanceBlockActionPacket(BlockPos pos, ServerboundTestInstanceBlockActionPacket.Action action, Optional<ResourceKey<GameTestInstance>> test, Vec3i size, Rotation rotation, boolean ignoreEntities) {
        this((BlockPos) null, (ServerboundTestInstanceBlockActionPacket.Action) null, (TestInstanceBlockEntity.Data) null);
    }

    public PacketType<ServerboundTestInstanceBlockActionPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundTestInstanceBlockActionPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundTestInstanceBlockActionPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public enum Action {

        INIT,
        QUERY,
        SET,
        RESET,
        SAVE,
        EXPORT,
        RUN
    }
}
