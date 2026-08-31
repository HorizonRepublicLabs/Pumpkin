package net.neoforged.neoforge.common.extensions;

import java.util.Optional;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public interface IBucketPickupExtension {

    default Optional<SoundEvent> getPickupSound(BlockState state) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBucketPickupExtension.getPickupSound:(Lnet/minecraft/world/level/block/state/BlockState;)Ljava/util/Optional;");
    }
}
