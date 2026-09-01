package dev.pumpkin.bridge;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * The mod menus a player has open.
 *
 * <p>When a machine's block calls {@code player.openMenu}, the mod's own
 * {@code AbstractContainerMenu} subclass is constructed here and kept by window id; the
 * server tells the client to open the matching screen and asks this class for slot
 * contents. Window ids are this side's to assign -- the Rust side uses them verbatim, so
 * the two never disagree about which menu a click belongs to.
 */
public final class PumpkinMenus {
    private PumpkinMenus() {
    }

    private static final AtomicInteger NEXT_WINDOW_ID = new AtomicInteger(100);

    private static final Map<Integer, AbstractContainerMenu> OPEN = new ConcurrentHashMap<>();

    private static final Map<Integer, PumpkinPlayer> PLAYERS = new ConcurrentHashMap<>();

    private static final Map<Integer, int[]> POSITIONS = new ConcurrentHashMap<>();

    /** Slots collected by an in-progress drag, per window. */
    private static final Map<Integer, java.util.List<Integer>> DRAGS = new ConcurrentHashMap<>();

    /** Items thrown out of the menu by the last click, drained into the reply. */
    private static final Map<Integer, java.util.List<ItemStack>> THROWN = new ConcurrentHashMap<>();

    /** Remembers which stand-in player opened a window; the click path hydrates it. */
    static void bindPlayer(int windowId, PumpkinPlayer player) {
        PLAYERS.put(windowId, player);
    }

    /** Remembers which block a window belongs to, so clicks can persist its entity. */
    public static void bindPosition(int windowId, int x, int y, int z) {
        POSITIONS.put(windowId, new int[] {x, y, z});
    }

    /** A fresh window id, cycling well clear of the ids vanilla screens use. */
    static int nextWindowId() {
        return 100 + (NEXT_WINDOW_ID.getAndIncrement() - 100) % 900;
    }

    static void register(int windowId, AbstractContainerMenu menu) {
        OPEN.put(windowId, menu);
    }

    /** The registered name of the menu's type, or null when it never registered. */
    static String typeName(AbstractContainerMenu menu) {
        return DeferredHolder.pumpkinResolveName("minecraft:menu", menu.getType());
    }

    /**
     * The menu's slots as {@code id:count} entries, comma-joined, in slot-index order.
     * Empty string for an unknown window.
     */
    public static String slotContents(int windowId) {
        AbstractContainerMenu menu = OPEN.get(windowId);
        if (menu == null) {
            return "";
        }
        StringBuilder reply = new StringBuilder();
        for (Slot slot : menu.slots) {
            if (reply.length() > 0) {
                reply.append(',');
            }
            ItemStack stack = slot.getItem();
            reply.append(stack == null || stack.isEmpty() ? "empty:0"
                    : PumpkinInteractions.pumpkinItemId(stack) + ":" + stack.count());
        }
        return reply.toString();
    }

    /** Forgets a closed window. */
    public static void close(int windowId) {
        OPEN.remove(windowId);
        PLAYERS.remove(windowId);
        POSITIONS.remove(windowId);
    }

