package net.minecraft.gametest.framework;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Rotation;
import dev.pumpkin.shim.Unimplemented;

public class GameTestInfo {

    public GameTestInfo(Holder.Reference<GameTestInstance> test, Rotation extraRotation, ServerLevel level, RetryOptions retryOptions) {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestInfo.<init>:(Lnet/minecraft/core/Holder$Reference;Lnet/minecraft/world/level/block/Rotation;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/gametest/framework/RetryOptions;)V");
    }

    public Identifier id() {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestInfo.id:()Lnet/minecraft/resources/Identifier;");
    }

    public ServerLevel getLevel() {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestInfo.getLevel:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public GameTestException getError() {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestInfo.getError:()Lnet/minecraft/gametest/framework/GameTestException;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestInfo.toString:()Ljava/lang/String;");
    }

    public GameTestInfo() {
    }
}
