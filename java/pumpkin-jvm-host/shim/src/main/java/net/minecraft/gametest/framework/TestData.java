package net.minecraft.gametest.framework;

import java.util.function.Function;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Rotation;
import dev.pumpkin.shim.Unimplemented;

public record TestData<EnvironmentType>(EnvironmentType environment, Identifier structure, int maxTicks, int setupTicks, boolean required, Rotation rotation, boolean manualOnly, int maxAttempts, int requiredSuccesses, boolean skyAccess, int padding) {

    public TestData(EnvironmentType environment, Identifier structure, int maxTicks, int setupTicks, boolean required, Rotation rotation) {
        this((EnvironmentType) null, (Identifier) null, (int) 0, (int) 0, (boolean) false, (Rotation) null, (boolean) false, (int) 0, (int) 0, (boolean) false, (int) 0);
    }

    public TestData(EnvironmentType environment, Identifier structure, int maxTicks, int setupTicks, boolean required) {
        this((EnvironmentType) null, (Identifier) null, (int) 0, (int) 0, (boolean) false, (Rotation) null, (boolean) false, (int) 0, (int) 0, (boolean) false, (int) 0);
    }

    public <T> TestData<T> map(Function<EnvironmentType, T> mapper) {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/TestData.map:(Ljava/util/function/Function;)Lnet/minecraft/gametest/framework/TestData;");
    }
}
