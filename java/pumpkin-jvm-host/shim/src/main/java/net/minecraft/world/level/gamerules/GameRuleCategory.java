package net.minecraft.world.level.gamerules;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record GameRuleCategory(Identifier id) {

    private static GameRuleCategory register(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleCategory.register:(Ljava/lang/String;)Lnet/minecraft/world/level/gamerules/GameRuleCategory;");
    }

    public static GameRuleCategory register(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleCategory.register:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/world/level/gamerules/GameRuleCategory;");
    }
}
