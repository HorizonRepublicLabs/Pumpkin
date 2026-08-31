package net.minecraft.world.entity.animal.cow;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class MushroomCow extends AbstractCow implements Shearable {

    public MushroomCow(EntityType<? extends MushroomCow> type, Level level) {
    }

    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.getWalkTargetValue:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/LevelReader;)F");
    }

    public void thunderHit(ServerLevel level, LightningBolt lightningBolt) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.thunderHit:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LightningBolt;)V");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.mobInteract:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public void shear(ServerLevel level, SoundSource soundSource, ItemStack tool) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.shear:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/sounds/SoundSource;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public boolean readyForShearing() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.readyForShearing:()Z");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public <T> T get(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    protected void applyImplicitComponents(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.applyImplicitComponents:(Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.applyImplicitComponent:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Z");
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.getDefaultDimensions:(Lnet/minecraft/world/entity/Pose;)Lnet/minecraft/world/entity/EntityDimensions;");
    }

    public MushroomCow getBreedOffspring(ServerLevel level, AgeableMob partner) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow.getBreedOffspring:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/cow/MushroomCow;");
    }

    public enum Variant implements StringRepresentable {

        RED, BROWN;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow$Variant.getSerializedName:()Ljava/lang/String;");
        }

        private int id() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow$Variant.id:()I");
        }

        private static MushroomCow.Variant byId(int id) {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/MushroomCow$Variant.byId:(I)Lnet/minecraft/world/entity/animal/cow/MushroomCow$Variant;");
        }
    }

    public MushroomCow() {
    }
}
