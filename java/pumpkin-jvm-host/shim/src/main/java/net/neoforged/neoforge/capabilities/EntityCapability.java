package net.neoforged.neoforge.capabilities;

import java.util.List;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public final class EntityCapability<T, C extends Object> extends BaseCapability<T, C> {

    public static <T, C extends Object> EntityCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/EntityCapability.create:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/EntityCapability;");
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
