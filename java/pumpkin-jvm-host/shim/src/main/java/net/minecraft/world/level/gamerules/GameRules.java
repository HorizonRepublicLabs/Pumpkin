package net.minecraft.world.level.gamerules;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.flag.FeatureFlagSet;
import dev.pumpkin.shim.Unimplemented;

public class GameRules {

    public static final GameRule<Boolean> ADVANCE_TIME = null;

    public static final GameRule<Boolean> ADVANCE_WEATHER = null;

    public static final GameRule<Boolean> BLOCK_EXPLOSION_DROP_DECAY = null;

    public static final GameRule<Boolean> KEEP_INVENTORY = null;

    public static final GameRule<Boolean> LAVA_SOURCE_CONVERSION = null;

    public static final GameRule<Boolean> LIMITED_CRAFTING = null;

    public static final GameRule<Boolean> MOB_DROPS = null;

    public static final GameRule<Boolean> MOB_GRIEFING = null;

    public static final GameRule<Boolean> SPAWN_MOBS = null;

    public static final GameRule<Boolean> SPAWN_MONSTERS = null;

    public static final GameRule<Boolean> SPAWN_PATROLS = null;

    public static final GameRule<Boolean> SPAWN_PHANTOMS = null;

    public static final GameRule<Boolean> SPAWN_WANDERING_TRADERS = null;

    public static final GameRule<Boolean> TNT_EXPLODES = null;

    public static final GameRule<Boolean> WATER_SOURCE_CONVERSION = null;

    public GameRules(FeatureFlagSet enabledFeatures, GameRuleMap map) {
    }

    public GameRules(FeatureFlagSet enabledFeatures) {
    }

    public GameRules(List<GameRule<?>> rules) {
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

    public GameRules() {
    }
}
