package net.minecraft.world.level.gamerules;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.flag.FeatureFlagSet;
import dev.pumpkin.shim.Unimplemented;

public class GameRules {

    public static final GameRule<Boolean> MOB_DROPS = null;

    public GameRules(FeatureFlagSet enabledFeatures, GameRuleMap map) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRules.<init>:(Lnet/minecraft/world/flag/FeatureFlagSet;Lnet/minecraft/world/level/gamerules/GameRuleMap;)V");
    }

    public GameRules(FeatureFlagSet enabledFeatures) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRules.<init>:(Lnet/minecraft/world/flag/FeatureFlagSet;)V");
    }

    public GameRules(List<GameRule<?>> rules) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRules.<init>:(Ljava/util/List;)V");
    }

    public <T> T get(GameRule<T> gameRule) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRules.get:(Lnet/minecraft/world/level/gamerules/GameRule;)Ljava/lang/Object;");
    }

    public <T> void set(GameRule<T> gameRule, T value, MinecraftServer server) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRules.set:(Lnet/minecraft/world/level/gamerules/GameRule;Ljava/lang/Object;Lnet/minecraft/server/MinecraftServer;)V");
    }

    public GameRules copy(FeatureFlagSet enabledFeatures) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRules.copy:(Lnet/minecraft/world/flag/FeatureFlagSet;)Lnet/minecraft/world/level/gamerules/GameRules;");
    }

    public interface VisitorCaller<T> {

        void call(GameRuleTypeVisitor visitor, GameRule<T> key);
    }

    protected GameRules() {
    }
}
