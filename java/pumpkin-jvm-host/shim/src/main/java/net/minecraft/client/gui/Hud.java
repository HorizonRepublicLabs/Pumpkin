package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.SubtitleOverlay;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import static net.neoforged.neoforge.client.gui.VanillaGuiLayers.*;
import dev.pumpkin.shim.Unimplemented;

public class Hud {

    private boolean isHidden;

    private int overlayMessageTime;

    private final SubtitleOverlay subtitleOverlay = null;

    public int leftHeight;

    public int rightHeight;

    public Hud(Minecraft minecraft) {
    }

    public boolean isHidden() {
        throw Unimplemented.forMember("net/minecraft/client/gui/Hud.isHidden:()Z");
    }

    private void tick() {
        throw Unimplemented.forMember("net/minecraft/client/gui/Hud.tick:()V");
    }

    public int getGuiTicks() {
        throw Unimplemented.forMember("net/minecraft/client/gui/Hud.getGuiTicks:()I");
    }

    public Font getFont() {
        throw Unimplemented.forMember("net/minecraft/client/gui/Hud.getFont:()Lnet/minecraft/client/gui/Font;");
    }

    private enum ContextualInfo {

        EMPTY, EXPERIENCE, LOCATOR, JUMPABLE_VEHICLE
    }

    public enum HeartType implements IExtensibleEnum {

        CONTAINER,
        NORMAL,
        POISIONED,
        WITHERED,
        ABSORBING,
        FROZEN
    }

    public Hud() {
    }
}
