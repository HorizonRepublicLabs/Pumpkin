package net.minecraft.client.data.models;

import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.world.item.Item;
import dev.pumpkin.shim.Unimplemented;

public interface ItemModelOutput {

    default void accept(Item item, ItemModel.Unbaked generator) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/ItemModelOutput.accept:(Lnet/minecraft/world/item/Item;Lnet/minecraft/client/renderer/item/ItemModel$Unbaked;)V");
    }

    void accept(Item item, ItemModel.Unbaked generator, ClientItem.Properties properties);

    void copy(Item donor, Item acceptor);

    default void register(Item item, net.minecraft.client.renderer.item.ClientItem clientItem) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/ItemModelOutput.register:(Lnet/minecraft/world/item/Item;Lnet/minecraft/client/renderer/item/ClientItem;)V");
    }

    default void register(net.minecraft.resources.Identifier identifier, net.minecraft.client.renderer.item.ClientItem clientItem) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/ItemModelOutput.register:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/client/renderer/item/ClientItem;)V");
    }
}
