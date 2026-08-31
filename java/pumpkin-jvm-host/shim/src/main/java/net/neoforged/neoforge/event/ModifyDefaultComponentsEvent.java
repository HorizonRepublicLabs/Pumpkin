package net.neoforged.neoforge.event;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public final class ModifyDefaultComponentsEvent extends Event implements IModBusEvent {

    public ModifyDefaultComponentsEvent(Map<Item, Initializer> modifiersByItem, List<Pair<ItemWithComponentsPredicate, Initializer>> modifiersByPredicate) {
    }

    public void modify(ItemLike item, Initializer patch) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/ModifyDefaultComponentsEvent.modify:(Lnet/minecraft/world/level/ItemLike;Lnet/neoforged/neoforge/event/ModifyDefaultComponentsEvent$Initializer;)V");
    }

    public void modify(ItemLike item, Consumer<DataComponentMap.Builder> patch) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/ModifyDefaultComponentsEvent.modify:(Lnet/minecraft/world/level/ItemLike;Ljava/util/function/Consumer;)V");
    }

    public interface ItemWithComponentsPredicate {

        boolean test(Item item, DataComponentGetter components);
    }

    public interface Initializer {

        void run(DataComponentMap.Builder components, HolderLookup.Provider context, Item item);
    }

    public ModifyDefaultComponentsEvent() {
    }
}
