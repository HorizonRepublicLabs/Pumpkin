package net.neoforged.neoforge.common.util;

import java.util.function.Consumer;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import dev.pumpkin.shim.Unimplemented;

public class FriendlyByteBufUtil {

    protected FriendlyByteBufUtil() {
    }

    public static byte[] writeCustomData(Consumer<RegistryFriendlyByteBuf> dataWriter, RegistryAccess registryAccess) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FriendlyByteBufUtil.writeCustomData:(Ljava/util/function/Consumer;Lnet/minecraft/core/RegistryAccess;)[B");
    }
}
