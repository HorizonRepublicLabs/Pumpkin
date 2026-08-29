package net.minecraft.gametest.framework;

import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.Unit;
import net.minecraft.world.Difficulty;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.level.gamerules.GameRuleMap;
import net.minecraft.world.timeline.Timeline;
import dev.pumpkin.shim.Unimplemented;

public interface TestEnvironmentDefinition<SavedDataType> {

    SavedDataType setup(ServerLevel level);

    void teardown(final ServerLevel level, final SavedDataType saveData);

    MapCodec<? extends TestEnvironmentDefinition<SavedDataType>> codec();

    class Activation<T> {

        private Activation(T value, TestEnvironmentDefinition<T> definition, ServerLevel level) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$Activation.<init>:(Ljava/lang/Object;Lnet/minecraft/gametest/framework/TestEnvironmentDefinition;Lnet/minecraft/server/level/ServerLevel;)V");
        }

        protected Activation() {
        }
    }

    record AllOf(List<Holder<TestEnvironmentDefinition<?>>> definitions) implements TestEnvironmentDefinition<List<? extends TestEnvironmentDefinition.Activation<?>>> {

        public AllOf(TestEnvironmentDefinition<?>... defs) {
            this((List<Holder<TestEnvironmentDefinition<?>>>) null);
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$AllOf.<init>:([Lnet/minecraft/gametest/framework/TestEnvironmentDefinition;)V");
        }

        public List<? extends TestEnvironmentDefinition.Activation<?>> setup(ServerLevel level) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$AllOf.setup:(Lnet/minecraft/server/level/ServerLevel;)Ljava/util/List;");
        }

        public void teardown(ServerLevel level, List<? extends TestEnvironmentDefinition.Activation<?>> activations) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$AllOf.teardown:(Lnet/minecraft/server/level/ServerLevel;Ljava/util/List;)V");
        }

        public MapCodec<TestEnvironmentDefinition.AllOf> codec() {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$AllOf.codec:()Lcom/mojang/serialization/MapCodec;");
        }
    }

    record ClockTime(Holder<WorldClock> clock, int time) implements TestEnvironmentDefinition<Long> {

        public Long setup(ServerLevel level) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$ClockTime.setup:(Lnet/minecraft/server/level/ServerLevel;)Ljava/lang/Long;");
        }

        public void teardown(ServerLevel level, Long saveData) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$ClockTime.teardown:(Lnet/minecraft/server/level/ServerLevel;Ljava/lang/Long;)V");
        }

        public MapCodec<TestEnvironmentDefinition.ClockTime> codec() {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$ClockTime.codec:()Lcom/mojang/serialization/MapCodec;");
        }
    }

    record Functions(Optional<Identifier> setupFunction, Optional<Identifier> teardownFunction) implements TestEnvironmentDefinition<Unit> {

        public Unit setup(ServerLevel level) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$Functions.setup:(Lnet/minecraft/server/level/ServerLevel;)Lnet/minecraft/util/Unit;");
        }

        public void teardown(ServerLevel level, Unit saveData) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$Functions.teardown:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/util/Unit;)V");
        }

        public MapCodec<TestEnvironmentDefinition.Functions> codec() {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$Functions.codec:()Lcom/mojang/serialization/MapCodec;");
        }
    }

    record SetDifficulty(Difficulty difficulty) implements TestEnvironmentDefinition<Difficulty> {

        public Difficulty setup(ServerLevel level) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$SetDifficulty.setup:(Lnet/minecraft/server/level/ServerLevel;)Lnet/minecraft/world/Difficulty;");
        }

        public void teardown(ServerLevel level, Difficulty saveData) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$SetDifficulty.teardown:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/Difficulty;)V");
        }

        public MapCodec<TestEnvironmentDefinition.SetDifficulty> codec() {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$SetDifficulty.codec:()Lcom/mojang/serialization/MapCodec;");
        }
    }

    record SetGameRules(GameRuleMap gameRulesMap) implements TestEnvironmentDefinition<GameRuleMap> {

        public GameRuleMap setup(ServerLevel level) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$SetGameRules.setup:(Lnet/minecraft/server/level/ServerLevel;)Lnet/minecraft/world/level/gamerules/GameRuleMap;");
        }

        public void teardown(ServerLevel level, GameRuleMap saveData) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$SetGameRules.teardown:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/gamerules/GameRuleMap;)V");
        }

        public MapCodec<TestEnvironmentDefinition.SetGameRules> codec() {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$SetGameRules.codec:()Lcom/mojang/serialization/MapCodec;");
        }
    }

    record Timelines(List<Holder<Timeline>> timelines) implements TestEnvironmentDefinition<EnvironmentAttributeSystem> {

        public EnvironmentAttributeSystem setup(ServerLevel level) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$Timelines.setup:(Lnet/minecraft/server/level/ServerLevel;)Lnet/minecraft/world/attribute/EnvironmentAttributeSystem;");
        }

        public void teardown(ServerLevel level, EnvironmentAttributeSystem saveData) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$Timelines.teardown:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/attribute/EnvironmentAttributeSystem;)V");
        }

        public MapCodec<TestEnvironmentDefinition.Timelines> codec() {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$Timelines.codec:()Lcom/mojang/serialization/MapCodec;");
        }
    }

    record Weather(TestEnvironmentDefinition.Weather.Type weather) implements TestEnvironmentDefinition<TestEnvironmentDefinition.Weather.Type> {

        public TestEnvironmentDefinition.Weather.Type setup(ServerLevel level) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$Weather.setup:(Lnet/minecraft/server/level/ServerLevel;)Lnet/minecraft/gametest/framework/TestEnvironmentDefinition$Weather$Type;");
        }

        public void teardown(ServerLevel level, TestEnvironmentDefinition.Weather.Type saveData) {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$Weather.teardown:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/gametest/framework/TestEnvironmentDefinition$Weather$Type;)V");
        }

        public MapCodec<TestEnvironmentDefinition.Weather> codec() {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$Weather.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public enum Type implements StringRepresentable {

            CLEAR, RAIN, THUNDER;

            public void apply(ServerLevel level) {
                throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$Weather$Type.apply:(Lnet/minecraft/server/level/ServerLevel;)V");
            }

            public String getSerializedName() {
                throw Unimplemented.forMember("net/minecraft/gametest/framework/TestEnvironmentDefinition$Weather$Type.getSerializedName:()Ljava/lang/String;");
            }
        }
    }
}
