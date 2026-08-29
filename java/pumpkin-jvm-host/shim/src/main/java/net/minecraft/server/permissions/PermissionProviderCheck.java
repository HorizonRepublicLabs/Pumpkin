package net.minecraft.server.permissions;

import java.util.function.Predicate;
import dev.pumpkin.shim.Unimplemented;

public record PermissionProviderCheck<T extends PermissionSetSupplier>(PermissionCheck test) implements Predicate<T> {

    public boolean test(T t) {
        throw Unimplemented.forMember("net/minecraft/server/permissions/PermissionProviderCheck.test:(Lnet/minecraft/server/permissions/PermissionSetSupplier;)Z");
    }
}
