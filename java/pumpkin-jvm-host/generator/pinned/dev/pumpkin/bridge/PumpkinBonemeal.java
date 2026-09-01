package dev.pumpkin.bridge;

import java.lang.reflect.Method;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Bonemeal routed into a mod's own {@code BonemealableBlock} methods.
 *
 * <p>The server asks the three vanilla questions as three calls -- is this a valid
 * target, does this application succeed, perform it -- because that is the shape of the
 * Rust hook that dispatches them. Each call rebuilds the state and neighborhood; the
 * questions are cheap and bonemeal is rare.
 */
public final class PumpkinBonemeal {
    private PumpkinBonemeal() {
    }

    /**
     * @param mode {@code valid}, {@code success} or {@code perform}
     * @return {@code TRUE}/{@code FALSE} for the two questions; the perform mode answers
     *         {@code TICKED;STATE=age=N;SOUNDS=...} like a random tick, because it changes
     *         the world the same way. {@code PASS} when the block is not bonemealable.
     */
    public static String apply(String blockId, int x, int y, int z, String stateSpec,
            String neighborhood, String mode) throws Exception {
        Object blockObject = DeferredHolder.pumpkinResolve("minecraft:block", blockId);
        if (!(blockObject instanceof Block block) || !(block instanceof BonemealableBlock)) {
            return "PASS";
        }

        java.util.Map<String, BlockState> snapshot = PumpkinRandomTicks.snapshotOf(neighborhood);
        BlockState state = PumpkinRandomTicks.withValues(block.defaultBlockState(), block, stateSpec);
        snapshot.put(x + "," + y + "," + z, state);

        PumpkinLevel level = PumpkinInteractions.pumpkinLevel();
        level.pumpkinSetRandomTickContext(15, snapshot);
        try {
            BlockPos pos = new BlockPos(x, y, z);
            switch (mode) {
                case "valid" -> {
                    Method method = PumpkinRandomTicks.findMethod(
                            block.getClass(), "isValidBonemealTarget", 3);
                    method.setAccessible(true);
                    return (Boolean) method.invoke(block, level, pos, state) ? "TRUE" : "FALSE";
                }
                case "success" -> {
                    Method method = PumpkinRandomTicks.findMethod(
                            block.getClass(), "isBonemealSuccess", 4);
                    method.setAccessible(true);
                    return (Boolean) method.invoke(block, level, level.getRandom(), pos, state)
                            ? "TRUE" : "FALSE";
                }
                case "perform" -> {
                    Method method = PumpkinRandomTicks.findMethod(
                            block.getClass(), "performBonemeal", 4);
                    method.setAccessible(true);
                    method.invoke(block, level, level.getRandom(), pos, state);
                    StringBuilder reply = new StringBuilder("TICKED;STATE=");
                    BlockState written = level.pumpkinWrittenState(x, y, z);
                    if (written == null) {
                        reply.append("unchanged");
                    } else {
                        reply.append(PumpkinRandomTicks.describe(written));
                    }
                    reply.append(";SOUNDS=").append(String.join(",", level.pumpkinDrainSounds()));
                    return reply.toString();
                }
                default -> throw new IllegalArgumentException("unknown bonemeal mode " + mode);
            }
        } finally {
            level.pumpkinClearRandomTickContext();
        }
    }
}
