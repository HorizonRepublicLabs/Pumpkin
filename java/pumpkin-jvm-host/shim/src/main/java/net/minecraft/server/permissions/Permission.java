package net.minecraft.server.permissions;

import com.mojang.serialization.MapCodec;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public interface Permission {

    MapCodec<? extends Permission> codec();

    record Atom(Identifier id) implements Permission {

        public MapCodec<Permission.Atom> codec() {
            throw Unimplemented.forMember("net/minecraft/server/permissions/Permission$Atom.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public static Permission.Atom create(String name) {
            throw Unimplemented.forMember("net/minecraft/server/permissions/Permission$Atom.create:(Ljava/lang/String;)Lnet/minecraft/server/permissions/Permission$Atom;");
        }

        public static Permission.Atom create(Identifier id) {
            throw Unimplemented.forMember("net/minecraft/server/permissions/Permission$Atom.create:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/server/permissions/Permission$Atom;");
        }
    }

    record HasCommandLevel(PermissionLevel level) implements Permission {

        public MapCodec<Permission.HasCommandLevel> codec() {
            throw Unimplemented.forMember("net/minecraft/server/permissions/Permission$HasCommandLevel.codec:()Lcom/mojang/serialization/MapCodec;");
        }
    }
}
