package net.minecraft.server.permissions;

import com.mojang.serialization.MapCodec;
import dev.pumpkin.shim.Unimplemented;

public interface PermissionCheck {

    boolean check(PermissionSet source);

    MapCodec<? extends PermissionCheck> codec();

    class AlwaysPass implements PermissionCheck {

        protected AlwaysPass() {
        }

        public boolean check(PermissionSet source) {
            throw Unimplemented.forMember("net/minecraft/server/permissions/PermissionCheck$AlwaysPass.check:(Lnet/minecraft/server/permissions/PermissionSet;)Z");
        }

        public MapCodec<PermissionCheck.AlwaysPass> codec() {
            throw Unimplemented.forMember("net/minecraft/server/permissions/PermissionCheck$AlwaysPass.codec:()Lcom/mojang/serialization/MapCodec;");
        }
    }

    record Require(Permission permission) implements PermissionCheck {

        public MapCodec<PermissionCheck.Require> codec() {
            throw Unimplemented.forMember("net/minecraft/server/permissions/PermissionCheck$Require.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public boolean check(PermissionSet source) {
            throw Unimplemented.forMember("net/minecraft/server/permissions/PermissionCheck$Require.check:(Lnet/minecraft/server/permissions/PermissionSet;)Z");
        }
    }
}
