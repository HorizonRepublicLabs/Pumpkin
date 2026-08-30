package dev.pumpkin.bridge;

import java.lang.reflect.Method;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Random ticks routed from the server into a mod's own block code -- how crops grow.
 *
 * <p>Called over JNI from the world's random-tick loop. The request carries the ticked
 * block's state values, the light level there, and a snapshot of the neighboring states
 * growth logic reads (the soil square below, the same-level ring). The block's real
 * {@code randomTick} runs against the {@link PumpkinLevel} stand-in; whatever state it
 * wrote back travels to Rust as {@code STATE=age=3} for the server to apply.
 */
public final class PumpkinRandomTicks {
    private PumpkinRandomTicks() {
    }

    /**
     * @param stateSpec    the ticked block's property values, {@code age=2} comma-joined;
     *                     empty for a single-state block
     * @param neighborhood {@code x,y,z=namespace:path|prop=v,prop=v} semicolon-joined
     * @return {@code TICKED;STATE=age=3;SOUNDS=...} -- {@code STATE=unchanged} when the
     *         mod wrote nothing
     */
    public static String randomTick(String blockId, int x, int y, int z, String stateSpec,
            int brightness, String neighborhood) throws Exception {
        Object blockObject = DeferredHolder.pumpkinResolve("minecraft:block", blockId);
        if (!(blockObject instanceof Block block)) {
            return "PASS;STATE=unchanged;SOUNDS=";
        }

        java.util.Map<String, BlockState> snapshot = new java.util.HashMap<>();
        for (String entry : neighborhood.split(";")) {
            if (entry.isEmpty()) {
                continue;
            }
            int eq = entry.indexOf('=');
            snapshot.put(entry.substring(0, eq), stateOf(entry.substring(eq + 1)));
        }
        BlockState state = withValues(block.defaultBlockState(), block, stateSpec);
        snapshot.put(x + "," + y + "," + z, state);

        PumpkinLevel level = PumpkinInteractions.pumpkinLevel();
        level.pumpkinSetRandomTickContext(brightness, snapshot);
        try {
            Method method = findMethod(block.getClass(), "randomTick", 4);
            method.setAccessible(true);
            method.invoke(block, state, level, new BlockPos(x, y, z), level.getRandom());

            StringBuilder reply = new StringBuilder("TICKED;STATE=");
            BlockState written = level.pumpkinWrittenState(x, y, z);
            if (written == null) {
                reply.append("unchanged");
            } else {
                reply.append(describe(written));
            }
            reply.append(";SOUNDS=").append(String.join(",", level.pumpkinDrainSounds()));
            return reply.toString();
        } finally {
            level.pumpkinClearRandomTickContext();
        }
    }

    /** {@code namespace:path|prop=v,prop=v} into a state of the resolved block. */
    private static BlockState stateOf(String spec) {
        int bar = spec.indexOf('|');
        String id = bar < 0 ? spec : spec.substring(0, bar);
        Block block = resolveBlock(id);
        return withValues(block.defaultBlockState(), block, bar < 0 ? "" : spec.substring(bar + 1));
    }

    private static BlockState withValues(BlockState state, Block block, String values) {
        if (values.isEmpty()) {
            return state;
        }
        for (String pair : values.split(",")) {
            int eq = pair.indexOf('=');
            if (eq < 0) {
                continue;
            }
            String name = pair.substring(0, eq);
            String value = pair.substring(eq + 1);
            for (Property<?> property : block.pumpkinDeclaredProperties()) {
                if (name.equals(property.pumpkinName)) {
                    state = setRaw(state, property, property.pumpkinParse.get(value));
                    break;
                }
            }
        }
        return state;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static BlockState setRaw(BlockState state, Property property, Comparable value) {
        if (value == null) {
            return state;
        }
        return (BlockState) state.setValue(property, value);
    }

    /**
     * A block instance for a snapshot entry. Mod blocks resolve to the instance the mod
     * registered, so identity checks (a crux under a crop) hold. Vanilla names try the
     * shim's {@link Blocks} constants for the same reason -- growth compares the soil
     * against farmland. Anything else gets a transient stand-in whose identity matches
     * nothing, which is the honest answer for a block neither side handed to the mod.
     */
    private static Block resolveBlock(String id) {
        Object mod = DeferredHolder.pumpkinResolve("minecraft:block", id);
        if (mod instanceof Block block) {
            return block;
        }
        String path = id.startsWith("minecraft:") ? id.substring("minecraft:".length()) : id;
        Block vanilla = VANILLA.get(path);
        if (vanilla != null) {
            return vanilla;
        }
        return new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
                .pumpkinTemplate(path));
    }

    private static final java.util.Map<String, Block> VANILLA = vanillaConstants();

    private static java.util.Map<String, Block> vanillaConstants() {
        java.util.Map<String, Block> map = new java.util.HashMap<>();
        for (java.lang.reflect.Field field : Blocks.class.getDeclaredFields()) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())
                    && Block.class.isAssignableFrom(field.getType())) {
                try {
                    map.put(field.getName().toLowerCase(java.util.Locale.ROOT),
                            (Block) field.get(null));
                } catch (ReflectiveOperationException ignored) {
                    // A constant whose initializer failed stays unmapped; positions built
                    // on it fall to the transient stand-in below.
                }
            }
        }
        return map;
    }

    /** {@code prop=value} comma-joined, values in their serialized spelling. */
    private static String describe(BlockState state) {
        StringBuilder out = new StringBuilder();
        for (java.util.Map.Entry<Property<?>, Comparable<?>> entry
                : state.pumpkinValues.entrySet()) {
            if (out.length() > 0) {
                out.append(',');
            }
            Comparable<?> value = entry.getValue();
            String spelled = value instanceof StringRepresentable representable
                    ? representable.getSerializedName()
                    : String.valueOf(value);
            out.append(entry.getKey().pumpkinName).append('=').append(spelled);
        }
        return out.toString();
    }

    private static Method findMethod(Class<?> type, String name, int parameterCount) {
        for (Class<?> current = type; current != null; current = current.getSuperclass()) {
            for (Method method : current.getDeclaredMethods()) {
                if (method.getName().equals(name)
                        && method.getParameterCount() == parameterCount) {
                    return method;
                }
            }
        }
        throw new IllegalStateException(type.getName() + " has no " + name
                + " with " + parameterCount + " parameters");
    }
}
