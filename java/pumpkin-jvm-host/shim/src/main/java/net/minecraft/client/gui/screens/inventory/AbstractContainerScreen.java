package net.minecraft.client.gui.screens.inventory;

import java.util.Set;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractContainerScreen<T extends AbstractContainerMenu> extends Screen implements MenuAccess<T> {

    private static final Identifier SLOT_HIGHLIGHT_BACK_SPRITE = null;

    private static final Identifier SLOT_HIGHLIGHT_FRONT_SPRITE = null;

    protected final int imageWidth = 0;

    protected final int imageHeight = 0;

    protected int titleLabelY;

    protected int inventoryLabelX;

    protected int inventoryLabelY;

    protected final T menu = null;

    protected final Component playerInventoryTitle = Stubs.of(Component.class, "net/minecraft/network/chat/Component");

    protected Slot hoveredSlot;

    private Slot lastClickSlot;

    protected int leftPos;

    protected int topPos;

    protected final Set<Slot> quickCraftSlots = null;

    protected boolean isQuickCrafting;

    private int quickCraftingType;

    private int quickCraftingButton;

    private boolean skipNextRelease;

    private boolean doubleclick;

    private ItemStack lastQuickMoved;

    public AbstractContainerScreen(T menu, Inventory inventory, Component title) {
    }

    public AbstractContainerScreen(T menu, Inventory inventory, Component title, int imageWidth, int imageHeight) {
    }

    protected void init() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.init:()V");
    }

    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.extractRenderState:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
    }

    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.extractContents:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
    }

    public boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.mouseScrolled:(DDDD)Z");
    }

    private void extractSlotHighlightBack(GuiGraphicsExtractor graphics) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.extractSlotHighlightBack:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;)V");
    }

    private void extractSlotHighlightFront(GuiGraphicsExtractor graphics) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.extractSlotHighlightFront:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;)V");
    }

    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.extractTooltip:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;II)V");
    }

    protected void extractSlot(GuiGraphicsExtractor graphics, Slot slot, int mouseX, int mouseY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.extractSlot:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/world/inventory/Slot;II)V");
    }

    protected void renderSlotContents(GuiGraphicsExtractor graphics, ItemStack itemStack, Slot slot, String itemCount) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.renderSlotContents:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/inventory/Slot;Ljava/lang/String;)V");
    }

    private void recalculateQuickCraftRemaining() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.recalculateQuickCraftRemaining:()V");
    }

    private Slot getHoveredSlot(double x, double y) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.getHoveredSlot:(DD)Lnet/minecraft/world/inventory/Slot;");
    }

    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.mouseClicked:(Lnet/minecraft/client/input/MouseButtonEvent;Z)Z");
    }

    private void checkHotbarMouseClicked(MouseButtonEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.checkHotbarMouseClicked:(Lnet/minecraft/client/input/MouseButtonEvent;)V");
    }

    protected boolean hasClickedOutside(double mx, double my, int xo, int yo) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.hasClickedOutside:(DDII)Z");
    }

    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.mouseDragged:(Lnet/minecraft/client/input/MouseButtonEvent;DD)Z");
    }

    public boolean mouseReleased(MouseButtonEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.mouseReleased:(Lnet/minecraft/client/input/MouseButtonEvent;)Z");
    }

    private boolean isHovering(Slot slot, double xm, double ym) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.isHovering:(Lnet/minecraft/world/inventory/Slot;DD)Z");
    }

    protected boolean isHovering(int left, int top, int w, int h, double xm, double ym) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.isHovering:(IIIIDD)Z");
    }

    protected void slotClicked(Slot slot, int slotId, int buttonNum, ContainerInput containerInput) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.slotClicked:(Lnet/minecraft/world/inventory/Slot;IILnet/minecraft/world/inventory/ContainerInput;)V");
    }

    public boolean keyPressed(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.keyPressed:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    public void removed() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.removed:()V");
    }

    public boolean isPauseScreen() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.isPauseScreen:()Z");
    }

    public boolean isInGameUi() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.isInGameUi:()Z");
    }

    public final void tick() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.tick:()V");
    }

    protected void containerTick() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.containerTick:()V");
    }

    public T getMenu() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.getMenu:()Lnet/minecraft/world/inventory/AbstractContainerMenu;");
    }

    public Slot getHoveredSlot() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.getHoveredSlot:()Lnet/minecraft/world/inventory/Slot;");
    }

    public int getLeftPos() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.getLeftPos:()I");
    }

    public int getTopPos() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.getTopPos:()I");
    }

    public int getImageWidth() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.getImageWidth:()I");
    }

    public int getImageHeight() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.getImageHeight:()I");
    }

    public void onClose() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.onClose:()V");
    }

    public AbstractContainerScreen() {
    }
}
