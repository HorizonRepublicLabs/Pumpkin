package dev.pumpkin.bridge;

import java.lang.reflect.Method;
import java.util.List;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Block breaks routed into a mod's own {@code getDrops} -- how a harvested crop decides
 * what it yields.
 *
 * <p>Some blocks compute their drops in code rather than shipping a loot table:
 * MysticalAgriculture's crops read the age off the broken state, roll secondary-drop
 * chances against the soil, and build the stack list themselves. A block whose class
 * hierarchy carries no such override answers {@code PASS}, and the server falls back to
 * the drops parsed from loot tables at wire time.
 */
public final class PumpkinBlockDrops {
    private PumpkinBlockDrops() {
    }

    /**
     * @param stateSpec    the broken state's property values, {@code age=7} comma-joined
     * @param neighborhood the same snapshot shape the random-tick bridge sends; getDrops
     *                     reads the soil below the break
     * @return {@code OK;DROPS=id:count,...} or {@code PASS;DROPS=} when the mod defines
     *         no drops of its own
     */
    public static String getDrops(String blockId, int x, int y, int z, String stateSpec,
            String neighborhood) throws Exception {
        Object blockObject = DeferredHolder.pumpkinResolve("minecraft:block", blockId);
        if (!(blockObject instanceof Block block)) {
            return "PASS;DROPS=";
        }
        Method method = findModGetDrops(block.getClass());
        if (method == null) {
            return "PASS;DROPS=";
        }

        java.util.Map<String, BlockState> snapshot = PumpkinRandomTicks.snapshotOf(neighborhood);
        BlockState state = PumpkinRandomTicks.withValues(block.defaultBlockState(), block, stateSpec);
        snapshot.put(x + "," + y + "," + z, state);

        PumpkinLevel level = PumpkinInteractions.pumpkinLevel();
        level.pumpkinSetRandomTickContext(0, snapshot);
        try {
            method.setAccessible(true);
            Object result = method.invoke(block, state,
                    new PumpkinLootBuilder(level, new Vec3(x + 0.5, y + 0.5, z + 0.5)));
            StringBuilder reply = new StringBuilder("OK;DROPS=");
            boolean first = true;
            if (result instanceof List<?> drops) {
                for (Object dropped : drops) {
                    if (!(dropped instanceof ItemStack stack) || stack.isEmpty()) {
                        continue;
                    }
                    if (!first) {
                        reply.append(',');
                    }
                    reply.append(PumpkinInteractions.describe(stack));
                    first = false;
                }
            }
            return reply.toString();
        } finally {
            level.pumpkinClearRandomTickContext();
        }
    }

    /**
     * The mod's own {@code getDrops(BlockState, LootParams.Builder)}, or null.
     *
     * <p>The walk stops at the first {@code net.minecraft} class: a declaration there is
     * the shim's throwing stub, not mod logic, and running it would stop a break that the
     * static drop model handles fine.
     */
    private static Method findModGetDrops(Class<?> type) {
        for (Class<?> current = type; current != null
                && !current.getName().startsWith("net.minecraft."); current = current.getSuperclass()) {
            for (Method method : current.getDeclaredMethods()) {
                if (method.getName().equals("getDrops") && method.getParameterCount() == 2
                        && method.getParameterTypes()[0] == BlockState.class) {
                    return method;
                }
            }
        }
        return null;
    }

    /**
     * The loot builder a mod's getDrops receives: it answers the two questions crops ask
     * -- where the break happened and which level it happened in. Every other optional
     * parameter is absent, which is what optional means.
     */
    private static final class PumpkinLootBuilder extends LootParams.Builder {
        private final PumpkinLevel level;
        private final Vec3 origin;

        PumpkinLootBuilder(PumpkinLevel level, Vec3 origin) {
            this.level = level;
            this.origin = origin;
        }

        @Override
        public ServerLevel getLevel() {
            return level;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T getOptionalParameter(ContextKey<T> param) {
            if (param == LootContextParams.ORIGIN) {
                return (T) origin;
            }
            return null;
        }
    }
}
