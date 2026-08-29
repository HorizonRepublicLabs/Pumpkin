package net.minecraft.world.level.levelgen.structure.templatesystem;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public abstract class RuleTest {

    public abstract boolean test(BlockState state, RandomSource random);

    protected abstract RuleTestType<?> getType();

    protected RuleTest() {
    }
}
