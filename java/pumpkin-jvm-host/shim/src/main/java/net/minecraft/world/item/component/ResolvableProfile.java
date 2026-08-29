package net.minecraft.world.item.component;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.datafixers.util.Either;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.server.players.ProfileResolver;
import net.minecraft.world.entity.player.PlayerSkin;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import dev.pumpkin.shim.Unimplemented;

public abstract class ResolvableProfile implements TooltipProvider {

    private static ResolvableProfile create(Either<GameProfile, ResolvableProfile.Partial> value, PlayerSkin.Patch patch) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile.create:(Lcom/mojang/datafixers/util/Either;Lnet/minecraft/world/entity/player/PlayerSkin$Patch;)Lnet/minecraft/world/item/component/ResolvableProfile;");
    }

    protected abstract Either<GameProfile, ResolvableProfile.Partial> unpack();

    protected ResolvableProfile(GameProfile partialProfile, PlayerSkin.Patch skinPatch) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile.<init>:(Lcom/mojang/authlib/GameProfile;Lnet/minecraft/world/entity/player/PlayerSkin$Patch;)V");
    }

    public abstract CompletableFuture<GameProfile> resolveProfile(ProfileResolver profileResolver);

    public abstract Optional<String> name();

    public static final class Dynamic extends ResolvableProfile {

        private Dynamic(Either<String, UUID> nameOrId, PlayerSkin.Patch skinPatch) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Dynamic.<init>:(Lcom/mojang/datafixers/util/Either;Lnet/minecraft/world/entity/player/PlayerSkin$Patch;)V");
        }

        public Optional<String> name() {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Dynamic.name:()Ljava/util/Optional;");
        }

        public boolean equals(Object o) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Dynamic.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Dynamic.hashCode:()I");
        }

        protected Either<GameProfile, ResolvableProfile.Partial> unpack() {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Dynamic.unpack:()Lcom/mojang/datafixers/util/Either;");
        }

        public CompletableFuture<GameProfile> resolveProfile(ProfileResolver profileResolver) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Dynamic.resolveProfile:(Lnet/minecraft/server/players/ProfileResolver;)Ljava/util/concurrent/CompletableFuture;");
        }

        public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Dynamic.addToTooltip:(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;Lnet/minecraft/core/component/DataComponentGetter;)V");
        }

        protected Dynamic() {
        }
    }

    protected record Partial(Optional<String> name, Optional<UUID> id, PropertyMap properties) {
    }

    public static final class Static extends ResolvableProfile {

        private Static(Either<GameProfile, ResolvableProfile.Partial> contents, PlayerSkin.Patch skinPatch) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Static.<init>:(Lcom/mojang/datafixers/util/Either;Lnet/minecraft/world/entity/player/PlayerSkin$Patch;)V");
        }

        public CompletableFuture<GameProfile> resolveProfile(ProfileResolver profileResolver) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Static.resolveProfile:(Lnet/minecraft/server/players/ProfileResolver;)Ljava/util/concurrent/CompletableFuture;");
        }

        protected Either<GameProfile, ResolvableProfile.Partial> unpack() {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Static.unpack:()Lcom/mojang/datafixers/util/Either;");
        }

        public Optional<String> name() {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Static.name:()Ljava/util/Optional;");
        }

        public boolean equals(Object o) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Static.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Static.hashCode:()I");
        }

        public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ResolvableProfile$Static.addToTooltip:(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;Lnet/minecraft/core/component/DataComponentGetter;)V");
        }

        protected Static() {
        }
    }

    protected ResolvableProfile() {
    }
}
