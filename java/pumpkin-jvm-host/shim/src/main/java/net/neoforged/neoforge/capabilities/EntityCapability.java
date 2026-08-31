package net.neoforged.neoforge.capabilities;

import java.util.List;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public final class EntityCapability<T, C extends Object> extends BaseCapability<T, C> {

    // Pumpkin divergence: real bodies -- interned by name, as NeoForge's registry does,
    // so creating the same capability twice hands back the same token and identity
    // comparisons hold. Queries against these tokens are a later subsystem; creating
    // and carrying one is pure identity.
    private static final java.util.concurrent.ConcurrentHashMap<Identifier, EntityCapability<?, ?>> PUMPKIN_INTERNED =
            new java.util.concurrent.ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T, C extends Object> EntityCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {
        return (EntityCapability<T, C>) PUMPKIN_INTERNED.computeIfAbsent(name,
                key -> new EntityCapability<>(key, typeClass, contextClass));
    }

    public static <T> EntityCapability<T, Void> createVoid(Identifier name, Class<T> typeClass) {
        return create(name, typeClass, Void.class);
    }

    public static synchronized List<EntityCapability<?, ?>> getAll() {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/EntityCapability.getAll:()Ljava/util/List;");
    }

    private EntityCapability(Identifier name, Class<T> typeClass, Class<C> contextClass) {
    }

    public T getCapability(Entity entity, C context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/EntityCapability.getCapability:(Lnet/minecraft/world/entity/Entity;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    public EntityCapability() {
    }
}