    /**
     * One click, run against the mod's own menu.
     *
     * <p>Mode 0 is pickup (vanilla's carried-stack swap, written here because the shim
     * has no clicked()); mode 1 is quick-move, which calls the menu's own
     * {@code quickMoveStack} -- the mod's logic. Other modes refuse with a key. The
     * player's inventory is hydrated from {@code playerInv} ({@code slot:id:count}
     * entries) before the click, and the reply carries every slot, the carried stack and
     * the machine's save blob, so the server can show and persist what happened.
     */
    public static String click(int windowId, int slotIndex, int button, int mode,
            String playerInv) throws Exception {
        AbstractContainerMenu menu = OPEN.get(windowId);
        PumpkinPlayer player = PLAYERS.get(windowId);
        if (menu == null || player == null) {
            return "GONE";
        }

        for (String entry : playerInv.split(",")) {
            if (entry.isEmpty()) {
                continue;
            }
            // slot:namespace:path:count
            int firstColon = entry.indexOf(':');
            int lastColon = entry.lastIndexOf(':');
            int slot = Integer.parseInt(entry.substring(0, firstColon));
            String id = entry.substring(firstColon + 1, lastColon);
            int count = Integer.parseInt(entry.substring(lastColon + 1));
            player.getInventory().setItem(slot,
                    count <= 0 ? ItemStack.EMPTY
                            : PumpkinInteractions.pumpkinBuildStack(id, count));
        }

        if (mode == 1) {
            // Quick move: the mod's own logic, looped like vanilla until it stops moving.
            menu.quickMoveStack(player, slotIndex);
        } else if (mode == 0 && slotIndex >= 0 && slotIndex < menu.slots.size()) {
            Slot slot = menu.slots.get(slotIndex);
            ItemStack carried = menu.getCarried();
            ItemStack inSlot = slot.getItem();
            if (carried.isEmpty()) {
                if (!inSlot.isEmpty() && slot.mayPickup(player)) {
                    int taking = button == 1 ? (inSlot.count() + 1) / 2 : inSlot.count();
                    menu.setCarried(slot.remove(taking));
                    slot.onTake(player, menu.getCarried());
                }
            } else if (inSlot.isEmpty()) {
                if (slot.mayPlace(carried)) {
                    int placing = button == 1 ? 1 : carried.count();
                    placing = Math.min(placing, slot.getMaxStackSize(carried));
                    slot.set(carried.copyWithCount(placing));
                    menu.setCarried(carried.count() == placing ? ItemStack.EMPTY
                            : carried.copyWithCount(carried.count() - placing));
                }
            } else if (inSlot.getItem() == carried.getItem() && slot.mayPlace(carried)) {
                int placing = button == 1 ? 1 : carried.count();
                int room = slot.getMaxStackSize(carried) - inSlot.count();
                placing = Math.min(placing, room);
                if (placing > 0) {
                    slot.set(inSlot.copyWithCount(inSlot.count() + placing));
                    menu.setCarried(carried.count() == placing ? ItemStack.EMPTY
                            : carried.copyWithCount(carried.count() - placing));
                }
            } else if (slot.mayPlace(carried) && slot.mayPickup(player)) {
                // Different items: swap.
                slot.set(carried);
                menu.setCarried(inSlot);
            }
        } else if (mode == 2 && slotIndex >= 0 && slotIndex < menu.slots.size()) {
            // Swap: exchange the clicked slot with a hotbar slot (button 0-8) or the
            // offhand (button 40). Both directions respect the slots' own rules.
            int targetInv = button == 40 ? 40 : button;
            Slot target = null;
            for (Slot candidate : menu.slots) {
                if (candidate.container == player.getInventory()
                        && candidate.pumpkinContainerSlot() == targetInv) {
                    target = candidate;
                    break;
                }
            }
            Slot clicked = menu.slots.get(slotIndex);
            if (target != null && target != clicked) {
                ItemStack a = clicked.getItem();
                ItemStack b = target.getItem();
                if ((a.isEmpty() || target.mayPlace(a))
                        && (b.isEmpty() || clicked.mayPlace(b))
                        && clicked.mayPickup(player) && target.mayPickup(player)) {
                    clicked.set(b);
                    target.set(a);
                }
            }
        } else if (mode == 3 && slotIndex >= 0 && slotIndex < menu.slots.size()) {
            // Clone (creative middle-click): a full copy onto an empty cursor. Gamemode
            // gating lives on the client for this one; a survival client never sends it.
            if (menu.getCarried().isEmpty()) {
                ItemStack inSlot = menu.slots.get(slotIndex).getItem();
                if (!inSlot.isEmpty()) {
                    menu.setCarried(inSlot.copyWithCount(64));
                }
            }
        } else if (mode == 4) {
            // Throw: Q over a slot (button 0 = one, 1 = the stack); the thrown stack
            // rides the reply into the real world as a drop.
            if (slotIndex >= 0 && slotIndex < menu.slots.size()) {
                Slot slot = menu.slots.get(slotIndex);
                ItemStack inSlot = slot.getItem();
                if (!inSlot.isEmpty() && slot.mayPickup(player)) {
                    int throwing = button == 1 ? inSlot.count() : 1;
                    ItemStack thrown = slot.remove(throwing);
                    THROWN.computeIfAbsent(windowId, ignored -> new java.util.ArrayList<>())
                            .add(thrown);
                }
            } else if (slotIndex == -999 && !menu.getCarried().isEmpty()) {
                ItemStack carried2 = menu.getCarried();
                int throwing = button == 1 ? carried2.count() : 1;
                THROWN.computeIfAbsent(windowId, ignored -> new java.util.ArrayList<>())
                        .add(carried2.copyWithCount(throwing));
                menu.setCarried(carried2.count() <= throwing ? ItemStack.EMPTY
                        : carried2.copyWithCount(carried2.count() - throwing));
            }
        } else if (mode == 5) {
            // Drag: start (button 0/4/8) collects nothing, add (1/5/9) collects slots,
            // end (2/6/10) distributes the carried stack -- evenly for a left drag, one
            // each for a right drag.
            int stage = button & 3;
            boolean single = (button & 4) != 0;
            if (stage == 0) {
                DRAGS.put(windowId, new java.util.ArrayList<>());
            } else if (stage == 1) {
                java.util.List<Integer> drag = DRAGS.get(windowId);
                if (drag != null && slotIndex >= 0 && slotIndex < menu.slots.size()) {
                    drag.add(slotIndex);
                }
            } else if (stage == 2) {
                java.util.List<Integer> drag = DRAGS.remove(windowId);
                ItemStack carried2 = menu.getCarried();
                if (drag != null && !drag.isEmpty() && !carried2.isEmpty()) {
                    int per = single ? 1 : carried2.count() / drag.size();
                    int remaining = carried2.count();
                    for (int index : drag) {
                        if (per <= 0 || remaining <= 0) {
                            break;
                        }
                        Slot slot = menu.slots.get(index);
                        ItemStack inSlot = slot.getItem();
                        if (!slot.mayPlace(carried2)) {
                            continue;
                        }
                        if (inSlot.isEmpty()) {
                            int placing = Math.min(per, slot.getMaxStackSize(carried2));
                            slot.set(carried2.copyWithCount(placing));
                            remaining -= placing;
                        } else if (inSlot.getItem() == carried2.getItem()) {
                            int room = slot.getMaxStackSize(carried2) - inSlot.count();
                            int placing = Math.min(per, room);
                            if (placing > 0) {
                                slot.set(inSlot.copyWithCount(inSlot.count() + placing));
                                remaining -= placing;
                            }
                        }
                    }
                    menu.setCarried(remaining <= 0 ? ItemStack.EMPTY
                            : carried2.copyWithCount(remaining));
                }
            }
        } else if (mode == 6) {
            // Pickup-all (double-click): sweep every matching stack onto the cursor.
            ItemStack carried2 = menu.getCarried();
            if (!carried2.isEmpty()) {
                int total = carried2.count();
                for (Slot slot : menu.slots) {
                    if (total >= 64) {
                        break;
                    }
                    ItemStack inSlot = slot.getItem();
                    if (!inSlot.isEmpty() && inSlot.getItem() == carried2.getItem()
                            && slot.mayPickup(player)) {
                        int taking = Math.min(inSlot.count(), 64 - total);
                        slot.remove(taking);
                        total += taking;
                    }
                }
                menu.setCarried(carried2.copyWithCount(total));
            }
        } else {
            throw dev.pumpkin.shim.Unimplemented.forMember(
                    "net/minecraft/world/inventory/AbstractContainerMenu.clicked (mode " + mode + ")");
        }

        StringBuilder reply = new StringBuilder("CLICKED");
        reply.append(";SLOTS=").append(slotContents(windowId));
        ItemStack carried = menu.getCarried();
        reply.append(";CARRIED=").append(carried == null || carried.isEmpty() ? "empty:0"
                : PumpkinInteractions.pumpkinItemId(carried) + ":" + carried.count());
        reply.append(";PLAYERINV=");
        var stacks = player.getInventory().pumpkinItems();
        for (int i = 0; i < 36; i++) {
            if (i > 0) {
                reply.append(',');
            }
            ItemStack stack = stacks.get(i);
            reply.append(stack == null || stack.isEmpty() ? "empty:0"
                    : PumpkinInteractions.pumpkinItemId(stack) + ":" + stack.count());
        }
        int[] pos = POSITIONS.get(windowId);
        reply.append(";POS=").append(pos == null ? ""
                : pos[0] + "," + pos[1] + "," + pos[2]);
        reply.append(";DROPS=");
        java.util.List<ItemStack> thrown = THROWN.remove(windowId);
        if (thrown != null) {
            boolean first = true;
            for (ItemStack stack : thrown) {
                if (!first) {
                    reply.append(',');
                }
                reply.append(PumpkinInteractions.pumpkinItemId(stack) + ":" + stack.count());
                first = false;
            }
        }
        reply.append(";DATA=");
        if (pos != null) {
            var entity = PumpkinBlockEntities.get(pos[0], pos[1], pos[2]);
            if (entity != null) {
                PumpkinValueIO.Output output = new PumpkinValueIO.Output();
                java.lang.reflect.Method save = null;
                for (Class<?> current = entity.getClass(); current != null;
                        current = current.getSuperclass()) {
                    for (java.lang.reflect.Method method : current.getDeclaredMethods()) {
                        if (method.getName().equals("saveAdditional")
                                && method.getParameterCount() == 1) {
                            save = method;
                            break;
                        }
                    }
                    if (save != null) {
                        break;
                    }
                }
                if (save != null) {
                    save.setAccessible(true);
                    save.invoke(entity, output);
                    if (!output.isEmpty()) {
                        reply.append(java.util.Base64.getEncoder().encodeToString(
                                output.pumpkinJson().toString()
                                        .getBytes(java.nio.charset.StandardCharsets.UTF_8)));
                    }
                }
            }
        }
        return reply.toString();
    }
}
