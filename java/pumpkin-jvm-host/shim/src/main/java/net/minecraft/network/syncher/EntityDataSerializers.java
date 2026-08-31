package net.minecraft.network.syncher;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.LivingEntity;
import dev.pumpkin.shim.Stubs;

public class EntityDataSerializers {

    public static final EntityDataSerializer<String> STRING = Stubs.of(EntityDataSerializer.class, "net/minecraft/network/syncher/EntityDataSerializer");

    public static final EntityDataSerializer<Boolean> BOOLEAN = Stubs.of(EntityDataSerializer.class, "net/minecraft/network/syncher/EntityDataSerializer");

    public static final EntityDataSerializer<Optional<BlockPos>> OPTIONAL_BLOCK_POS = Stubs.of(EntityDataSerializer.class, "net/minecraft/network/syncher/EntityDataSerializer");

    public static final EntityDataSerializer<Optional<EntityReference<LivingEntity>>> OPTIONAL_LIVING_ENTITY_REFERENCE = Stubs.of(EntityDataSerializer.class, "net/minecraft/network/syncher/EntityDataSerializer");

    protected EntityDataSerializers() {
    }
}
