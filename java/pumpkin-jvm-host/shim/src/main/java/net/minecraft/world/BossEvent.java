package net.minecraft.world;

import java.util.UUID;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public abstract class BossEvent {

    public BossEvent(UUID id, Component name, BossEvent.BossBarColor color, BossEvent.BossBarOverlay overlay) {
    }

    public UUID getId() {
        throw Unimplemented.forMember("net/minecraft/world/BossEvent.getId:()Ljava/util/UUID;");
    }

    public Component getName() {
        throw Unimplemented.forMember("net/minecraft/world/BossEvent.getName:()Lnet/minecraft/network/chat/Component;");
    }

    public BossEvent.BossBarColor getColor() {
        throw Unimplemented.forMember("net/minecraft/world/BossEvent.getColor:()Lnet/minecraft/world/BossEvent$BossBarColor;");
    }

    public void setColor(BossEvent.BossBarColor color) {
        throw Unimplemented.forMember("net/minecraft/world/BossEvent.setColor:(Lnet/minecraft/world/BossEvent$BossBarColor;)V");
    }

    public enum BossBarColor implements StringRepresentable {

        PINK,
        BLUE,
        RED,
        GREEN,
        YELLOW,
        PURPLE,
        WHITE;

        public String getName() {
            throw Unimplemented.forMember("net/minecraft/world/BossEvent$BossBarColor.getName:()Ljava/lang/String;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/BossEvent$BossBarColor.getSerializedName:()Ljava/lang/String;");
        }
    }

    public enum BossBarOverlay implements StringRepresentable {

        PROGRESS, NOTCHED_6, NOTCHED_10, NOTCHED_12, NOTCHED_20;

        public String getName() {
            throw Unimplemented.forMember("net/minecraft/world/BossEvent$BossBarOverlay.getName:()Ljava/lang/String;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/BossEvent$BossBarOverlay.getSerializedName:()Ljava/lang/String;");
        }
    }

    public BossEvent() {
    }
}
