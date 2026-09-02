package dev.pumpkin.bridge;

import java.lang.reflect.Method;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * The state a mod's block wants when a player puts it down.
 *
 * <p>Without this every mod block went into the world in its default state, which for a
 * Mekanism machine means facing north whichever way the player was standing -- and a
 * machine's facing is not decoration: its side configuration is written in terms of
 * front, back and sides, so a wrongly-faced machine takes items in on the wrong face and
 * pushes them out of another.
 *
 * <p>The direction is not worked out here. Mods disagree about what placement means --
 * face the placer, face away, take the clicked face, ignore the question entirely -- so
 * the block is asked through its own {@code getStateForPlacement}, and what comes back is
 * whatever that block decided. A block that does not override it answers with its
 * default, which is its own answer too.
 */
public final class PumpkinPlacement {
    private PumpkinPlacement() {
    }

    /**
     * @param face    the clicked face, {@code north}/{@code up}/... as Rust spells it
     * @param yRot    the placing player's yaw in degrees
     * @param xRot    the placing player's pitch in degrees
     * @return {@code STATE=facing=east,...}, or {@code STATE=default} when the block
     *         wants the state it would have had anyway
     */
    public static String stateForPlacement(String blockId, int x, int y, int z, String face,
            float yRot, float xRot, boolean sneaking, String heldItemId, int heldCount,
            String playerUuid) throws Exception {
        Object blockObject = DeferredHolder.pumpkinResolve("minecraft:block", blockId);
        if (!(blockObject instanceof Block block)) {
            return "STATE=default";
        }
        Method method = PumpkinRandomTicks.findMethod(block.getClass(), "getStateForPlacement", 1);
        if (method == null) {
            return "STATE=default";
        }

        ItemStack held = PumpkinInteractions.pumpkinBuildStack(heldItemId, heldCount);
        PumpkinPlayer player = new PumpkinPlayer(held, x + 0.5, y + 1.0, z + 0.5);
        player.pumpkinSetSneaking(sneaking);
        player.pumpkinSetRotation(yRot, xRot);
        if (!playerUuid.isEmpty()) {
            player.pumpkinSetUuid(java.util.UUID.fromString(playerUuid));
        }

        BlockPlaceContext context = new BlockPlaceContext();
        context.pumpkinSet(new BlockPos(x, y, z), directionNamed(face), player, held);

        method.setAccessible(true);
        Object placed = method.invoke(block, context);
        if (!(placed instanceof BlockState state)) {
            // The block declined to place at all. Rust has already decided the block goes
            // here, so the honest answer is the state it would otherwise have had rather
            // than a state the mod never named.
            return "STATE=default";
        }
        // Always the concrete values, even when they match the block's own default:
        // Pumpkin numbers a registered block's states itself, and its default need not
        // be the state the mod calls default. Answering "default" for a facing the mod
        // named would hand that decision back to whichever state Pumpkin happened to
        // number first.
        String values = PumpkinRandomTicks.describe(state);
        return values.isEmpty() ? "STATE=default" : "STATE=" + values;
    }

    private static Direction directionNamed(String name) {
        for (Direction direction : Direction.values()) {
            if (direction.getSerializedName().equals(name)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("not a direction: " + name);
    }
}
