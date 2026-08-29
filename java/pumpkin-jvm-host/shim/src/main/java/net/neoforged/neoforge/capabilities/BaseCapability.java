package net.neoforged.neoforge.capabilities;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public abstract class BaseCapability<T, C extends Object> {

    protected BaseCapability(Identifier name, Class<T> typeClass, Class<C> contextClass) {
    }

    public final Identifier name() {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BaseCapability.name:()Lnet/minecraft/resources/Identifier;");
    }

    public BaseCapability() {
    }
}
