package net.minecraft.network.protocol.game;

import java.util.Optional;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import dev.pumpkin.shim.Unimplemented;

public record CommonPlayerSpawnInfo(Holder<DimensionType> dimensionType, ResourceKey<Level> dimension, long seed, GameType gameType, GameType previousGameType, boolean isDebug, boolean isFlat, Optional<GlobalPos> lastDeathLocation, int portalCooldown, int seaLevel) {

    public CommonPlayerSpawnInfo(RegistryFriendlyByteBuf input) {
        this((Holder<DimensionType>) null, (ResourceKey<Level>) null, (long) 0L, (GameType) null, (GameType) null, (boolean) false, (boolean) false, (Optional<GlobalPos>) null, (int) 0, (int) 0);
    }

    public void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/CommonPlayerSpawnInfo.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }
}
