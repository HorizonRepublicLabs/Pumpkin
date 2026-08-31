package net.neoforged.neoforge.capabilities;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public abstract class BaseCapability<T, C extends Object> {

    // Pumpkin divergence: real fields -- a capability token is its identity, and mods
    // compare and print these from the moment they are created.
    private final Identifier name;

    private final Class<T> pumpkinTypeClass;

    protected BaseCapability(Identifier name, Class<T> typeClass, Class<C> contextClass) {
        this.name = name;
        this.pumpkinTypeClass = typeClass;
    }

    public final Identifier name() {
        return name;
    }

    public final Class<T> typeClass() {
        return pumpkinTypeClass;
    }

    // Pumpkin divergence: the generator's convenience constructor has to satisfy the
    // real final fields; a token built this way has no identity and says so if asked.
    public BaseCapability() {
        this(null, null, null);
    }
}
