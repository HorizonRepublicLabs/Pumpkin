package net.minecraft.world.level.saveddata.maps;

import io.netty.buffer.ByteBuf;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import dev.pumpkin.shim.Unimplemented;

public class MapItemSavedData extends SavedData {

    private MapItemSavedData(int centerX, int centerZ, byte scale, boolean trackingPosition, boolean unlimitedTracking, boolean locked, ResourceKey<Level> dimension) {
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/maps/MapItemSavedData.<init>:(IIBZZZLnet/minecraft/resources/ResourceKey;)V");
    }

    private MapItemSavedData(ResourceKey<Level> dimension, int centerX, int centerZ, byte scale, ByteBuffer colors, boolean trackingPosition, boolean unlimitedTracking, boolean locked, List<MapBanner> banners, List<MapFrame> frames) {
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/maps/MapItemSavedData.<init>:(Lnet/minecraft/resources/ResourceKey;IIBLjava/nio/ByteBuffer;ZZZLjava/util/List;Ljava/util/List;)V");
    }

    public class HoldingPlayer {

        private HoldingPlayer(Player player) {
            throw Unimplemented.forMember("net/minecraft/world/level/saveddata/maps/MapItemSavedData$HoldingPlayer.<init>:(Lnet/minecraft/world/entity/player/Player;)V");
        }

        protected HoldingPlayer() {
        }
    }

    private record MapDecorationLocation(Holder<MapDecorationType> type, byte x, byte y, byte rot) {
    }

    public record MapPatch(int startX, int startY, int width, int height, byte[] mapColors) {

        private static void write(ByteBuf output, Optional<MapItemSavedData.MapPatch> optional) {
            throw Unimplemented.forMember("net/minecraft/world/level/saveddata/maps/MapItemSavedData$MapPatch.write:(Lio/netty/buffer/ByteBuf;Ljava/util/Optional;)V");
        }

        private static Optional<MapItemSavedData.MapPatch> read(ByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/world/level/saveddata/maps/MapItemSavedData$MapPatch.read:(Lio/netty/buffer/ByteBuf;)Ljava/util/Optional;");
        }
    }

    protected MapItemSavedData() {
    }
}
