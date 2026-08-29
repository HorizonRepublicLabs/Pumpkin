package net.minecraft.gametest.framework;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.MutableComponent;
import dev.pumpkin.shim.Unimplemented;

public abstract class GameTestInstance {

    protected GameTestInstance(TestData<Holder<TestEnvironmentDefinition<?>>> info) {
    }

    public abstract void run(GameTestHelper helper);

    public abstract MapCodec<? extends GameTestInstance> codec();

    protected TestData<Holder<TestEnvironmentDefinition<?>>> info() {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestInstance.info:()Lnet/minecraft/gametest/framework/TestData;");
    }

    protected abstract MutableComponent typeDescription();

    public GameTestInstance() {
    }
}
