package net.minecraft.world.level.gamerules;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import java.util.function.ToIntFunction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import dev.pumpkin.shim.Unimplemented;

public final class GameRule<T> implements FeatureElement {

    public GameRule(GameRuleCategory category, GameRuleType gameRuleType, ArgumentType<T> argument, GameRules.VisitorCaller<T> visitorCaller, Codec<T> valueCodec, ToIntFunction<T> commandResultFunction, T defaultValue, FeatureFlagSet requiredFeatures) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRule.<init>:(Lnet/minecraft/world/level/gamerules/GameRuleCategory;Lnet/minecraft/world/level/gamerules/GameRuleType;Lcom/mojang/brigadier/arguments/ArgumentType;Lnet/minecraft/world/level/gamerules/GameRules$VisitorCaller;Lcom/mojang/serialization/Codec;Ljava/util/function/ToIntFunction;Ljava/lang/Object;Lnet/minecraft/world/flag/FeatureFlagSet;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRule.toString:()Ljava/lang/String;");
    }

    public String id() {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRule.id:()Ljava/lang/String;");
    }

    public Identifier getIdentifier() {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRule.getIdentifier:()Lnet/minecraft/resources/Identifier;");
    }

    public String serialize(T value) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRule.serialize:(Ljava/lang/Object;)Ljava/lang/String;");
    }

    public DataResult<T> deserialize(String value) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRule.deserialize:(Ljava/lang/String;)Lcom/mojang/serialization/DataResult;");
    }

    public GameRuleCategory category() {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRule.category:()Lnet/minecraft/world/level/gamerules/GameRuleCategory;");
    }

    public Codec<T> valueCodec() {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRule.valueCodec:()Lcom/mojang/serialization/Codec;");
    }

    public FeatureFlagSet requiredFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRule.requiredFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    protected GameRule() {
    }
}
