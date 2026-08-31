package net.minecraft.world.level.block.entity;

import com.mojang.serialization.Codec;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.debug.DebugValueSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class BeehiveBlockEntity extends BlockEntity {

    public BeehiveBlockEntity(BlockPos worldPosition, BlockState blockState) {
    }

    public void setChanged() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BeehiveBlockEntity.setChanged:()V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BeehiveBlockEntity.isEmpty:()Z");
    }

    protected void loadAdditional(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BeehiveBlockEntity.loadAdditional:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void saveAdditional(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BeehiveBlockEntity.saveAdditional:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void applyImplicitComponents(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BeehiveBlockEntity.applyImplicitComponents:(Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BeehiveBlockEntity.collectImplicitComponents:(Lnet/minecraft/core/component/DataComponentMap$Builder;)V");
    }

    public void removeComponentsFromTag(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BeehiveBlockEntity.removeComponentsFromTag:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public void registerDebugValues(ServerLevel level, DebugValueSource.Registration registration) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BeehiveBlockEntity.registerDebugValues:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/util/debug/DebugValueSource$Registration;)V");
    }

    private static class BeeData {

        private BeeData(BeehiveBlockEntity.Occupant occupant) {
        }

        public boolean tick() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BeehiveBlockEntity$BeeData.tick:()Z");
        }

        protected BeeData() {
        }
    }

    public enum BeeReleaseStatus {

        HONEY_DELIVERED, BEE_RELEASED, EMERGENCY
    }

    public record Occupant(TypedEntityData<EntityType<?>> entityData, int ticksInHive, int minTicksInHive) {

        public static final Codec<List<BeehiveBlockEntity.Occupant>> LIST_CODEC = null;

        public static BeehiveBlockEntity.Occupant create(int ticksInHive) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BeehiveBlockEntity$Occupant.create:(I)Lnet/minecraft/world/level/block/entity/BeehiveBlockEntity$Occupant;");
        }
    }

    public BeehiveBlockEntity() {
    }
}
