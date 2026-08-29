package net.neoforged.neoforge.common.extensions;

import java.util.OptionalInt;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.MenuProvider;
import dev.pumpkin.shim.Unimplemented;

public interface IPlayerExtension {

    default OptionalInt openMenu(MenuProvider menuProvider, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IPlayerExtension.openMenu:(Lnet/minecraft/world/MenuProvider;Lnet/minecraft/core/BlockPos;)Ljava/util/OptionalInt;");
    }

    default OptionalInt openMenu(MenuProvider menuProvider, Consumer<RegistryFriendlyByteBuf> extraDataWriter) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IPlayerExtension.openMenu:(Lnet/minecraft/world/MenuProvider;Ljava/util/function/Consumer;)Ljava/util/OptionalInt;");
    }
}
