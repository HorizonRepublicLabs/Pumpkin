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
            String heldItemId, int heldCount, String savedData, boolean hasSignal,
            boolean sneaking, String playerUuid) throws Exception {
        PumpkinLevel.pumpkinSetSignal(hasSignal);
        Object blockObject = DeferredHolder.pumpkinResolve("minecraft:block", blockId);
        if (!(blockObject instanceof Block block)) {
            return "PASS;HELD=unchanged;DROPS=";
        }

        net.minecraft.world.level.block.entity.BlockEntity blockEntity = null;
        if (!entityTypeId.isEmpty()
                && DeferredHolder.pumpkinResolve("minecraft:block_entity_type", entityTypeId)
                        instanceof BlockEntityType<?> type) {
            boolean existed = PumpkinBlockEntities.exists(x, y, z);
            blockEntity = PumpkinBlockEntities.getOrCreate(type, x, y, z, block.defaultBlockState());
            // A freshly built entity whose position has saved state gets that state back
            // before the mod's code sees it -- this is where persistence re-enters.
            // Loading runs even with nothing saved: absent keys are where mods apply
            // their defaults (Mekanism's side configs come from exactly that path).
            if (!existed) {
                com.google.gson.JsonObject parsed = savedData.isEmpty()
                        ? new com.google.gson.JsonObject()
                        : com.google.gson.JsonParser
                                .parseString(new String(java.util.Base64.getDecoder().decode(savedData),
                                        java.nio.charset.StandardCharsets.UTF_8))
                                .getAsJsonObject();
                Method load = findMethod(blockEntity.getClass(), "loadAdditional", 1);
                load.setAccessible(true);
                load.invoke(blockEntity, new PumpkinValueIO.Input(parsed));
            }
        }

        ItemStack held = buildStack(heldItemId, heldCount);
        PumpkinLevel level = LEVEL;
        level.pumpkinDrops().clear();
        PumpkinPlayer player = new PumpkinPlayer(held, x + 0.5, y + 1.0, z + 0.5);
        player.pumpkinSetSneaking(sneaking);
        if (!playerUuid.isEmpty()) {
            player.pumpkinSetUuid(java.util.UUID.fromString(playerUuid));
        }
        BlockPos pos = new BlockPos(x, y, z);
        BlockState state = block.defaultBlockState();
        BlockHitResult hit = new BlockHitResult(
                new Vec3(x + 0.5, y + 0.5, z + 0.5), Direction.UP, pos, false);

        Method method = findMethod(block.getClass(), "useItemOn", 7);
        method.setAccessible(true);
        Object result = method.invoke(block, held, state, level, pos, player,
                InteractionHand.MAIN_HAND, hit);
        // Vanilla fallthrough: TRY_WITH_EMPTY_HAND means "retry without the item" --
        // machine GUIs live in useWithoutItem.
        if (result instanceof InteractionResult.TryEmptyHandInteraction) {
            Method withoutItem = findMethod(block.getClass(), "useWithoutItem", 5);
            withoutItem.setAccessible(true);
            result = withoutItem.invoke(block, state, level, pos, player, hit);
        }

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

        reply.append(";SOUNDS=").append(String.join(",", level.pumpkinDrainSounds()));
        reply.append(";MENU=");
        if (player.pumpkinOpenedMenu() != null) {
            reply.append(player.pumpkinOpenedMenu());
            String[] parts = player.pumpkinOpenedMenu().split("\\|");
            PumpkinMenus.bindPosition(Integer.parseInt(parts[1]), x, y, z);
        }

        // Whatever the interaction did to the entity travels back as an opaque blob and
        // is saved inside Pumpkin's own block entity -- persistence's outbound half.
        reply.append(";DATA=");
        if (blockEntity != null) {
            PumpkinValueIO.Output output = new PumpkinValueIO.Output();
            Method save = findMethod(blockEntity.getClass(), "saveAdditional", 1);
            save.setAccessible(true);
            save.invoke(blockEntity, output);
            if (!output.isEmpty()) {
                reply.append(java.util.Base64.getEncoder().encodeToString(
                        output.pumpkinJson().toString()
                                .getBytes(java.nio.charset.StandardCharsets.UTF_8)));
            }
        }
        return reply.toString();
    }

    /** The registered id a stack's item answers to, or "unknown". */
    public static String pumpkinItemId(ItemStack stack) {
        Item item = stack.getItem();
        String id = item == null ? null : item.pumpkinRegisteredId();
        return id == null ? "unknown" : id;
    }

    /** A stack for a registered or vanilla item id -- the reverse of pumpkinItemId. */
    public static ItemStack pumpkinBuildStack(String itemId, int count) {
        return buildStack(itemId, count);
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
        // Interned: vanilla items are singletons, and mod recipe caches key on item
        // identity -- two stand-ins for one id must be the same object.
        return new ItemStack(VANILLA_STAND_INS.computeIfAbsent(itemId, id -> {
            Item item = new Item(new Item.Properties());
            item.pumpkinSetRegisteredId(id);
            return item;
        }), count);
    }

    private static final java.util.concurrent.ConcurrentHashMap<String, Item> VANILLA_STAND_INS =
            new java.util.concurrent.ConcurrentHashMap<>();

    /** The item's registered id and count, e.g. {@code mysticalagriculture:prosperity_shard:3}. */
    static String describe(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return "empty:0";
        }
        return pumpkinItemId(stack) + ":" + stack.count();
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

    /** Block names whose blocks answered "no ticker" -- skipped without reflection. */
    private static final java.util.Set<String> NO_TICKER =
            java.util.concurrent.ConcurrentHashMap.newKeySet();

    /**
     * One server tick of a mod block entity, through the mod's own {@code getTicker}.
     *
     * <p>Returns {@code "NONE"} for a block whose mod declines a ticker (remembered, so
     * later ticks cost one set lookup), or {@code "TICKED;DATA=..;DROPS=.."} -- DATA only
     * when the entity marked itself changed, because serialising an idle machine twenty
     * times a second buys nothing.
     */
    /**
     * A neighbor of the block changed: transmitters re-scan their sides, which is how
     * a tank placed after its tube becomes the network's acceptor. Vanilla tells block
     * entities per-side; the host does not know which side changed, and a full re-scan
     * answers the same question at six times the (tiny) cost.
     *
     * @return {@code OK}, or {@code NONE} when the entity has nothing to re-scan
     */
    public static String neighborChanged(String blockId, String entityTypeId, int x, int y, int z)
            throws Exception {
        Object typeObject = DeferredHolder.pumpkinResolve("minecraft:block_entity_type", entityTypeId);
        if (!(typeObject instanceof BlockEntityType<?> type) || !PumpkinBlockEntities.exists(x, y, z)) {
            return "NONE";
        }
        net.minecraft.world.level.block.entity.BlockEntity entity =
                PumpkinBlockEntities.getOrCreate(type, x, y, z);
        try {
            Object transmitter = entity.getClass().getMethod("getTransmitter").invoke(entity);
            transmitter.getClass().getMethod("refreshConnections").invoke(transmitter);
            return "OK";
        } catch (NoSuchMethodException notATransmitter) {
            return "NONE";
        }
    }

    /**
     * One server tick, as the NeoForge game bus tells it: mods that registered tick
     * handlers (Mekanism's transmitter networks, frequency manager) run here, once per
     * Pumpkin server tick.
     *
     * <p>The reply carries the container channels: a transmitter network moves items in
     * and out of vanilla containers here, outside any block's ticker, and this is the
     * only reply that reports it.
     *
     * @return {@code TICKED;EJECT=...;PULLED=...}
     */
    public static String tickServer() {
        net.minecraft.server.MinecraftServer server = PumpkinMinecraftServer.pumpkinInstance();
        PumpkinLevel level = PumpkinInteractions.pumpkinLevel();
        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(
                new net.neoforged.neoforge.event.tick.ServerTickEvent.Pre(() -> true, server));
        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(
                new net.neoforged.neoforge.event.tick.LevelTickEvent.Pre(() -> true, level));
        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(
                new net.neoforged.neoforge.event.tick.LevelTickEvent.Post(() -> true, level));
        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(
                new net.neoforged.neoforge.event.tick.ServerTickEvent.Post(() -> true, server));
        return pumpkinContainerReply("TICKED");
    }

    /**
     * One item pulled out of the machine by a hopper below, through the mod's own item
     * handler with the bottom-side context -- the mod's side configuration decides.
     *
     * @return {@code EXTRACTED;ITEM=id:1;DATA=blob}, or {@code NONE} when the mod
     *         declines (no handler, nothing stored, or the bottom side is not an output).
     */
    public static String extractBlock(String blockId, String entityTypeId, int x, int y, int z,
            String savedData) throws Exception {
        Object blockObject = DeferredHolder.pumpkinResolve("minecraft:block", blockId);
        Object typeObject = DeferredHolder.pumpkinResolve("minecraft:block_entity_type", entityTypeId);
        if (!(blockObject instanceof Block block)
                || !(typeObject instanceof BlockEntityType<?> type)) {
            return "NONE";
        }
        BlockState state = block.defaultBlockState();
        boolean existed = PumpkinBlockEntities.exists(x, y, z);
        net.minecraft.world.level.block.entity.BlockEntity entity =
                PumpkinBlockEntities.getOrCreate(type, x, y, z, state);
        if (!existed) {
            com.google.gson.JsonObject parsed = savedData.isEmpty()
                    ? new com.google.gson.JsonObject()
                    : com.google.gson.JsonParser
                            .parseString(new String(java.util.Base64.getDecoder().decode(savedData),
                                    java.nio.charset.StandardCharsets.UTF_8))
                            .getAsJsonObject();
            Method load = findMethod(entity.getClass(), "loadAdditional", 1);
            load.setAccessible(true);
            load.invoke(entity, new PumpkinValueIO.Input(parsed));
        }

        PumpkinLevel level = PumpkinInteractions.pumpkinLevel();
        net.neoforged.neoforge.transfer.ResourceHandler<net.neoforged.neoforge.transfer.item.ItemResource> handler =
                level.getCapability(net.neoforged.neoforge.capabilities.Capabilities.Item.BLOCK,
                        new BlockPos(x, y, z), state, entity, Direction.DOWN);
        if (handler == null) {
            return "NONE";
        }
        String extractedId = null;
        try (net.neoforged.neoforge.transfer.transaction.Transaction txn =
                net.neoforged.neoforge.transfer.transaction.Transaction.openRoot()) {
            for (int i = 0; i < handler.size() && extractedId == null; i++) {
                net.neoforged.neoforge.transfer.item.ItemResource resource = handler.getResource(i);
                if (resource == null || resource.isEmpty()) {
                    continue;
                }
                if (handler.extract(i, resource, 1, txn) == 1) {
                    extractedId = pumpkinItemId(resource.toStack(1));
                }
            }
            if (extractedId != null) {
                txn.commit();
            }
        }
        if (extractedId == null) {
            return "NONE";
        }
        StringBuilder reply = new StringBuilder("EXTRACTED;ITEM=").append(extractedId).append(":1");
        reply.append(";DATA=");
        PumpkinValueIO.Output output = new PumpkinValueIO.Output();
        Method save = findMethod(entity.getClass(), "saveAdditional", 1);
        save.setAccessible(true);
        save.invoke(entity, output);
        reply.append(java.util.Base64.getEncoder().encodeToString(
                output.pumpkinJson().toString().getBytes(java.nio.charset.StandardCharsets.UTF_8)));
        return reply.toString();
    }

    /**
     * The tick reply's container channels, drained whatever the ticked block turned out
     * to be.
     *
     * <p>They carry what mods moved in and out of neighbouring vanilla containers, and
     * that movement does not all happen inside a block's own ticker: Mekanism's
     * transmitters have no {@code BlockEntityTicker} at all -- their networks run on the
     * server tick event -- so a transporter's pull from a chest is finished before this
     * call arrives and would be thrown away if only ticking blocks reported it. Every
     * reply drains, including the "this block does not tick" ones.
     */
    private static String pumpkinContainerReply(String head) {
        return head
                + ";EJECT=" + String.join(",", PumpkinVanillaContainers.pumpkinDrainPushed())
                + ";PULLED=" + String.join(",", PumpkinVanillaContainers.pumpkinDrainPulled());
    }

    public static String tickBlock(String blockId, String entityTypeId, int x, int y, int z,
            String savedData, boolean hasSignal, double biomeTemperature, int containerMask,
            String containerContents) throws Exception {
        PumpkinLevel.pumpkinSetSignal(hasSignal);
        PumpkinLevel.pumpkinSetBiomeTemperature(biomeTemperature);
        PumpkinLevel.pumpkinSetContainerNeighbors(x, y, z, containerMask, containerContents);
        if (NO_TICKER.contains(blockId)) {
            return pumpkinContainerReply("NONE");
        }
        Object blockObject = DeferredHolder.pumpkinResolve("minecraft:block", blockId);
        Object typeObject = DeferredHolder.pumpkinResolve("minecraft:block_entity_type", entityTypeId);
        if (!(blockObject instanceof Block block)
                || !(typeObject instanceof BlockEntityType<?> type)) {
            NO_TICKER.add(blockId);
            return pumpkinContainerReply("NONE");
        }

        PumpkinLevel level = PumpkinInteractions.pumpkinLevel();
        BlockState state = block.defaultBlockState();
        Method getTicker = findMethod(block.getClass(), "getTicker", 3);
        getTicker.setAccessible(true);
        Object ticker = getTicker.invoke(block, level, state, type);
        if (ticker == null) {
            NO_TICKER.add(blockId);
            return pumpkinContainerReply("NONE");
        }

        boolean existed = PumpkinBlockEntities.exists(x, y, z);
        net.minecraft.world.level.block.entity.BlockEntity entity =
                PumpkinBlockEntities.getOrCreate(type, x, y, z, state);
        if (!existed) {
            com.google.gson.JsonObject parsed = savedData.isEmpty()
                    ? new com.google.gson.JsonObject()
                    : com.google.gson.JsonParser
                            .parseString(new String(java.util.Base64.getDecoder().decode(savedData),
                                    java.nio.charset.StandardCharsets.UTF_8))
                            .getAsJsonObject();
            Method load = findMethod(entity.getClass(), "loadAdditional", 1);
            load.setAccessible(true);
            load.invoke(entity, new PumpkinValueIO.Input(parsed));
        }
        level.pumpkinDrops().clear();
        @SuppressWarnings("unchecked")
        net.minecraft.world.level.block.entity.BlockEntityTicker<
                net.minecraft.world.level.block.entity.BlockEntity> cast =
                (net.minecraft.world.level.block.entity.BlockEntityTicker<
                        net.minecraft.world.level.block.entity.BlockEntity>) ticker;
        cast.tick(level, new BlockPos(x, y, z), state, entity);

        StringBuilder reply = new StringBuilder(pumpkinContainerReply("TICKED"));
        reply.append(";SOUNDS=").append(String.join(",", level.pumpkinDrainSounds()));
        reply.append(";DATA=");
        if (entity.pumpkinTakeChanged()) {
            PumpkinValueIO.Output output = new PumpkinValueIO.Output();
            Method save = findMethod(entity.getClass(), "saveAdditional", 1);
            save.setAccessible(true);
            save.invoke(entity, output);
            if (!output.isEmpty()) {
                reply.append(java.util.Base64.getEncoder().encodeToString(
                        output.pumpkinJson().toString()
                                .getBytes(java.nio.charset.StandardCharsets.UTF_8)));
            }
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

    static Method findMethod(Class<?> type, String name, int parameterCount)
            throws NoSuchMethodException {
        for (Class<?> current = type; current != null; current = current.getSuperclass()) {
            for (Method method : current.getDeclaredMethods()) {
                if (method.getName().equals(name) && method.getParameterCount() == parameterCount) {
                    return method;
                }
            }
        }
        // Interface default methods (EntityBlock.getTicker and kin) live on interfaces
        // the class walk above never visits.
        for (Method method : type.getMethods()) {
            if (method.getName().equals(name) && method.getParameterCount() == parameterCount) {
                return method;
            }
        }
        throw new NoSuchMethodException(type.getName() + " has no " + name
                + "(" + parameterCount + " args)");
    }
}
