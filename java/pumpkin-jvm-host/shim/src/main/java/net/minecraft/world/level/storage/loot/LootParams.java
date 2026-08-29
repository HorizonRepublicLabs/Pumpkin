package net.minecraft.world.level.storage.loot;

import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.context.ContextKey;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class LootParams {

    public LootParams(ServerLevel level, ContextMap params, Map<Identifier, LootParams.DynamicDrop> dynamicDrops, float luck) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootParams.<init>:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/util/context/ContextMap;Ljava/util/Map;F)V");
    }

    public ServerLevel getLevel() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootParams.getLevel:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public static class Builder {

        public Builder(ServerLevel level) {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootParams$Builder.<init>:(Lnet/minecraft/server/level/ServerLevel;)V");
        }

        public ServerLevel getLevel() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootParams$Builder.getLevel:()Lnet/minecraft/server/level/ServerLevel;");
        }

        public <T> T getOptionalParameter(ContextKey<T> param) {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootParams$Builder.getOptionalParameter:(Lnet/minecraft/util/context/ContextKey;)Ljava/lang/Object;");
        }

        public LootParams create(ContextKeySet contextKeySet) {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootParams$Builder.create:(Lnet/minecraft/util/context/ContextKeySet;)Lnet/minecraft/world/level/storage/loot/LootParams;");
        }

        protected Builder() {
        }
    }

    public interface DynamicDrop {

        void add(Consumer<ItemStack> output);
    }

    protected LootParams() {
    }
}
