package net.minecraft.stats;

import java.nio.file.Path;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import dev.pumpkin.shim.Unimplemented;

public class ServerStatsCounter extends StatsCounter {

    public ServerStatsCounter(MinecraftServer server, Path file) {
    }

    public void save() {
        throw Unimplemented.forMember("net/minecraft/stats/ServerStatsCounter.save:()V");
    }

    public void setValue(Player player, Stat<?> stat, int count) {
        throw Unimplemented.forMember("net/minecraft/stats/ServerStatsCounter.setValue:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/stats/Stat;I)V");
    }

    public ServerStatsCounter() {
    }
}
