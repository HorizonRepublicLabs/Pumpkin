package net.minecraft.advancements;

import java.util.Optional;
import net.minecraft.core.ClientAsset;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStackTemplate;
import dev.pumpkin.shim.Unimplemented;

public class DisplayInfo {

    public DisplayInfo(ItemStackTemplate icon, Component title, Component description, Optional<ClientAsset.ResourceTexture> background, AdvancementType type, boolean showToast, boolean announceChat, boolean hidden) {
    }

    public Component getDescription() {
        throw Unimplemented.forMember("net/minecraft/advancements/DisplayInfo.getDescription:()Lnet/minecraft/network/chat/Component;");
    }

    public AdvancementType getType() {
        throw Unimplemented.forMember("net/minecraft/advancements/DisplayInfo.getType:()Lnet/minecraft/advancements/AdvancementType;");
    }

    public float getX() {
        throw Unimplemented.forMember("net/minecraft/advancements/DisplayInfo.getX:()F");
    }

    public float getY() {
        throw Unimplemented.forMember("net/minecraft/advancements/DisplayInfo.getY:()F");
    }

    public DisplayInfo() {
    }
}
