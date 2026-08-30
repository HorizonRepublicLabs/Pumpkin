package dev.pumpkin.bridge;

import dev.pumpkin.shim.Unimplemented;
import java.lang.reflect.Method;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Right-clicks routed from the server into a mod's own block code.
 *
 * <p>Called over JNI when a player uses a JVM-registered block. Resolves the registered
 * {@code Block} instance, makes sure the mod's tile entity exists at the position, builds
 * the one-interaction {@code Level} and {@code Player} stand-ins, and invokes the block's
 * {@code useItemOn} the way vanilla would. What travels back is a flat string the Rust
 * side parses: the result kind, the hand stack if the mod replaced it, and any items the
 * mod dropped into the world.
 */
public final class PumpkinInteractions {
    private PumpkinInteractions() {
    }

    /**
     * One level for every interaction: the mod-side entities keep a reference to it, so
     * it has to outlive any single click. Drops are drained per interaction; the mod
     * thread serialises access.
     */
    private static final PumpkinLevel LEVEL = new PumpkinLevel();

    /** The shared level, for the entity registry to attach at creation. */
    static PumpkinLevel pumpkinLevel() {
        return LEVEL;
    }

    public static String useBlockOn(String blockId, String entityTypeId, int x, int y, int z,
            String heldItemId, int heldCount) throws Exception {
        Object blockObject = DeferredHolder.pumpkinResolve("minecraft:block", blockId);
        if (!(blockObject instanceof Block block)) {
            return "PASS;HELD=unchanged;DROPS=";
        }

        if (!entityTypeId.isEmpty()
                && DeferredHolder.pumpkinResolve("minecraft:block_entity_type", entityTypeId)
                        instanceof BlockEntityType<?> type) {
            PumpkinBlockEntities.getOrCreate(type, x, y, z);
        }

        ItemStack held = buildStack(heldItemId, heldCount);
        PumpkinLevel level = LEVEL;
        level.pumpkinDrops().clear();
        PumpkinPlayer player = new PumpkinPlayer(held, x + 0.5, y + 1.0, z + 0.5);
        BlockPos pos = new BlockPos(x, y, z);
        BlockState state = block.defaultBlockState();
        BlockHitResult hit = new BlockHitResult(
                new Vec3(x + 0.5, y + 0.5, z + 0.5), Direction.UP, pos, false);

        Method method = findUseItemOn(block.getClass());
        method.setAccessible(true);
        Object result = method.invoke(block, held, state, level, pos, player,
                InteractionHand.MAIN_HAND, hit);

        StringBuilder reply = new StringBuilder();
        reply.append(kindOf(result));
        reply.append(";HELD=");
        ItemStack after = player.pumpkinHeldAfter();
        if (after == null) {
            reply.append("unchanged");
        } else {
            reply.append(describe(after));
        }
        reply.append(";DROPS=");
        boolean first = true;
        for (ItemStack drop : level.pumpkinDrops()) {
            if (!first) {
                reply.append(',');
            }
            reply.append(describe(drop));
            first = false;
        }
        return reply.toString();
    }

    private static ItemStack buildStack(String itemId, int count) {
        if (itemId.isEmpty() || count <= 0) {
            return new ItemStack((net.minecraft.world.level.ItemLike) null, 0);
        }
        Object registered = DeferredHolder.pumpkinResolve("minecraft:item", itemId);
        if (registered instanceof Item item) {
            return new ItemStack(item, count);
        }
        // A vanilla item has no holder here; a synthetic Item carrying the id is enough
        // for the mod to store and hand back, and the id survives the round trip.
        Item stand_in = new Item(new Item.Properties());
        stand_in.pumpkinSetRegisteredId(itemId);
        return new ItemStack(stand_in, count);
    }

    /** The item's registered id and count, e.g. {@code mysticalagriculture:prosperity_shard:3}. */
    private static String describe(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return "empty:0";
        }
        Item item = stack.getItem();
        String id = item == null ? null : item.pumpkinRegisteredId();
        return (id == null ? "unknown" : id) + ":" + stack.count();
    }

    private static String kindOf(Object result) {
        if (result instanceof InteractionResult.Success) {
            return "SUCCESS";
        }
        if (result instanceof InteractionResult.Fail) {
            return "FAIL";
        }
        if (result instanceof InteractionResult.Pass) {
            return "PASS";
        }
        return "PASS";
    }

    private static Method findUseItemOn(Class<?> type) throws NoSuchMethodException {
        for (Class<?> current = type; current != null; current = current.getSuperclass()) {
            for (Method method : current.getDeclaredMethods()) {
                if (method.getName().equals("useItemOn") && method.getParameterCount() == 7) {
                    return method;
                }
            }
        }
        throw new NoSuchMethodException(type.getName() + " has no useItemOn(7 args)");
    }
}
