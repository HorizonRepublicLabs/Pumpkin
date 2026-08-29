package net.minecraft.network.chat;

import java.util.Optional;
import java.util.UUID;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStackTemplate;
import dev.pumpkin.shim.Unimplemented;

public interface HoverEvent {

    HoverEvent.Action action();

    enum Action implements StringRepresentable {

        SHOW_TEXT, SHOW_ITEM, SHOW_ENTITY;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/network/chat/HoverEvent$Action.getSerializedName:()Ljava/lang/String;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/network/chat/HoverEvent$Action.toString:()Ljava/lang/String;");
        }
    }

    class EntityTooltipInfo {

        public EntityTooltipInfo(EntityType<?> type, UUID uuid, Component name) {
            throw Unimplemented.forMember("net/minecraft/network/chat/HoverEvent$EntityTooltipInfo.<init>:(Lnet/minecraft/world/entity/EntityType;Ljava/util/UUID;Lnet/minecraft/network/chat/Component;)V");
        }

        public EntityTooltipInfo(EntityType<?> type, UUID uuid, Optional<Component> name) {
            throw Unimplemented.forMember("net/minecraft/network/chat/HoverEvent$EntityTooltipInfo.<init>:(Lnet/minecraft/world/entity/EntityType;Ljava/util/UUID;Ljava/util/Optional;)V");
        }

        public boolean equals(Object o) {
            throw Unimplemented.forMember("net/minecraft/network/chat/HoverEvent$EntityTooltipInfo.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("net/minecraft/network/chat/HoverEvent$EntityTooltipInfo.hashCode:()I");
        }

        protected EntityTooltipInfo() {
        }
    }

    record ShowEntity(HoverEvent.EntityTooltipInfo entity) implements HoverEvent {

        public HoverEvent.Action action() {
            throw Unimplemented.forMember("net/minecraft/network/chat/HoverEvent$ShowEntity.action:()Lnet/minecraft/network/chat/HoverEvent$Action;");
        }
    }

    record ShowItem(ItemStackTemplate item) implements HoverEvent {

        public HoverEvent.Action action() {
            throw Unimplemented.forMember("net/minecraft/network/chat/HoverEvent$ShowItem.action:()Lnet/minecraft/network/chat/HoverEvent$Action;");
        }
    }

    record ShowText(Component value) implements HoverEvent {

        public HoverEvent.Action action() {
            throw Unimplemented.forMember("net/minecraft/network/chat/HoverEvent$ShowText.action:()Lnet/minecraft/network/chat/HoverEvent$Action;");
        }
    }
}
