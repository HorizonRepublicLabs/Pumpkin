package net.minecraft.server;

import com.mojang.datafixers.DataFixer;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.BiConsumer;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import dev.pumpkin.shim.Unimplemented;

public class PlayerAdvancements {

    public PlayerAdvancements(DataFixer dataFixer, PlayerList playerList, ServerAdvancementManager manager, Path playerSavePath, ServerPlayer player) {
    }

    protected void load(ServerAdvancementManager manager) {
        throw Unimplemented.forMember("net/minecraft/server/PlayerAdvancements.load:(Lnet/minecraft/server/ServerAdvancementManager;)V");
    }

    public void save() {
        throw Unimplemented.forMember("net/minecraft/server/PlayerAdvancements.save:()V");
    }

    private record Data(Map<Identifier, AdvancementProgress> map) {

        public void forEach(BiConsumer<Identifier, AdvancementProgress> consumer) {
            throw Unimplemented.forMember("net/minecraft/server/PlayerAdvancements$Data.forEach:(Ljava/util/function/BiConsumer;)V");
        }
    }

    public record TriggerInstanceKey(AdvancementHolder advancement, String criterion) {
    }

    public PlayerAdvancements() {
    }
}
