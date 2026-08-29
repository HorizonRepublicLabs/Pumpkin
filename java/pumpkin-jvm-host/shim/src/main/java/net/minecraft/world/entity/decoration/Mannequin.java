package net.minecraft.world.entity.decoration;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class Mannequin extends Avatar {

    public Mannequin(EntityType<Mannequin> type, Level level) {
    }

    protected Mannequin(Level level) {
    }

    public static Mannequin create(EntityType<Mannequin> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/Mannequin.create:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/decoration/Mannequin;");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/Mannequin.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public ResolvableProfile getProfile() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/Mannequin.getProfile:()Lnet/minecraft/world/item/component/ResolvableProfile;");
    }

    protected Component getDescription() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/Mannequin.getDescription:()Lnet/minecraft/network/chat/Component;");
    }

    protected boolean isImmobile() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/Mannequin.isImmobile:()Z");
    }

    public boolean isEffectiveAi() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/Mannequin.isEffectiveAi:()Z");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/Mannequin.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/Mannequin.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public <T> T get(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/Mannequin.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    protected void applyImplicitComponents(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/Mannequin.applyImplicitComponents:(Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/Mannequin.applyImplicitComponent:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Z");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/decoration/Mannequin.aiStep:()V");
    }

    public Mannequin() {
    }
}
