package net.neoforged.neoforge.client.event;

import java.util.List;
import java.util.Map;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.client.IItemDecorator;
import dev.pumpkin.shim.Unimplemented;

public class RegisterItemDecorationsEvent extends Event implements IModBusEvent {

    public RegisterItemDecorationsEvent(Map<Item, List<IItemDecorator>> decorators) {
    }

    public void register(ItemLike itemLike, IItemDecorator decorator) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterItemDecorationsEvent.register:(Lnet/minecraft/world/level/ItemLike;Lnet/neoforged/neoforge/client/IItemDecorator;)V");
    }

    public RegisterItemDecorationsEvent() {
    }
}
