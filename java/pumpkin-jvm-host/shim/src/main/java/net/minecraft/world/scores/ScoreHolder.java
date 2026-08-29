package net.minecraft.world.scores;

import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public interface ScoreHolder {

    String getScoreboardName();

    default Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/scores/ScoreHolder.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }
}
