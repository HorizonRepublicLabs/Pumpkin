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
    }
}
