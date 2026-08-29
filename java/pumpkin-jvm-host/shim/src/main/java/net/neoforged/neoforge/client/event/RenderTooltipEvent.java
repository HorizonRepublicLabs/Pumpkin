package net.neoforged.neoforge.client.event;

import com.mojang.datafixers.util.Either;
import java.util.List;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class RenderTooltipEvent extends Event {

    protected RenderTooltipEvent(ItemStack itemStack, GuiGraphicsExtractor graphics, int x, int y, Font font, List<ClientTooltipComponent> components) {
    }

    public List<ClientTooltipComponent> getComponents() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderTooltipEvent.getComponents:()Ljava/util/List;");
    }

    public int getX() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderTooltipEvent.getX:()I");
    }

    public int getY() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderTooltipEvent.getY:()I");
    }

    public static class GatherComponents extends Event implements ICancellableEvent {

        public GatherComponents(ItemStack itemStack, int screenWidth, int screenHeight, List<Either<FormattedText, TooltipComponent>> tooltipElements, int maxWidth) {
        }

        public ItemStack getItemStack() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderTooltipEvent$GatherComponents.getItemStack:()Lnet/minecraft/world/item/ItemStack;");
        }

        public List<Either<FormattedText, TooltipComponent>> getTooltipElements() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderTooltipEvent$GatherComponents.getTooltipElements:()Ljava/util/List;");
        }

        public GatherComponents() {
        }
    }

    public static class Pre extends RenderTooltipEvent implements ICancellableEvent {

        public Pre(ItemStack stack, GuiGraphicsExtractor graphics, int x, int y, int screenWidth, int screenHeight, Font font, List<ClientTooltipComponent> components, ClientTooltipPositioner positioner) {
        }

        public void setX(int x) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderTooltipEvent$Pre.setX:(I)V");
        }

        public void setY(int y) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderTooltipEvent$Pre.setY:(I)V");
        }

        public Pre() {
        }
    }

    public static class Texture extends RenderTooltipEvent {

        public Texture(ItemStack stack, GuiGraphicsExtractor graphics, int x, int y, Font font, List<ClientTooltipComponent> components, Identifier texture) {
        }

        public Texture() {
        }
    }

    public RenderTooltipEvent() {
    }
}
