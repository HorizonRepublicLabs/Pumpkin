package net.minecraft.world.level.gamerules;

import dev.pumpkin.shim.Unimplemented;

public interface GameRuleTypeVisitor {

    default <T> void visit(GameRule<T> gameRule) {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleTypeVisitor.visit:(Lnet/minecraft/world/level/gamerules/GameRule;)V");
    }
}
