package net.neoforged.neoforge.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;

public final class PacketDistributor {

    protected PacketDistributor() {
    }

    // Pumpkin divergence: every send below drops its payload instead of throwing.
    //
    // A mod's custom payload is addressed to a mod-aware client, and there is never one
    // connected here: Pumpkin speaks vanilla Minecraft's protocol, and a vanilla client
    // has no decoder for mekanism:transporter_update. What these packets carry is
    // presentation -- the items drawn moving inside a transporter, a progress bar on an
    // open screen -- and the state they describe has already changed on the server.
    //
    // Throwing was worse than useless: the send happens in the middle of a mod's tick,
    // so the exception took the tick thread down mid-transaction. A transporter pulled
    // an item, aborted the transaction that would have removed it from the chest, and
    // then delivered its half-finished stack again every tick -- items appearing out of
    // nothing, 558 dead ticks in a forty-second run.
    //
    // Said once per payload type, so the log names what a client would have been shown
    // without repeating it twenty times a second.
    private static final java.util.Set<String> PUMPKIN_DROPPED =
            java.util.concurrent.ConcurrentHashMap.newKeySet();

    private static void pumpkinDrop(CustomPacketPayload payload, CustomPacketPayload... payloads) {
        pumpkinDropOne(payload);
        if (payloads != null) {
            for (CustomPacketPayload extra : payloads) {
                pumpkinDropOne(extra);
            }
        }
    }

    private static void pumpkinDropOne(CustomPacketPayload payload) {
        if (payload == null) {
            return;
        }
        String name = payload.getClass().getName();
        if (PUMPKIN_DROPPED.add(name)) {
            System.err.println("[pumpkin] a mod sent " + name + " to its client; no client"
                    + " here can read a mod's packets, so it is dropped -- the server-side"
                    + " state it describes is unaffected");
        }
    }

    public static void sendToPlayer(ServerPlayer player, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        pumpkinDrop(payload, payloads);
    }

    public static void sendToAllPlayers(CustomPacketPayload payload, CustomPacketPayload... payloads) {
        pumpkinDrop(payload, payloads);
    }

    public static void sendToPlayersTrackingEntity(Entity entity, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        pumpkinDrop(payload, payloads);
    }

    public static void sendToPlayersTrackingEntityAndSelf(Entity entity, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        pumpkinDrop(payload, payloads);
    }

    public static void sendToPlayersTrackingChunk(ServerLevel level, ChunkPos chunkPos, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        pumpkinDrop(payload, payloads);
    }
}
