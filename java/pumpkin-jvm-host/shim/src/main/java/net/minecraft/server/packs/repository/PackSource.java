package net.minecraft.server.packs.repository;

import java.util.function.UnaryOperator;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public interface PackSource {

    Component decorate(final Component packDescription);

    boolean shouldAddAutomatically();

    static PackSource create(UnaryOperator<Component> decorator, boolean addAutomatically) {
        throw Unimplemented.forMember("net/minecraft/server/packs/repository/PackSource.create:(Ljava/util/function/UnaryOperator;Z)Lnet/minecraft/server/packs/repository/PackSource;");
    }
}
