package net.minecraft.network.protocol.game;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundPlayerLookAtPacket implements Packet<ClientGamePacketListener> {

    public ClientboundPlayerLookAtPacket(EntityAnchorArgument.Anchor fromAnchor, double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerLookAtPacket.<init>:(Lnet/minecraft/commands/arguments/EntityAnchorArgument$Anchor;DDD)V");
    }

    public ClientboundPlayerLookAtPacket(EntityAnchorArgument.Anchor fromAnchor, Entity entity, EntityAnchorArgument.Anchor toAnchor) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerLookAtPacket.<init>:(Lnet/minecraft/commands/arguments/EntityAnchorArgument$Anchor;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/commands/arguments/EntityAnchorArgument$Anchor;)V");
    }

    private ClientboundPlayerLookAtPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerLookAtPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerLookAtPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundPlayerLookAtPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerLookAtPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerLookAtPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public Vec3 getPosition(Level level) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerLookAtPacket.getPosition:(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/phys/Vec3;");
    }

    protected ClientboundPlayerLookAtPacket() {
    }
}
