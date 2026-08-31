package net.neoforged.neoforge.client.gui.modlist;

import java.net.URI;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.ModContainer;
import dev.pumpkin.shim.Unimplemented;

public class DefaultModDisplayInfo implements ModDisplayInfo {

    private final ModContainer container = null;

    public DefaultModDisplayInfo(ModContainer container) {
    }

    public String id() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.id:()Ljava/lang/String;");
    }

    public Component displayName() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.displayName:()Lnet/minecraft/network/chat/Component;");
    }

    public String version() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.version:()Ljava/lang/String;");
    }

    public Component authors() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.authors:()Lnet/minecraft/network/chat/Component;");
    }

    public Component credits() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.credits:()Lnet/minecraft/network/chat/Component;");
    }

    public Component description() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.description:()Lnet/minecraft/network/chat/Component;");
    }

    public Component license() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.license:()Lnet/minecraft/network/chat/Component;");
    }

    public ImageResource banner() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.banner:()Lnet/neoforged/neoforge/client/gui/modlist/ImageResource;");
    }

    public ImageResource icon() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.icon:()Lnet/neoforged/neoforge/client/gui/modlist/ImageResource;");
    }

    public boolean iconBlur() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.iconBlur:()Z");
    }

    public URI displayUrl() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.displayUrl:()Ljava/net/URI;");
    }

    public URI issuesUrl() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.issuesUrl:()Ljava/net/URI;");
    }

    public ModContainer container() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.container:()Lnet/neoforged/fml/ModContainer;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/DefaultModDisplayInfo.toString:()Ljava/lang/String;");
    }

    public DefaultModDisplayInfo() {
    }
}
