package net.minecraft.network.chat;

import com.mojang.serialization.MapCodec;
import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.dialog.Dialog;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public interface ClickEvent {

    ClickEvent.Action action();

    enum Action implements StringRepresentable {

        OPEN_URL,
        OPEN_FILE,
        RUN_COMMAND,
        SUGGEST_COMMAND,
        SHOW_DIALOG,
        CHANGE_PAGE,
        COPY_TO_CLIPBOARD,
        CUSTOM;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ClickEvent$Action.getSerializedName:()Ljava/lang/String;");
        }

        public MapCodec<? extends ClickEvent> valueCodec() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ClickEvent$Action.valueCodec:()Lcom/mojang/serialization/MapCodec;");
        }
    }

    record ChangePage(int page) implements ClickEvent {

        public ClickEvent.Action action() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ClickEvent$ChangePage.action:()Lnet/minecraft/network/chat/ClickEvent$Action;");
        }
    }

    record CopyToClipboard(String value) implements ClickEvent {

        public ClickEvent.Action action() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ClickEvent$CopyToClipboard.action:()Lnet/minecraft/network/chat/ClickEvent$Action;");
        }
    }

    record Custom(Identifier id, Optional<Tag> payload) implements ClickEvent {

        public ClickEvent.Action action() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ClickEvent$Custom.action:()Lnet/minecraft/network/chat/ClickEvent$Action;");
        }
    }

    record OpenFile(String path) implements ClickEvent {

        public OpenFile(File file) {
            this((String) null);
        }

        public OpenFile(Path path) {
            this((String) null);
        }

        public ClickEvent.Action action() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ClickEvent$OpenFile.action:()Lnet/minecraft/network/chat/ClickEvent$Action;");
        }
    }

    record OpenUrl(URI uri) implements ClickEvent {

        public ClickEvent.Action action() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ClickEvent$OpenUrl.action:()Lnet/minecraft/network/chat/ClickEvent$Action;");
        }
    }

    record RunCommand(String command) implements ClickEvent {

        public ClickEvent.Action action() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ClickEvent$RunCommand.action:()Lnet/minecraft/network/chat/ClickEvent$Action;");
        }
    }

    record ShowDialog(Holder<Dialog> dialog) implements ClickEvent {

        public ClickEvent.Action action() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ClickEvent$ShowDialog.action:()Lnet/minecraft/network/chat/ClickEvent$Action;");
        }
    }

    record SuggestCommand(String command) implements ClickEvent {

        public ClickEvent.Action action() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ClickEvent$SuggestCommand.action:()Lnet/minecraft/network/chat/ClickEvent$Action;");
        }
    }
}
