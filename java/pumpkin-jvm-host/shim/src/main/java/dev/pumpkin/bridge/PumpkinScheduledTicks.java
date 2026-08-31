package dev.pumpkin.bridge;

import java.lang.reflect.Method;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Placement and scheduled ticks routed into a mod's own block code -- how a growth
 * accelerator starts and keeps its rhythm.
 *
 * <p>The accelerator's {@code onPlace} asks the level for a tick some seconds out; its
 * {@code tick} scans the column above for a bonemealable block, forces a random tick on
 * it, and schedules the next round. Both requests are captured off the level stand-in and
 * carried back for the server to honour.
 */
public final class PumpkinScheduledTicks {
    private PumpkinScheduledTicks() {
    }

    /**
     * Runs the mod's {@code onPlace}, if it declares one.
     *
     * @return {@code PLACED;SCHEDULE=<ticks>} or {@code PLACED;SCHEDULE=none};
     *         {@code PASS} when no mod class overrides onPlace
     */
    public static String onPlace(String blockId, int x, int y, int z, String stateSpec)
            throws Exception {
        Object blockObject = DeferredHolder.pumpkinResolve("minecraft:block", blockId);
        if (!(blockObject instanceof Block block)) {
            return "PASS";
        }
        Method method = findModMethod(block.getClass(), "onPlace", 5);
        if (method == null) {
            return "PASS";
        }
        BlockState state = PumpkinRandomTicks.withValues(block.defaultBlockState(), block, stateSpec);
        PumpkinLevel level = PumpkinInteractions.pumpkinLevel();
        level.pumpkinSetRandomTickContext(0, Map.of(x + "," + y + "," + z, state));
        try {
            method.setAccessible(true);
            // The old state is the block itself: the mods that override this only read
            // the level and the position, and inventing a previous block would be a guess.
            method.invoke(block, state, level, new BlockPos(x, y, z), state, false);
            return "PLACED;SCHEDULE=" + scheduleOf(level);
        } finally {
            level.pumpkinClearRandomTickContext();
        }
    }

    /**
     * Runs the mod's {@code tick} against a column snapshot.
     *
     * @param brightnessSpec {@code x,y,z:level} semicolon-joined -- light measured per
     *                       position, because the crop the tick reaches sits above the
     *                       ticked block
     * @return {@code TICKED;WRITES=x,y,z:prop=v,prop=v&...;SCHEDULE=<ticks>|none;SOUNDS=...}
     */
    public static String tick(String blockId, int x, int y, int z, String stateSpec,
            String neighborhood, String brightnessSpec) throws Exception {
        Object blockObject = DeferredHolder.pumpkinResolve("minecraft:block", blockId);
        if (!(blockObject instanceof Block block)) {
            return "PASS";
        }
        Method method = findModMethod(block.getClass(), "tick", 4);
        if (method == null) {
            return "PASS";
        }

        java.util.Map<String, BlockState> snapshot = PumpkinRandomTicks.snapshotOf(neighborhood);
        BlockState state = PumpkinRandomTicks.withValues(block.defaultBlockState(), block, stateSpec);
        snapshot.put(x + "," + y + "," + z, state);
        java.util.Map<String, Integer> brightness = new java.util.HashMap<>();
        for (String entry : brightnessSpec.split(";")) {
            if (entry.isEmpty()) {
                continue;
            }
            int colon = entry.lastIndexOf(':');
            brightness.put(entry.substring(0, colon),
                    Integer.parseInt(entry.substring(colon + 1)));
        }

        PumpkinLevel level = PumpkinInteractions.pumpkinLevel();
        level.pumpkinSetRandomTickContext(0, brightness, snapshot);
        try {
            method.setAccessible(true);
            method.invoke(block, state, level, new BlockPos(x, y, z), level.getRandom());
            StringBuilder reply = new StringBuilder("TICKED;WRITES=");
            boolean first = true;
            for (Map.Entry<String, BlockState> write : level.pumpkinWrites().entrySet()) {
                if (!first) {
                    reply.append('&');
                }
                reply.append(write.getKey()).append(':')
                        .append(PumpkinRandomTicks.describe(write.getValue()));
                first = false;
            }
            reply.append(";SCHEDULE=").append(scheduleOf(level));
            reply.append(";SOUNDS=").append(String.join(",", level.pumpkinDrainSounds()));
            return reply.toString();
        } finally {
            level.pumpkinClearRandomTickContext();
        }
    }

    private static String scheduleOf(PumpkinLevel level) {
        Integer delay = level.pumpkinScheduledDelay();
        return delay == null ? "none" : delay.toString();
    }

    /** A mod-declared override, or null -- the walk stops at the first shim class. */
    private static Method findModMethod(Class<?> type, String name, int parameterCount) {
        for (Class<?> current = type; current != null
                && !current.getName().startsWith("net.minecraft."); current = current.getSuperclass()) {
            for (Method method : current.getDeclaredMethods()) {
                if (method.getName().equals(name)
                        && method.getParameterCount() == parameterCount) {
                    return method;
                }
            }
        }
        return null;
    }
}
