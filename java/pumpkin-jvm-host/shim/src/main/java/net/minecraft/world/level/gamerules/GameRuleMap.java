package net.minecraft.world.level.gamerules;

import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import java.util.Set;
import net.minecraft.world.level.saveddata.SavedData;
import dev.pumpkin.shim.Unimplemented;

public final class GameRuleMap extends SavedData {

    private GameRuleMap(Reference2ObjectMap<GameRule<?>, Object> map) {
    }

    public static GameRuleMap copyOf(GameRuleMap gameRuleMap) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleMap.copyOf:(Lnet/minecraft/world/level/gamerules/GameRuleMap;)Lnet/minecraft/world/level/gamerules/GameRuleMap;");
    }

    public <T> T get(GameRule<T> gameRule) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleMap.get:(Lnet/minecraft/world/level/gamerules/GameRule;)Ljava/lang/Object;");
    }

    public <T> void set(GameRule<T> gameRule, T value) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleMap.set:(Lnet/minecraft/world/level/gamerules/GameRule;Ljava/lang/Object;)V");
    }

    public <T> T remove(GameRule<T> gameRule) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleMap.remove:(Lnet/minecraft/world/level/gamerules/GameRule;)Ljava/lang/Object;");
    }

    public Set<GameRule<?>> keySet() {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleMap.keySet:()Ljava/util/Set;");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleMap.size:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleMap.toString:()Ljava/lang/String;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleMap.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleMap.hashCode:()I");
    }

    public static class Builder {

        public <T> GameRuleMap.Builder set(GameRule<T> gameRule, T value) {
            throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleMap$Builder.set:(Lnet/minecraft/world/level/gamerules/GameRule;Ljava/lang/Object;)Lnet/minecraft/world/level/gamerules/GameRuleMap$Builder;");
        }

        public GameRuleMap build() {
            throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleMap$Builder.build:()Lnet/minecraft/world/level/gamerules/GameRuleMap;");
        }

        public Builder() {
        }
    }

    public GameRuleMap() {
    }
}
